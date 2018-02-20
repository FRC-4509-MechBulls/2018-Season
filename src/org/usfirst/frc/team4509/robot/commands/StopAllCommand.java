package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class StopAllCommand extends Command {
	
	public StopAllCommand() {
		requires(Robot.drivingSubsystem);
		requires(Robot.winchSubsystem);
		requires(Robot.grabberSubsystem);
	}
	
	@Override
	protected void execute() {
		Robot.drivingSubsystem.stop();
		Robot.winchSubsystem.stop();
		Robot.grabberSubsystem.stop();
	}

	@Override
	protected boolean isFinished() { return false; }

}
