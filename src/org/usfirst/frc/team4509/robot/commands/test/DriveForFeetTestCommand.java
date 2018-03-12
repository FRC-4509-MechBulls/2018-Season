package org.usfirst.frc.team4509.robot.commands.test;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;


public class DriveForFeetTestCommand extends Command {

	double distance;
	int direction;
	
	public DriveForFeetTestCommand() {
		double distance = 1;
		this.distance = this.distance * (12 * Preferences.getInstance().getInt("TICKS_PER_INCH", RobotMap.TICKS_PER_INCH));
		System.out.println(distance + " feet = " + this.distance + " ticks.");
		this.direction = 1;
	}

	protected void initialize() {
		Robot.drivingSubsystem.resetEncoders();
		System.out.println(this.distance);
		this.direction = (int)(this.distance / Math.abs(this.distance));
		System.out.println("We are going " + (this.direction > 0 ? "forward" : "backward") + ".");
	} 

	protected void execute() {
		System.out.println("Driving at speed " + (this.distance / Math.abs(this.distance)) + ".");
		Robot.drivingSubsystem.drive(this.distance / Math.abs(this.distance));
	}

	protected boolean isFinished() {
		if(direction == 1) {
			System.out.println("We still have " + (this.distance - Robot.drivingSubsystem.getAverageEncoderTicks()) + " ticks to drive.");
			return this.distance - Robot.drivingSubsystem.getAverageEncoderTicks() < 0.5;
		} else if(direction == -1) {
			System.out.println("We still have " + (Math.abs(this.distance) - Math.abs(Robot.drivingSubsystem.getAverageEncoderTicks())) + " ticks to drive.");
			return Math.abs(this.distance) - Math.abs(Robot.drivingSubsystem.getAverageEncoderTicks()) < 0.5;
		}
		System.out.println("We're done driving.");
		return true;
	}

	protected void end() {
		System.out.println("Stopping...");
		Robot.drivingSubsystem.stop();
	}

}
