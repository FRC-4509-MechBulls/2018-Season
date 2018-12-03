package org.usfirst.frc.team4509.robot.motionprofiling;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SelectLastProfileCommand extends InstantCommand {
	
	public SelectLastProfileCommand() {
		this.setRunWhenDisabled(true);
	}
	
	protected void execute() {
		Robot.motionProfilingSubsystem.cancelProfileCommand();
		Robot.motionProfilingSubsystem.selectLast();
	}

}
