package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class TurnTestCommand extends Command {

	double targetDegrees;

	public TurnTestCommand() {
		super(5);
		requires(Robot.drivingSubsystem);
	}

	protected void initialize() {
		RobotMap.gyro.reset();
		this.targetDegrees = 27 + RobotMap.gyro.getAngle();
		Robot.drivingSubsystem.stop();
	}

	protected void execute() {
		double multiplier = 0.5;
		if(this.targetDegrees - RobotMap.gyro.getAngle() > 0)
			Robot.drivingSubsystem.turn(1 * multiplier);
		else
			Robot.drivingSubsystem.turn(-1 * multiplier);
	}

	protected boolean isFinished() {
		System.out.println(Math.abs(this.targetDegrees - RobotMap.gyro.getAngle()) + " <= " + Preferences.getInstance().getDouble("GYRO_PRECISION", RobotMap.GYRO_PRECISION));
		return Math.abs(this.targetDegrees - RobotMap.gyro.getAngle()) <= Preferences.getInstance().getDouble("GYRO_PRECISION", RobotMap.GYRO_PRECISION) || this.isTimedOut();
	}

	protected void end() {
		Robot.drivingSubsystem.stop();
	}
	
}
