package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class HoldWinchCommand extends Command {

	public HoldWinchCommand() {
		requires(Robot.winchSubsystem);
	}

	protected void execute() {
		Robot.winchSubsystem.set(0.1);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.winchSubsystem.stop();
	}

}
