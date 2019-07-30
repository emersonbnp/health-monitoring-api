package com.healthmonitoringapi.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.healthmonitoringapi.entities.Infant;
import com.healthmonitoringapi.entities.VitalSign;
import com.healthmonitoringapi.repositories.InfantRepository;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import utils.LatestSign;

public class ClientHandler implements Runnable {

	private static int COOLDOWN = 60;
	private int timeAlertOxUnder90 = 61;
	private int timeAlertPronePosition = 61;
	private int timeAlertBPMUnder80 = 61;

	private int timeOxUnder90 = 0;
	private int timeBPMUnder80 = 0;

	private Socket client;
	private BufferedReader inputStream = null;
	private PrintWriter pw = null;

	private InfantRepository infantRepository;

	public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

	private OkHttpClient clientHttp = new OkHttpClient();

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
									Double.parseDouble(data[1]), "S".equals(data[2]), Double.parseDouble(data[3]),
									data[4]);
							LatestSign.getInstance().put(vitalSign.getDevice(), vitalSign);
							signHandler(vitalSign);
						}
					} else {
						inputStream.close();
						client.close();
						break;
					}
					if (timeAlertBPMUnder80 <= COOLDOWN) {
						timeAlertBPMUnder80++;
					}
					if (timeAlertOxUnder90 <= COOLDOWN) {
						timeAlertOxUnder90++;
					}
					if (timeAlertPronePosition <= COOLDOWN) {
						timeAlertPronePosition++;
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
		Infant infant = infantRepository.findByDevice(vitalSign.getDevice()).orElse(null);
		if (infant == null) {
			return;
		}
		try {
			Notification notification = null;
			if ((notification = notify(vitalSign)) != null) {
				post("https://onesignal.com/api/v1/notifications", infant.getParent().getUserID(),
						notification.getMessage());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Notification notify(VitalSign vitalSign) {
		Notification notificacao = null;
		if (vitalSign.getHeartRate() < 80) {
			timeBPMUnder80++;
		} else {
			timeBPMUnder80 = 0;
		}
		if (vitalSign.getOxygenLevel() < 90) {
			timeOxUnder90++;
		} else {
			timeOxUnder90 = 0;
		}
		if (vitalSign.getHeartRate() < 80) {
			if (timeAlertBPMUnder80 >= COOLDOWN) {
				notificacao = new Notification(1, "RITMO CARDIACO DO LACTENTE ABAIXO DE 80 BPM!");
				timeAlertBPMUnder80 = 0;
				timeBPMUnder80 = 0;
			}
		}
		if ("S".equals(vitalSign.getBreaststroke())) {
			notificacao = new Notification(1, "LACTENTE DEITADO EM POSIÇÃO PRONADA!");
			timeAlertPronePosition = 0;
			timeAlertPronePosition = 0;
		}
		if (timeOxUnder90 > 30) {
			if (timeAlertOxUnder90 >= COOLDOWN) {
				notificacao = new Notification(1, "RITMO CARDIACO DO LACTENTE ABAIXO DE 80 BPM!");
				timeAlertOxUnder90 = 0;
				timeOxUnder90 = 0;
			}
		}
		return notificacao;
	}

	private String post(String url, String userID, String message) throws IOException {
		String json = "{" + "\"app_id\": \"e343031b-ccc7-44f2-aa67-0bec9402010b\"," + "\"include_player_ids\": [\""
				+ userID + "\"]," + "\"data\": {\"foo\": \"bar\"}," + "\"contents\": {\"en\": \"" + message + "\"}"
				+ "}";
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).post(body).build();
		try (Response response = clientHttp.newCall(request).execute()) {
			return response.body().string();
		}
	}

}
