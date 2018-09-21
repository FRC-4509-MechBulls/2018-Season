package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class DirectDriveCommand extends Command {
	
	public DirectDriveCommand() {
		requires(Robot.drivingSubsystem); // Tells the Scheduler that this command will need the DrivingSubsystem.
	}

	protected void initialize() {
		// Make sure the controller is initialized before we try to use it.
		if(Robot.oi.controller == null) throw new NullPointerException("Controller was null.");
	}

	public void execute() {
		Robot.drivingSubsystem.drive(Robot.oi.controller.getDrive(), Robot.oi.controller.getTurn()); // Use input directly from the controller
	}

	protected boolean isFinished() {
		return false; // We don't want the command to stop, we want it to be interrupted.
	}

	protected void end() {
		Robot.drivingSubsystem.stop();
	}

}
