package de.dl0wh.clock;

import java.time.LocalTime;

public class CurrentLocalTime {
	
	public static String getFormatInDE() {
		LocalTime now = LocalTime.now();
		int hh = now.getHour();
		int mm = now.getMinute();
		int ss = now.getSecond();
		return hh + "." + mm + "." + ss;
	}
}
