package org.usfirst.frc.team4509.robot.motionprofiling;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	public void record() {
		(new RecordMotionProfileCommand(new MotionProfile.Builder("Profile " + this.profiles.size()))).start();
	}
	
	public void runSelected() {
		if(this.profiles.size() > 0)
			(new RunMotionProfileCommand(this.profiles.get(this.selectedProfile))).start();
	}
	
	public MotionProfile getSelected() {
		if(this.profiles.size() > 0 && this.selectedProfile < this.profiles.size())
			return this.profiles.get(this.selectedProfile);
		else {
			System.out.println("ERROR: Tried to access out-of-bounds profile!");
			return null;
		}
	}
	
	public void addProfile(MotionProfile profile) {
		if(profile != null) {
			this.profiles.add(profile);
		} else {
			System.out.println("ERROR: Tried to add null profile to MotionProfilingSubsystem list!");
		}
	}
	
	public void stop() {
		if(this.getCurrentCommand() != null)
			this.getCurrentCommand().cancel();
	}
	
	public void selectNext() {
		if(this.selectedProfile + 1 < this.profiles.size()) {
			this.selectedProfile += 1;
		} else {
			this.selectedProfile = 0;
		}
		SmartDashboard.putString("Selected Profile", Robot.motionProfilingSubsystem.getSelectedProfileName());
		System.out.println(this.profiles.get(this.selectedProfile));
	}
	
	public void selectLast() {
		if(this.selectedProfile - 1 > -1) {
			this.selectedProfile -= 1;
		} else {
			this.selectedProfile = this.profiles.size() - 1;
		}
		SmartDashboard.putString("Selected Profile", Robot.motionProfilingSubsystem.getSelectedProfileName());
		System.out.println(this.profiles.get(this.selectedProfile));
	}
	
	public String getSelectedProfileName() {
		return this.getSelected().name != null ? this.getSelected().name : "None Selected";
	}

}
