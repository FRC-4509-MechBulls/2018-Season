package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

// Turns the robot a given number of degrees
public class TurnCommand extends Command {

	double targetAngle; // in degrees
	
	public TurnCommand(double target) {
		this.targetAngle = target;
	}

	protected void initialize() {
		Robot.drivingSubsystem.stop(); // make sure the robot is stopped before we take the gyro angle.
	}

	protected void execute() {
		/* 1. Take the difference between the current angle and target angle. 
		 * 2. Divide it by the absolute value of itself to get 1 or -1, depending on whether the number was positive or negative.
		 * 3. Cast the double to an int.
		 */
		int direction;
		try {
			double diff = this.targetAngle - RobotMap.gyro.getAngle();
			direction = (int)(diff / Math.abs(diff)); // -1 if we need to turn left, 1 if right
		} catch(ArithmeticException e) {
			direction = 0; // if the difference is somehow 0 and the equation divides by it, just set it to 0.
		}
		Robot.drivingSubsystem.turn(direction);
	}

	protected boolean isFinished() {
		if(this.timeSinceInitialized() > 15) // 15 = seconds
			return true; // if turning takes longer than 15 seconds, just give up. No pirouettes. 
		
		// Take the difference between the current angle and the target angle and check if it's close enough
		return Math.abs(RobotMap.gyro.getAngle() - this.targetAngle) < 1; // 1 = margin of error (in degrees)
	}

	protected void end() {
		Robot.drivingSubsystem.stop();
	}
	
}
