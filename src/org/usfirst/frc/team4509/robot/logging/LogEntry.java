package org.usfirst.frc.team4509.robot.logging;

import java.util.ArrayList;

public class LogEntry {
	
	/*
	 * 0 = FATAL   - the robot will not be able to function
	 * 1 = ERROR   - the robot will function, but not properly
	 * 2 = WARNING - something happened that shouldn't, but it won't impair function
	 * 3 = INFO    - just some information about the status of the robot
	 * 4 = DEBUG   - extended info, e.g. sensor values, talon values, etc.
	 */
	int level;
	String originClass, originMethod, message;
	ArrayList<String> tags;
	
	public LogEntry(int l, String oC, String oM, String m) {
		this.level = l;
		this.originClass = oC;
		this.originMethod = oM;
		this.message = m;
		this.tags = new ArrayList<String>();
	}
	
	public LogEntry addTag(String tag) {
		this.tags.add(tag);
		return this;
	}
	
	public boolean hasTag(String tag) {
		return this.tags.contains(tag);
	}
	
	public void put() {
		Log.getInstance().put(this);
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(this.level).append(": ");
		str.append(this.originClass).append(".");
		str.append(this.originMethod).append("(): ");
		str.append(this.message);
		if(this.tags.size() > 0) {
			str.append(" Tags: ");
			for(int i = 0; i < this.tags.size(); i++) {
				str.append(this.tags.get(i));
				if(i != this.tags.size() - 1)
					str.append(",");
			}
		}
		
		return str.toString();
	}
	
}
