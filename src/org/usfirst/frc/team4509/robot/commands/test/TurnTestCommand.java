package org.usfirst.frc.team4509.robot.commands.test;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class TurnTestCommand extends Command {

	double targetDegrees;

	public TurnTestCommand() {
		super(5);
		requires(Robot.drivingSubsystem);
		this.targetDegrees = 90;
	}

	protected void initialize() {
		RobotMap.gyro.reset();
		System.out.println("The gyro is reporting an angle of " + RobotMap.gyro.getAngle() + " degrees.");
		Robot.drivingSubsystem.stop();
	}

	protected void execute() {
		double multiplier = 0.5;
		System.out.println("We are currently at " + RobotMap.gyro.getAngle() + " degrees.");
		if(this.targetDegrees > RobotMap.gyro.getAngle()) {
			System.out.println("Turning right.");
			Robot.drivingSubsystem.turn(1 * multiplier);
		} else {
			System.out.println("Turning left.");
			Robot.drivingSubsystem.turn(-1 * multiplier);
		}
	}

	protected boolean isFinished() {
		System.out.println("We still have " + (this.targetDegrees - RobotMap.gyro.getAngle()) + " degrees to turn.");
		if(this.isTimedOut()) {
			System.out.println("Timing out.");
			return true;
		}
		if(Math.abs(this.targetDegrees - RobotMap.gyro.getAngle()) <= Preferences.getInstance().getDouble("GYRO_PRECISION", RobotMap.GYRO_PRECISION)) {
			System.out.println("Turn appears to be successful.");
			return true;
		}
		return false;
	}

	protected void end() {
		System.out.println("Stopping...");
		Robot.drivingSubsystem.stop();
	}
	
}
