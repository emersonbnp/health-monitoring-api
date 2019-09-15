package com.healthmonitoringapi.socket;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HealthServiceNotificationCommand implements NotificationCommand {

	private final OkHttpClient clientHttp = new OkHttpClient();
	private final MediaType JSON = MediaType.get("application/json; charset=utf-8");
	private final String NOTIFICATION_API = "http://localhsot:8085/notifications";
	private final String parentName;
	private final String phone;
	private final String addressDescription;
	private final String notificationDescription;

	public HealthServiceNotificationCommand(String parentName, String phone, String addressDescription,
			String notificationDescription) {
		this.parentName = parentName;
		this.phone = phone;
		this.addressDescription = addressDescription;
		this.notificationDescription = notificationDescription;
	}

	@Override
	public void execute() {
		String json = "{" + "\"notificationDescription\": \" " + notificationDescription + "\","
				+ "\"parentName\": \" " + parentName + "\","
				+ "\"phone\": \" " + phone + "\","
				+ "\"addressDescription\": \" " + addressDescription + "\"}";
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(NOTIFICATION_API).post(body).build();
		try (Response response = clientHttp.newCall(request).execute()) {

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
