package utils;

import java.util.HashMap;
import java.util.Map;

import com.healthmonitoringapi.entities.VitalSign;

public class LatestSign {

	public static Map<String, VitalSign> latestSign = new HashMap<>();
	
	public synchronized static Map<String, VitalSign> getInstance() {
		
		return latestSign;
	}
	
}
