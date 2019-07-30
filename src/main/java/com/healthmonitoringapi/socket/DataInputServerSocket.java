package com.healthmonitoringapi.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.healthmonitoringapi.repositories.InfantRepository;

public class DataInputServerSocket implements Runnable{

	private ServerSocket server;
	public int SERVER_PORT = 8085;
	private boolean serverStarted = false;
	
	private InfantRepository infantRepository;
	
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
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
