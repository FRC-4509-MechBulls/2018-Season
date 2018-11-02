package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class MoveWinchCommand extends TimedCommand {
	
	public double speed;
	
	public MoveWinchCommand(double timeout, double speed) {
		super(timeout);
		this.speed = speed;
	}

	protected void initialize() {
		Robot.winchSubsystem.stop();
	}

	protected void execute() {
		Robot.winchSubsystem.set(speed * 0.5);
	}

	protected void end() {
		Robot.winchSubsystem.stop();
	}
	
}