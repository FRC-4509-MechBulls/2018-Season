package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class DriveForSecondsTimedCommand extends Command {
	
	double speed, timeout;
	
	public DriveForSecondsTimedCommand(double speed, double seconds) {
		requires(Robot.drivingSubsystem);
		this.timeout = seconds;
		this.speed = speed;
		System.out.println("DriveForSecondsTimedCommand constructed.");
	}

	@Override
	protected void initialize() {
		System.out.println("DriveForSecondsTimedCommand initialized.");
	}

	@Override
	protected void execute() {
		System.out.println("DriveForSecondsTimedCommand executed.");
		Robot.drivingSubsystem.drive(this.speed);
	}
	
	@Override
	protected boolean isFinished() {
		System.out.println("Time: " + this.timeSinceInitialized());
		return this.timeout < this.timeSinceInitialized();
	}

	@Override
	protected void end() {
		System.out.println("Time: " + this.timeSinceInitialized());
		Robot.drivingSubsystem.stop();
		System.out.println("DriveForSecondsTimedCommand ended.");
	}
}
