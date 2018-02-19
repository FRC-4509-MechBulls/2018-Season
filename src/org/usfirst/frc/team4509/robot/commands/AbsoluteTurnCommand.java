package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Turn to an absolute angle
 * 
 * @param targetDegrees angle to turn to. must be in the range of [0, 359]
 * @author FRC Team 4509
 */
public class AbsoluteTurnCommand extends Command {
	
	double targetDegrees;
	
	public AbsoluteTurnCommand(int targetDegrees) {
		if(targetDegrees < 0 || targetDegrees >= 360) throw new IllegalArgumentException("targetDegrees must be in the range of [0, 360]. targetDegrees was " + targetDegrees + ".");
		requires(Robot.drivingSubsystem);
		this.setInterruptible(false);
		this.targetDegrees = targetDegrees;
	}
	
	@Override
	protected void execute() {
		int diff = (int)(this.targetDegrees - (RobotMap.gyro.getAngle() % 360));
		if(Math.abs(diff) > 180) diff *= -1; // if we would have to turn more than 180 degrees, go the other direction
		if(diff > 0)
			Robot.drivingSubsystem.turn(1);
		else
			Robot.drivingSubsystem.turn(-1);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(this.targetDegrees - RobotMap.gyro.getAngle()) < RobotMap.GYRO_PRECISION;
	}
	
	@Override
	protected void end() {
		Robot.drivingSubsystem.stop();
	}

}