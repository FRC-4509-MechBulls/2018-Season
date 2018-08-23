package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class DirectDriveCommand extends Command {
	
	public DirectDriveCommand() {
		requires(Robot.drivingSubsystem);
	}

	protected void initialize() {
		if(Robot.oi.controller == null) throw new NullPointerException("Controller was null.");
	}

	public void execute() {
		Robot.drivingSubsystem.drive(Robot.oi.controller.getDrive(), Robot.oi.controller.getTurn());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.drivingSubsystem.stop();
	}

}
