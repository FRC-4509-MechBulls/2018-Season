package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class TurnCommand extends Command {

	double targetAngle; // in degrees
	double turnSpeed; // range of (0, 1). should be (0.60, 0.80)
	
	public TurnCommand(double target) {
		this.targetAngle = target;
		this.turnSpeed = 0.75;
	}
	
	public TurnCommand(double target, double speed) {
		this.targetAngle = target;
		if(speed < 0)
			this.turnSpeed = 0;
		else if(speed > 1)
			this.turnSpeed = 1;
		else
			this.turnSpeed = speed;
	}

	protected void initialize() {
		Robot.drivingSubsystem.stop(); // make sure the robot is stopped before we take the gyro angle.
	}

	protected void execute() {
		/* 1. Take the difference between the current angle and target angle. 
		 * 2. Divide it by the absolute value of itself to get 1 or -1, depending on whether the number was positive or negative.
		 * 4. Reduce the speed if the difference is small
		 */
		int direction;
		double diff = this.targetAngle - RobotMap.gyro.getAngle();
		
		try {
			direction = (int)(diff / Math.abs(diff)); // -1 if we need to turn left, 1 if right
		} catch(ArithmeticException e) {
			direction = 0; // if the difference is somehow 0 and the equation divides by it, just set it to 0.
		}
		
		Robot.drivingSubsystem.turn(((double)direction) * this.turnSpeed);
	}

	protected boolean isFinished() {
		if(this.timeSinceInitialized() > 10) // 10 seconds
			return true; // if turning takes longer than 10 seconds, just give up. No pirouettes. 
		
		// Take the difference between the current angle and the target angle and check if it's close enough
		return Math.abs(RobotMap.gyro.getAngle() - this.targetAngle) < 3; // 3 = margin of error (in degrees)
	}

	protected void end() {
		System.out.println("TurnCommand ended. Error is " + Math.abs(RobotMap.gyro.getAngle() - this.targetAngle) + " degrees.");
		Robot.drivingSubsystem.stop();
	}
	
}
