package org.usfirst.frc.team4509.robot.commands;


import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


/**
 * Operate the grabber using input directly from the controller
 * 
 * @author FRC Team 4509
 */
public class DirectGrabberCommand extends Command {

	public DirectGrabberCommand() {
		requires(Robot.grabberSubsystem);
	}

	protected void initialize() {
		if(Robot.oi.controller == null) throw new NullPointerException("Controller was null.");
	}

	protected void execute() {
		Robot.grabberSubsystem.set(Robot.oi.controller.getGrabber());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.grabberSubsystem.stop();
	}

}
