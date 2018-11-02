package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class MoveGrabberCommand extends TimedCommand {
	
	public double speed;
	
	public MoveGrabberCommand(double timeout, double speed) {
		super(timeout);
		this.speed = speed;
	}

	protected void initialize() {
		Robot.grabberSubsystem.stop();
	}
	
	protected void execute() {
		Robot.grabberSubsystem.set(speed);
	}

	protected void end() {
		Robot.grabberSubsystem.stop();
	}
	
}