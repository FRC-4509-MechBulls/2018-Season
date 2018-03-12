package org.usfirst.frc.team4509.robot.commands.test;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;


public class DriveForFeetTestCommand extends Command {

	double distance, originalTicks;
	int direction;
	
	public DriveForFeetTestCommand() {
		super(10);
		double distance = 1;
		this.distance = this.distance * (12 * Preferences.getInstance().getInt("TICKS_PER_INCH", RobotMap.TICKS_PER_INCH));
		System.out.println(distance + " feet = " + this.distance + " ticks.");
		this.direction = (int)(this.distance / Math.abs(this.distance));
		System.out.println("We are going " + (this.direction > 0 ? "forward" : "backward") + ".");
	}

	protected void initialize() {
		Robot.drivingSubsystem.resetEncoders();
		System.out.println("Encoders are at " + Robot.drivingSubsystem.getAverageEncoderTicks() + ".");
	} 

	protected void execute() {
		System.out.println("Driving at speed " + (this.distance / Math.abs(this.distance)) + ".");
		Robot.drivingSubsystem.drive(this.direction);
	}

	protected boolean isFinished() {
		System.out.println("The encoder says we are at " + Robot.drivingSubsystem.getAverageEncoderTicks() + " ticks");
		if(this.isTimedOut()) {
			System.out.println("Timed out.");
			return true;
		}
		if(direction == 1) {
			System.out.println("We still have " + (this.distance - Robot.drivingSubsystem.getAverageEncoderTicks()) + " ticks to drive.");
			return this.distance - Robot.drivingSubsystem.getAverageEncoderTicks() < 0.5;
		} else if(direction == -1) {
			System.out.println("We still have " + (Math.abs(this.distance) - Math.abs(Robot.drivingSubsystem.getAverageEncoderTicks())) + " ticks to drive.");
			return Math.abs(this.distance) - Math.abs(Robot.drivingSubsystem.getAverageEncoderTicks()) < 0.5;
		}
		return true;
	}

	protected void end() {
		System.out.println("Stopping...");
		Robot.drivingSubsystem.stop();
	}

}
