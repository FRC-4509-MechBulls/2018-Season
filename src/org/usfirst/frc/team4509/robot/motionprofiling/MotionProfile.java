package org.usfirst.frc.team4509.robot.motionprofiling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;

public class MotionProfile {
	
	public String name;
	List<MotionProfileStep> steps;
	Timer timer;
	
	public MotionProfile() {
		this.steps = new ArrayList<MotionProfileStep>();
		this.timer = new Timer();
	}
	
	public void startRecording() {
		this.timer.start();
	}
	
	public void stopRecording() {
		this.timer.stop();
	}
	
	public void addStep(MotionProfileStep step) {
		this.steps.add(step);
	}
	
	public void record() {
		MotionProfileStep step = MotionProfile.recordStep();
		if(this.timer.get() == 0)
			this.startRecording();
		step.time = this.timer.get();
		System.out.println("r" + step);
		this.addStep(step);
	}
	
	// get the first step before or at the given time
	public MotionProfileStep getStep(double t) {
		Collections.sort(this.steps);
		
		if(this.steps.size() == 0)
			return null;
		
		if(this.steps.get(0).time > t)
			return this.steps.get(0);
		
		if(this.steps.get(this.steps.size() - 1).time < t)
			return this.steps.get(this.steps.size() - 1);
		
		for(int i = 0; i < this.steps.size(); i++) {
			// if this step is before or at the time and the next step is after
			if(this.steps.get(i).time <= t && (i + 1 != this.steps.size() && this.steps.get(i + 1).time > t)) {
				return this.steps.get(i);
			}
		}
		return null;
	}
	
	// Create a step with the current talon speeds
	public static MotionProfileStep recordStep() {
		MotionProfileStep step = new MotionProfileStep();
		step.leftFrontDriveTalonSpeed = RobotMap.leftFrontDriveTalon.get();
		step.rightFrontDriveTalonSpeed = RobotMap.rightFrontDriveTalon.get();
		step.grabberLeftTalonSpeed = RobotMap.grabberLeftTalon.get();
		step.winchTalonSpeed = RobotMap.winchTalon.get();
		return step;
	}
	
	// returns true if the profile has steps, the first step is START, and the last step is END
	public static boolean isValid(MotionProfile profile) {
		return profile.steps.size() >= 2 && profile.steps.get(0).text.equals("START") && profile.steps.get(profile.steps.size() - 1).text.equals("END");
	}
	
}
