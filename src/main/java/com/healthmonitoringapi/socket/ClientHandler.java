package com.healthmonitoringapi.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.time.Period;

import com.healthmonitoringapi.entities.Infant;
import com.healthmonitoringapi.entities.VitalSign;
import com.healthmonitoringapi.repositories.InfantRepository;

import utils.LatestSign;

public class ClientHandler implements Runnable {

	private Integer months = 0;

	private Socket client;
	private BufferedReader inputStream = null;
	private PrintWriter pw = null;
	private NotificationBusiness logic = new NotificationBusiness();
	private InfantRepository infantRepository;
	private Infant infant;

	public ClientHandler(InfantRepository infantRepository) {
		this.infantRepository = infantRepository;

	}

	public ClientHandler(Socket client, InfantRepository infantRepository) throws IOException {
		this.client = client;
		this.infantRepository = infantRepository;
		if (this.client != null) {
			inputStream = new BufferedReader(new InputStreamReader(client.getInputStream()));
			pw = new PrintWriter(client.getOutputStream());
		}
	}

	@Override
	public void run() {
		String line = null;
		String data[] = null;
		if (inputStream != null) {
			while (true) {
				try {
					Thread.sleep(1000);
					if ((line = inputStream.readLine()) != null) {
						System.out.println(line);
						data = line.split(";");
						if (data != null) {
							VitalSign vitalSign = new VitalSign(Double.parseDouble(data[0]),
									Double.parseDouble(data[1]), "true".equals(data[2]), Double.parseDouble(data[3]),
									data[4]);
							if (infant == null) {
								infant = infantRepository.findByDevice(vitalSign.getDevice()).orElse(null);
								Period age = Period.between(infant.getBirthday(), LocalDate.now());
								months = age.getMonths();
							}
							LatestSign.getInstance().put(vitalSign.getDevice(), vitalSign);
							signHandler(vitalSign);
						}
					} else {
						inputStream.close();
						client.close();
						break;
					}
					pw.println("next");
					pw.flush();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void signHandler(VitalSign vitalSign) {
		if (infant == null) {
			return;
		}
		String parentName = infant.getParent().getFirstName() + " " + infant.getParent().getLastName();
		String phone = infant.getParent().getPhone();
		String address = infant.getAddress().getDescription();
		String userId = infant.getParent().getUserID();
		logic.check(vitalSign, months, parentName, phone, address, userId);
	}

}
