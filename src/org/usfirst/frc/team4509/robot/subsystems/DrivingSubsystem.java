package org.usfirst.frc.team4509.robot.subsystems;


import org.usfirst.frc.team4509.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * Controls the drive platform and slide drive talon
 * 
 * @author FRC Team 4509
 */
public class DrivingSubsystem extends Subsystem {
	
	public double baseDriveSpeed = DriveSpeedMode.Disabled.baseSpeed;

	public void initDefaultCommand() {}

	public void drive(double ySpeed, double rotation) {
		RobotMap.drive.arcadeDrive(this.baseDriveSpeed * ySpeed, this.baseDriveSpeed * rotation);
	}
		
	public void drive(double speed) {
		this.drive(speed, 0);
	}
	
	/**
	 * @param direction the direction to turn. -1 is left, 1 is right
	 */
	public void turn(double direction) {
		this.drive(0, direction);
	}
	
	public void stop() {
		RobotMap.leftFrontDriveTalon.set(0);
		RobotMap.rightFrontDriveTalon.set(0);
	}
	
	public enum DriveSpeedMode {
		Disabled(0), TeleOp(0.75), Auto(0.5), Full(1);
		public double baseSpeed;
		DriveSpeedMode(double baseSpeed) { this.baseSpeed = baseSpeed; }
	}
	
	public void setDriveSpeedMode(DriveSpeedMode mode) {
		this.baseDriveSpeed = mode.baseSpeed;
	}
	
	/**
	 * Changes the drive talons' neutral modes to the given mode.
	 * See {@link com.ctre.phoenix.motorcontrol.NeutralMode}
	 * @param mode the neutral mode to set to
	 */
	public void setNeutralMode(NeutralMode mode) {
		RobotMap.leftFrontDriveTalon.setNeutralMode(mode);
		RobotMap.leftBackDriveTalon.setNeutralMode(mode);
		RobotMap.rightFrontDriveTalon.setNeutralMode(mode);
		RobotMap.rightBackDriveTalon.setNeutralMode(mode);
	}
	
	public int getRightEncoderTicks() {
		return RobotMap.rightDriveEncoder.get();
	}
	
	public int getLeftEncoderTicks() {
		return RobotMap.leftDriveEncoder.get();
	}
	
	public double getAverageEncoderTicks() {
		return (RobotMap.leftDriveEncoder.get() + RobotMap.rightDriveEncoder.get()) / 2;
	}
	
	public void resetEncoders() {
		RobotMap.leftDriveEncoder.reset();
		RobotMap.rightDriveEncoder.reset();
	}
	
}