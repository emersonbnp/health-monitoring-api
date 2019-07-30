package com.healthmonitoringapi.entities;

public class VitalSign {

	private Double heartRate;
	private Double oxygenLevel;
	private Boolean breaststroke;
	private Double temperature;
	private String device;

	public VitalSign(Double heartRate, Double oxygenLevel, Boolean breaststroke, Double temperature, String device) {
		super();
		this.heartRate = heartRate;
		this.oxygenLevel = oxygenLevel;
		this.breaststroke = breaststroke;
		this.temperature = temperature;
		this.device = device;
	}

	public Double getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(Double heartRate) {
		this.heartRate = heartRate;
	}

	public Double getOxygenLevel() {
		return oxygenLevel;
	}

	public void setOxygenLevel(Double oxygenLevel) {
		this.oxygenLevel = oxygenLevel;
	}

	public Boolean getBreaststroke() {
		return breaststroke;
	}

	public void setBreaststroke(Boolean breaststroke) {
		this.breaststroke = breaststroke;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	@Override
	public String toString() {
		return "VitalSign [heartRate=" + heartRate + ", oxygenLevel=" + oxygenLevel + ", breaststroke=" + breaststroke
				+ ", temperature=" + temperature + ", device=" + device + "]";
	}

}
