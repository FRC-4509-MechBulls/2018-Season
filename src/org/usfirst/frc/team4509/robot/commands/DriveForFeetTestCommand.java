package org.usfirst.frc.team4509.robot.commands;


import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;


/**
 * Drive for a given number of feet
 * 
 * @author FRC Team 4509
 */
public class DriveForFeetTestCommand extends Command {

	double distance; // in feet
	int direction;
	
	public DriveForFeetTestCommand(double distance) {
		requires(Robot.drivingSubsystem);
	}

	protected void initialize() {
		Robot.drivingSubsystem.resetEncoders();
		this.distance = distance * (12 * Preferences.getInstance().getInt("TICKS_PER_INCH", RobotMap.TICKS_PER_INCH));
		System.out.println(this.distance);
		this.direction = (int)(this.distance / Math.abs(this.distance));
	} 

	protected void execute() {
		if(Math.abs(Robot.drivingSubsystem.getEncoderTicks()) < Math.abs(this.distance))
			Robot.drivingSubsystem.drive(this.distance / Math.abs(this.distance));
	}

	protected boolean isFinished() {
		if(direction == 1)
			return this.distance - Robot.drivingSubsystem.getEncoderTicks() < 0.5;
		else if(direction == -1)
			return Math.abs(this.distance) - Math.abs(Robot.drivingSubsystem.getEncoderTicks()) < 0.5;
		return true;
	}

	protected void end() {
		Robot.drivingSubsystem.stop();
	}

}