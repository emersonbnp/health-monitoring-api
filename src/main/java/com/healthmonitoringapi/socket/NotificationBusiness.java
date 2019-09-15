package com.healthmonitoringapi.socket;

import com.healthmonitoringapi.entities.VitalSign;

public class NotificationBusiness {

	// ACCEPTABLE PARAMETERS
	private final Double MIN_TEMP = 35.5;
	private final Double MAX_TEMP = 37.5;
	private final Integer OX = 95;
	private final NotificationCommandExecutor executor = new NotificationCommandExecutor();
	private boolean alertSent = false;

	public void check(VitalSign vitalSign, Integer months, String parentName, String phone, String address,
			String userId) {
		String notificacao = null;
		boolean alertHealthService = false;
		if (vitalSign.getHeartRate() < getMinHeartRate(months)) {
			notificacao = new String("RITMO CARDIACO DO LACTENTE ABAIXO DO NORMAL! RITMO CARDÍACO ESTÁ "
					+ vitalSign.getHeartRate() + " BPM.");
			alertHealthService = true;
		}
		if (vitalSign.getHeartRate() > getMaxHeartRate(months)) {
			notificacao = new String("RITMO CARDIACO DO LACTENTE ACIMA DO NORMAL! O RITMO CARDÍACO ESTÁ "
					+ vitalSign.getHeartRate() + " BPM.");
			alertHealthService = true;
		}
		if (vitalSign.getBreaststroke()) {
			notificacao = new String("LACTENTE DEITADO DE BRUÇOS!");
		}
		if (vitalSign.getOxygenLevel() < OX) {
			notificacao = new String("SATURAÇÃO DE OXIGÊNIO ABAIXO DE 95%!");
			alertHealthService = true;
		}
		if (vitalSign.getTemperature() < MIN_TEMP) {
			notificacao = new String(
					"HIPOTERMIA DETECTADA! A TEMPERATURA DO LACTENTE ESTÁ " + vitalSign.getTemperature() + " ºC.");
			alertHealthService = true;
		}
		if (vitalSign.getTemperature() > MAX_TEMP) {
			notificacao = new String(
					"FEBRE DETECTADA! A TEMPERATURA DO LACTENTE ESTÁ " + vitalSign.getTemperature() + " ºC.");
			alertHealthService = true;
		}
		if (!alertSent && alertHealthService) {
			alertHealthService(parentName, phone, address, notificacao);
		}
		if (!alertSent && notificacao != null) {
			alertParent(userId, phone, address, notificacao);
			alertSent = true;;
		}
	}

	private void alertHealthService(String parentName, String phone, String address, String message) {
		executor.sendNotification(new HealthServiceNotificationCommand(parentName, phone, address, message));
	}

	private void alertParent(String userId, String phone, String address, String message) {
		executor.sendNotification(new ParentNotificationCommand(userId, message));
	}

	private Integer getMinHeartRate(Integer months) {
		if (months <= 1) {
			return 70;
		} else {
			return 80;
		}
	}

	private Integer getMaxHeartRate(Integer months) {
		if (months <= 1) {
			return 170;
		} else if (months <= 11) {
			return 160;
		} else {
			return 130;
		}
	}

}
