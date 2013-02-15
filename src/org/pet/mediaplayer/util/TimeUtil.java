package org.pet.mediaplayer.util;

public class TimeUtil {

	public static final String secondsToString(int progress) {
		int minutes = (int) (progress / 60);
		int seconds = (int) (progress % 60);
		String s = minutes + ":" + (seconds < 10 ? "0" + seconds : seconds);
		return s;
	}
	
}
