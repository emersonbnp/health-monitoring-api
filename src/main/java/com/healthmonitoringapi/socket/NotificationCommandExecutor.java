package com.healthmonitoringapi.socket;

public class NotificationCommandExecutor {

	public void sendNotification(NotificationCommand command) {
		command.execute();
	}

}
