package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DirectWinchCommand extends Command {

	public DirectWinchCommand() {
		requires(Robot.winchSubsystem);
	}

	protected void initialize() {
		if(Robot.oi.controller == null) throw new NullPointerException("Controller was null.");
	}

	protected void execute() {
		Robot.winchSubsystem.set(Robot.oi.controller.getWinch());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.winchSubsystem.stop();
	}

}
