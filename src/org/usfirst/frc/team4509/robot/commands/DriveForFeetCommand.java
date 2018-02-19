package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author FRC Team 4509
 */
public class DriveForFeetCommand extends Command {

	double distance; // in feet
	
	public DriveForFeetCommand(double distance) {
		requires(Robot.drivingSubsystem);
		this.distance = distance * (12 * Preferences.getInstance().getInt("TICKS_PER_INCH", RobotMap.TICKS_PER_INCH));
	}

	protected void initialize() {  }

	protected void execute() {
		if(Math.abs(Robot.drivingSubsystem.getEncoderTicks()) < Math.abs(this.distance))
			Robot.drivingSubsystem.drive(this.distance / Math.abs(this.distance));
	}

	protected boolean isFinished() {
		return Math.abs(Robot.drivingSubsystem.getEncoderTicks() - this.distance) < 0.5;
	}

	protected void end() {
		Robot.drivingSubsystem.stop();
	}

}