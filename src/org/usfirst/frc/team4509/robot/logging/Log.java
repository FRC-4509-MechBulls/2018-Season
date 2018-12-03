package org.usfirst.frc.team4509.robot.logging;

public class Log {
	
	private static final Log instance = new Log();
	
	private Log() {}
	
	public static Log getInstance() {
		return Log.instance;
	}
	
	public void put(LogEntry entry) {
		if(entry.level <= 2)
			System.out.println(entry);
	}

}
