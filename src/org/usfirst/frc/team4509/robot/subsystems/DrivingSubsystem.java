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
	
	public double baseDriveSpeed;
	
	public DrivingSubsystem() {
		super();
		this.setDriveSpeedMode(DriveSpeedMode.Disabled);
	}

	public void initDefaultCommand() {}

	public void drive(double ySpeed, double rotation) {
		
		double speed = ySpeed;
		if(Math.abs(speed) > 1)
			speed = Math.abs(ySpeed) / ySpeed;
		speed *= this.baseDriveSpeed;
		
		double rot = rotation;
		if(Math.abs(rot) > 1)
			rot = Math.abs(rot) / rot;
		rot *= this.baseDriveSpeed;
		
		RobotMap.drive.arcadeDrive(speed, rot);
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
		Disabled(0), TeleOp(0.75), Auto(0.65);
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
	
}