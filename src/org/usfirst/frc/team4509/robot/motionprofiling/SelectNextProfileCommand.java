package org.usfirst.frc.team4509.robot.motionprofiling;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SelectNextProfileCommand extends InstantCommand {
	
	public SelectNextProfileCommand() {
		this.setRunWhenDisabled(true);
	}
	
	protected void execute() {
		Robot.motionProfilingSubsystem.cancelProfileCommand();
		Robot.motionProfilingSubsystem.selectNext();
	}

}
