package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.subsystems.DrivingSubsystem;

import edu.wpi.first.wpilibj.command.Command;


/**
 * Command to drive straight for the given number of seconds, disregarding angle corrections made along the way.
 */
public class TimedDriveCommand extends Command {

	private double timeout;
	private int direction;
	
	public TimedDriveCommand(double timeout, int direction) {
		if(!(direction == -1 || direction == 1))
			throw(new IllegalArgumentException("Direction must be -1 or 1."));
		requires(Robot.drivingSubsystem);
		this.timeout = timeout;
		this.direction = direction;
	}
	
	protected void initialize() {
		Robot.drivingSubsystem.stop();
		Robot.drivingSubsystem.setDriveSpeedMode(DrivingSubsystem.DriveSpeedMode.Full);
	}

	protected void execute() {
		Robot.drivingSubsystem.drive(this.direction);
	}
	
	protected boolean isFinished() {
		return this.timeout != -1 && this.timeSinceInitialized() >= this.timeout;
	}

	protected void end() {
		Robot.drivingSubsystem.stop();
	}
	
}
