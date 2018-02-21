package org.usfirst.frc.team4509.robot.subsystems;


import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * Controls the drive platform and slide drive talon
 * 
 * @author FRC Team 4509
 */
public class DrivingSubsystem extends Subsystem {

	public void initDefaultCommand() {}

	/**
	 * @param leftYAxis used to drive the left side of the robot
	 * @param rightYAxis used to drive the right side of the robot
	 */
	public void tankDriving(double leftYAxis, double rightYAxis) {
		double baseSpeed = Preferences.getInstance().getDouble("BASE_DRIVE_SPEED", 0.5);
		RobotMap.drive.tankDrive(baseSpeed * leftYAxis, baseSpeed * rightYAxis);
	}
	
	public void drive(double ySpeed, double rotation, double xSpeed) {
		double baseSpeed = Preferences.getInstance().getDouble("BASE_DRIVE_SPEED", 0.5);
		RobotMap.drive.arcadeDrive(baseSpeed * ySpeed, baseSpeed * rotation);
		RobotMap.slideDriveTalon.set(baseSpeed * xSpeed);
	}
		
	public void drive(double speed) {
		this.drive(speed, 0, 0);
	}
	
	/**
	 * @param direction the direction to turn. -1 is left, 1 is right
	 */
	public void turn(int direction) {
		this.drive(0, direction, 0);
	}
	
	public void stop() {
		RobotMap.leftDriveTalon.set(0);
		RobotMap.slideDriveTalon.set(0);
		RobotMap.rightDriveTalon.set(0);
	}
	
	public int getEncoderTicks() {
		return (int)((RobotMap.rightDriveEncoder.get() + RobotMap.leftDriveEncoder.get()) / 2);
	}
	
}