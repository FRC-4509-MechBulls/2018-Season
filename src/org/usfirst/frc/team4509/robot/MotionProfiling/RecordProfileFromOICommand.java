package org.usfirst.frc.team4509.robot.MotionProfiling;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class RecordProfileFromOICommand extends InstantCommand {
	
	protected void execute() {
		Robot.motionProfilingSubsystem.cancelProfileCommand();
		Robot.motionProfilingSubsystem.recordToSelected();
	}

}
