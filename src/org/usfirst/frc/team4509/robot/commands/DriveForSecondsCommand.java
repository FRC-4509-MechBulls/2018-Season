package org.usfirst.frc.team4509.robot.commands;


import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


/**
 * Drive for a given amount of seconds
 * 
 * @author FRC Team 4509
 */
public class DriveForSecondsCommand extends Command {
	
	double speed;
	
	public DriveForSecondsCommand(double speed, double seconds) {
		requires(Robot.drivingSubsystem);
		this.setTimeout(seconds);
		this.speed = speed;
	}

	@Override
	protected void initialize() {  }

	@Override
	protected void execute() {
		Robot.drivingSubsystem.drive(this.speed);
	}

	@Override
	protected void end() {
		Robot.drivingSubsystem.stop();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
