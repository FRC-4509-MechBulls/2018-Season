package org.usfirst.frc.team4509.robot.commands;


import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;


/**
 * Turn the robot a given amount in degrees 
 * 
 * @author FRC Team 4509
 */
public class TurnCommand extends Command {

	double targetDegrees, zeroAngle;

	public TurnCommand(double turnDegrees) {
		requires(Robot.drivingSubsystem);
		this.targetDegrees = turnDegrees + RobotMap.gyro.getAngle();
	}

	protected void initialize() {
		Robot.drivingSubsystem.stop();
		this.zeroAngle = RobotMap.gyro.getAngle();
	}

	protected void execute() {
		double multiplier = Math.abs(this.targetDegrees - this.getCorrectedAngle()) < 10 ? 0.75 : 1;
		if(this.targetDegrees - this.getCorrectedAngle() > 0)
			Robot.drivingSubsystem.turn(1 * multiplier);
		else
			Robot.drivingSubsystem.turn(-1 * multiplier);
	}

	protected boolean isFinished() {
		return Math.abs(this.targetDegrees - this.getCorrectedAngle()) <= Preferences.getInstance().getDouble("GYRO_PRECISION", RobotMap.GYRO_PRECISION) || this.isTimedOut();
	}

	protected void end() {
		Robot.drivingSubsystem.stop();
	}
	
	private double getCorrectedAngle() {
		return RobotMap.gyro.getAngle() - this.zeroAngle;
	}

}
