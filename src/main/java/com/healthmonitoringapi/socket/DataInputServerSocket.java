package com.healthmonitoringapi.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.healthmonitoringapi.repositories.InfantRepository;

public class DataInputServerSocket implements Runnable{

	private ServerSocket server;
	public int SERVER_PORT = 8085;
	private boolean serverStarted = false;	
	private InfantRepository infantRepository;
	private List<ClientHandler> clients = new ArrayList<>();
	
	public DataInputServerSocket (InfantRepository infantRepository) {
		this.infantRepository = infantRepository;
	}
	
	public void init () throws IOException {
		this.server = new ServerSocket(SERVER_PORT);
		this.serverStarted = true;
	}
	
	@Override
	public void run () {
		if (serverStarted) {
			try {
				while(true) {
					Socket client = server.accept();
					if (client != null) {
						ClientHandler clientHandler = new ClientHandler(client, infantRepository);
						Thread clientThread = new Thread(clientHandler);
						clientThread.start();
						clients.add(clientHandler);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
