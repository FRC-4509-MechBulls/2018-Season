package org.usfirst.frc.team4509.robot.motionprofiling;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LoadProfileCommand extends InstantCommand {
	
	public LoadProfileCommand() {
		this.setRunWhenDisabled(true);
	}
	
	protected void execute() {
		Robot.motionProfilingSubsystem.addProfile(MotionProfileStorage.getProfile(SmartDashboard.getString("Profile to load", null)));
	}

}
