package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;


public class DriveForSecondsTimedCommand extends TimedCommand {
	
	double speed;
	
	public DriveForSecondsTimedCommand(double speed, double seconds) {
		super(seconds);
		requires(Robot.drivingSubsystem);
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
}
