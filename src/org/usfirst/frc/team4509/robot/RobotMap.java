package org.usfirst.frc.team4509.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static final int DRIVE_TALON_FRONT_LEFT_PORT  = 8;
	public static final int DRIVE_TALON_FRONT_RIGHT_PORT = 0;
	public static final int DRIVE_TALON_BACK_LEFT_PORT   = 5;
	public static final int DRIVE_TALON_BACK_RIGHT_PORT  = 3;
	public static final Port NAVX_PORT = Port.kMXP;
	public static final int LEFT_JOYSTICK_PORT  = 1;
	public static final int RIGHT_JOYSTICK_PORT = 0;
	
	public static TalonSRX driveTalonFrontLeft;
	public static TalonSRX driveTalonFrontRight;
	public static TalonSRX driveTalonBackLeft;
	public static TalonSRX driveTalonBackRight;
	public static RobotDrive drive;
	public static AHRS navX;
	public static Joystick leftJoystick;
	public static Joystick rightJoystick;
	
	public static void initControls() {
		RobotMap.leftJoystick  = new Joystick(RobotMap.LEFT_JOYSTICK_PORT);
		RobotMap.rightJoystick = new Joystick(RobotMap.RIGHT_JOYSTICK_PORT);
		SmartDashboard.putBoolean("init/Controls Initialized", true);
	}
	
	public static void initDriveTalons() {
		RobotMap.driveTalonFrontLeft   = new TalonSRX(RobotMap.DRIVE_TALON_FRONT_LEFT_PORT);
		RobotMap.driveTalonFrontRight  = new TalonSRX(RobotMap.DRIVE_TALON_FRONT_RIGHT_PORT);
		RobotMap.driveTalonBackLeft    = new TalonSRX(RobotMap.DRIVE_TALON_BACK_LEFT_PORT);
		RobotMap.driveTalonBackRight   = new TalonSRX(RobotMap.DRIVE_TALON_BACK_RIGHT_PORT);
		SmartDashboard.putBoolean("init/Drive Talons Initialized", true);
	}
	
	public static void initDrive() {
		new RobotDrive(RobotMap.driveTalonFrontLeft, RobotMap.driveTalonBackLeft, RobotMap.driveTalonFrontRight, RobotMap.driveTalonBackRight);
		RobotMap.drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		RobotMap.drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		RobotMap.drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		RobotMap.drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
		SmartDashboard.putBoolean("init/Drive Initialized", true);
	}
	
	public static void initSensors() {
		RobotMap.navX = new AHRS(Port.kMXP);
		SmartDashboard.putBoolean("init/Sensors Initialized", true);
	}

}
