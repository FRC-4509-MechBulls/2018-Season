package org.usfirst.frc.team4509.robot.commands;


import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;


/**
 * Turn the robot a given amount in degrees 
 * 
 * @author FRC Team 4509
 */
public class TurnTimedCommand extends TimedCommand {

	double speed;

	public TurnTimedCommand(double seconds, double speed) {
		super(seconds);
		requires(Robot.drivingSubsystem);
		this.speed = speed;
	}

	protected void initialize() {
		Robot.drivingSubsystem.stop();
	}

	protected void execute() {
		Robot.drivingSubsystem.turn(this.speed * 0.25);
	}

	protected void end() {
		Robot.drivingSubsystem.stop();
	}

}
