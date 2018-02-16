package org.usfirst.frc.team4509.robot.commands;


import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;


/**
 * A command to turn the robot an amount given in degrees 
 * 
 * @author FRC Team 4509
 */
public class TurnCommand extends Command {

	double turnDegrees, targetDegrees;

	public TurnCommand(double turnDegrees) {
		requires(Robot.drivingSubsystem);
		this.setInterruptible(false);
		this.turnDegrees = turnDegrees > 180 ? turnDegrees - 360 : turnDegrees;
		this.targetDegrees = this.turnDegrees + RobotMap.gyro.getAngle();
	}

	protected void initialize() {
		Robot.drivingSubsystem.stop();
	}

	protected void execute() {
		if(this.turnDegrees - RobotMap.gyro.getAngle() > 0)
			Robot.drivingSubsystem.turn(1);
		else
			Robot.drivingSubsystem.turn(-1);
	}

	protected boolean isFinished() {
		return Math.abs(this.targetDegrees - RobotMap.gyro.getAngle()) < RobotMap.GYRO_PRECISION;
	}

	protected void end() {
		Robot.drivingSubsystem.stop();
	}

}
