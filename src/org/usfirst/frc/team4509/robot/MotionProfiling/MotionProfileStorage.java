package org.usfirst.frc.team4509.robot.MotionProfiling;


public class MotionProfileStorage {

	public static MotionProfile getEmptyProfile() {
		MotionProfile profile = new MotionProfile();
		profile.name = "Empty";
		return profile;
	}
	
	public static MotionProfile getStopProfile() {
		MotionProfile profile = new MotionProfile();
		profile.name = "Stop";
		profile.addStep(new MotionProfileStep("START",0,0,0,0.0,0.0));
		profile.addStep(new MotionProfileStep("END",0,0,0,0.0,0.0));
		return profile;
	}
	
}
