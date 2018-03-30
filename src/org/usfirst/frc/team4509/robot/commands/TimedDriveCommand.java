package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;


/**
 * Command to drive straight for the given number of seconds, disregarding angle corrections made along the way.
 */
public class TimedDriveCommand extends TimedCommand {

	private double speed;
	//private double zeroAngle;
	
	public TimedDriveCommand(double timeout, double speed) {
		super(timeout);
		requires(Robot.drivingSubsystem);
		this.speed = speed;
	}
	
	protected void initialize() {
		//Robot.drivingSubsystem.stop();
		//this.zeroAngle = RobotMap.gyro.getAngle();
	}

	protected void execute() {
		Robot.drivingSubsystem.drive(this.speed);
	}

	protected void end() {
		Robot.drivingSubsystem.stop();
		//this.correctDrift();
		//Robot.drivingSubsystem.stop();
	}
	
	/*private void correctDrift() {
		while(Math.abs(this.getCorrectedAngle()) > RobotMap.GYRO_PRECISION && !this.isFinished())
			Robot.drivingSubsystem.turn(-0.75 * (this.getCorrectedAngle() / Math.abs(this.getCorrectedAngle())));
	}
	
	private double getCorrectedAngle() {
		return RobotMap.gyro.getAngle() - this.zeroAngle;
	}*/
	
}
