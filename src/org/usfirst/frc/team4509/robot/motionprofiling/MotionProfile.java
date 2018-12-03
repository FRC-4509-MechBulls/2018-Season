package org.usfirst.frc.team4509.robot.motionprofiling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.usfirst.frc.team4509.robot.logging.LogEntry;

public class MotionProfile {
	
	public String name;
	MotionProfileStep[] steps;
	
	private MotionProfile() {}
	
	public static class Builder {
		
		String name;
		List<MotionProfileStep> steps;
		
		public Builder(String name) {
			this.name = name;
			this.steps = new ArrayList<MotionProfileStep>();
		}
		
		public void add(MotionProfileStep step) {
			if(this.steps.size() == 0 || step.time > this.steps.get(this.steps.size() - 1).time) {
				(new LogEntry(3, "MotionProfile", "add", "Added step " + step + " to profile " + this.name + "."))
					.addTag("Motion Profiling Subsystem").put();
				this.steps.add(step);
			} else {
				(new LogEntry(1, "MotionProfile", "add", "Tried to add step with an earlier time than the last step of profile " + this.name + "!"))
					.addTag("Motion Profiling Subsystem").put();
			}
		}
		
		public MotionProfile build() {
			MotionProfile profile = new MotionProfile();
			
			if(this.name == null) {
				(new LogEntry(1, "MotionProfile", "build", "Tried to build profile without a name!"))
					.addTag("Motion Profiling Subsystem").put();
				return null;
			}
			profile.name = this.name;
			
			Collections.sort(this.steps);
			if(this.steps.size() < 2) {
				System.out.println();
				(new LogEntry(1, "MotionProfile", "build", "Tried to build profile with less than two steps!"))
					.addTag("Motion Profiling Subsystem").put();
				return null;
			}
			if(!this.steps.get(0).text.equals("START")) {
				(new LogEntry(1, "MotionProfile", "build", "Tried to build profile where the first step wasn't START!"))
					.addTag("Motion Profiling Subsystem").put();
				return null;
			}
			if(!this.steps.get(this.steps.size() - 1).text.equals("END")) {
				(new LogEntry(1, "MotionProfile", "build", "Tried to build profile where the last step wasn't END!"))
					.addTag("Motion Profiling Subsystem").put();
				return null;
			}
			profile.steps = this.steps.toArray(new MotionProfileStep[0]);
				
			return profile;
		}
		
	}
	
	// get the first step before or at the given time
	public MotionProfileStep getStep(double t) {
		if(this.steps[0].time > t)
			return this.steps[0];
		
		if(this.steps[this.steps.length - 1].time < t)
			return this.steps[this.steps.length - 1];
		
		for(int i = 0; i < this.steps.length; i++) {
			// if this step is before or at the time and the next step is after
			if(this.steps[i].time <= t && (i + 1 != this.steps.length && this.steps[i + 1].time > t)) {
				return this.steps[i];
			}
		}
		(new LogEntry(2, "MotionProfile", "getStep", "Had to return null!")).addTag("Motion Profiling Subsystem").put();
		return null;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder(this.name + ":");
		for(int i = 0; i < this.steps.length; i++) {
			str.append(this.steps[i]);
			if(i != this.steps.length - 1)
				str.append(";");
		}
		return str.toString();
	}
	
}
