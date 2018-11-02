package org.usfirst.frc.team4509.robot.MotionProfiling;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class MotionProfilingSubsystem extends Subsystem {
	
	private int selectedProfile = 0;
	private List<MotionProfile> profiles;
	
	public MotionProfilingSubsystem() {
		this.profiles = new ArrayList<MotionProfile>();
	}

	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void cancelProfileCommand() {
		if(this.getCurrentCommandName().equals("RecordMotionProfileCommand") || this.getCurrentCommandName().equals("RunMotionProfileCommand")) {
			this.getCurrentCommand().cancel();
		}
	}
	
	public void recordToSelected() {
		if(this.profiles.size() > 0) {
			if(this.profiles.get(this.selectedProfile).steps.size() > 0) {
				MotionProfile profile = new MotionProfile();
				profile.name = "Profile " + this.profiles.size();
				this.addProfile(profile);
				this.selectedProfile = this.profiles.size() - 1;
			}
			(new RecordMotionProfileCommand(this.profiles.get(this.selectedProfile))).start();
		}
	}
	
	public void runSelected() {
		if(this.profiles.size() > 0)
			if(MotionProfile.isValid(this.profiles.get(this.selectedProfile)))
				(new RunMotionProfileCommand(this.profiles.get(this.selectedProfile))).start();
			else
				System.out.println("Warning: Selected profile will not be run because it is invalid.");
	}
	
	public MotionProfile getSelected() {
		if(this.profiles.size() > 0)
			return this.profiles.get(this.selectedProfile);
		else
			return null;
	}
	
	public void addProfile(MotionProfile profile) {
		this.profiles.add(profile);
	}
	
	public void stop() {
		if(this.getCurrentCommand() != null)
			this.getCurrentCommand().cancel();
	}
	
	public void selectNext() {
		if(selectedProfile + 1 < this.profiles.size()) {
			selectedProfile += 1;
		} else {
			selectedProfile = 0;
		}
		System.out.println("Selected profile " + (this.profiles.get(selectedProfile).name != null ? this.profiles.get(selectedProfile).name : " with no name"));
		if(this.profiles.get(selectedProfile).steps != null) {
			System.out.println("Steps:\n" + this.profiles.get(selectedProfile).steps);
		}
	}
	
	public void selectLast() {
		if(selectedProfile - 1 > -1) {
			selectedProfile -= 1;
		} else {
			selectedProfile = this.profiles.size() - 1;
		}
		System.out.println("Selected profile " + (this.profiles.get(selectedProfile).name != null ? this.profiles.get(selectedProfile).name : " with no name"));
		if(this.profiles.get(selectedProfile).steps != null) {
			System.out.println("Steps: " + this.profiles.get(selectedProfile).steps);
		}
	}
	
	// Set all the talons to the step's speeds
	public void apply(MotionProfileStep step) {
		RobotMap.leftFrontDriveTalon.set(step.leftFrontDriveTalonSpeed);
		RobotMap.rightFrontDriveTalon.set(step.rightFrontDriveTalonSpeed);
		RobotMap.grabberLeftTalon.set(step.grabberLeftTalonSpeed);
		RobotMap.winchTalon.set(step.winchTalonSpeed);
		System.out.println("p" + step);
	}

}
