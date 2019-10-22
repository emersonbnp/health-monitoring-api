package com.healthmonitoringapi.dto;

import lombok.Data;

@Data
public class VitalSignDTO {

	private Double heartRate;
	private Double oxygenLevel;
	private Boolean breaststroke;
	private Double temperature;
	private String device;

	public VitalSignDTO(Double heartRate, Double oxygenLevel, Boolean breaststroke, Double temperature, String device) {
		super();
		this.heartRate = heartRate;
		this.oxygenLevel = oxygenLevel;
		this.breaststroke = breaststroke;
		this.temperature = temperature;
		this.device = device;
	}

	@Override
	public String toString() {
		return "VitalSign [heartRate=" + heartRate + ", oxygenLevel=" + oxygenLevel + ", breaststroke=" + breaststroke
				+ ", temperature=" + temperature + ", device=" + device + "]";
	}

}
