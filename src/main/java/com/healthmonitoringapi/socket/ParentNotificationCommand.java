package com.healthmonitoringapi.socket;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ParentNotificationCommand implements NotificationCommand {

	private final OkHttpClient clientHttp = new OkHttpClient();
	private final MediaType JSON = MediaType.get("application/json; charset=utf-8");
	private final String ONE_SIGNAL = "https://onesignal.com/api/v1/notifications";
	private final String userId;
	private final String message;

	public ParentNotificationCommand(String userId, String message) {
		this.userId = userId;
		this.message = message;
	}

	@Override
	public void execute() {
		String json = "{" + "\"app_id\": \"e343031b-ccc7-44f2-aa67-0bec9402010b\"," + "\"include_player_ids\": [\""
				+ this.userId + "\"]," + "\"data\": {\"foo\": \"bar\"}," + "\"contents\": {\"en\": \"" + message + "\"}"
				+ "}";
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(ONE_SIGNAL).post(body).build();
		try (Response response = clientHttp.newCall(request).execute()) {

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
