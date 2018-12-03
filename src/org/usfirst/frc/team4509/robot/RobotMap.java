package org.usfirst.frc.team4509.robot;

import org.usfirst.frc.team4509.robot.logging.LogEntry;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * This class maps hardware to software and provides initialization functions.
 */
public class RobotMap {

	// Ports
	public static final int LEFT_FRONT_DRIVE_TALON_PORT  = 2;
	public static final int LEFT_BACK_DRIVE_TALON_PORT   = 1;
	public static final int RIGHT_FRONT_DRIVE_TALON_PORT = 3;
	public static final int RIGHT_BACK_DRIVE_TALON_PORT  = 4;
	public static final int GRABBER_LEFT_TALON_PORT      = 5;
	public static final int GRABBER_RIGHT_TALON_PORT     = 6;
	public static final int WINCH_TALON_PORT             = 7;
	public static final String ROOT_DIRECTORY = "/u/";
	
	// Devices
	public static WPI_TalonSRX leftFrontDriveTalon;
	public static WPI_TalonSRX leftBackDriveTalon;
	public static WPI_TalonSRX rightFrontDriveTalon;
	public static WPI_TalonSRX rightBackDriveTalon;
	public static WPI_TalonSRX grabberLeftTalon;
	public static WPI_TalonSRX grabberRightTalon;
	public static WPI_TalonSRX winchTalon;
	public static DifferentialDrive drive;
	public static Encoder leftEncoder;
	public static Encoder rightEncoder;
	public static UsbCamera camera;
	public static ADXRS450_Gyro gyro;
	
	// Initialize anything related to driving (motor controllers, encoders, etc.)
	public static void initDrive() {
		try {
			RobotMap.leftFrontDriveTalon = new WPI_TalonSRX(RobotMap.LEFT_FRONT_DRIVE_TALON_PORT);
			RobotMap.leftBackDriveTalon = new WPI_TalonSRX(RobotMap.LEFT_BACK_DRIVE_TALON_PORT);
			RobotMap.rightFrontDriveTalon = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_DRIVE_TALON_PORT);
			RobotMap.rightBackDriveTalon = new WPI_TalonSRX(RobotMap.RIGHT_BACK_DRIVE_TALON_PORT);
			
			RobotMap.leftBackDriveTalon.follow(RobotMap.leftFrontDriveTalon);
			RobotMap.rightBackDriveTalon.follow(RobotMap.rightFrontDriveTalon);
			
			RobotMap.drive = new DifferentialDrive(RobotMap.leftFrontDriveTalon, RobotMap.rightFrontDriveTalon);
		} catch(Exception e) {
			(new LogEntry(0, "RobotMap", "initDrive", "Exception was thrown! Message: " + e.getMessage())).addTag("Driving Subsystem").put();
		}
		
		try {
			RobotMap.leftFrontDriveTalon.setNeutralMode(NeutralMode.Coast);
			RobotMap.leftBackDriveTalon.setNeutralMode(NeutralMode.Coast);
			RobotMap.rightFrontDriveTalon.setNeutralMode(NeutralMode.Coast);
			RobotMap.rightBackDriveTalon.setNeutralMode(NeutralMode.Coast);
			
			RobotMap.drive.setDeadband(0);
		} catch(Exception e) {
			(new LogEntry(1, "RobotMap", "initDrive", "Exception was thrown! Message: " + e.getMessage())).addTag("Driving Subsystem").put();
		}
	}
	
	public static void initWinch() {
		try {
			RobotMap.winchTalon = new WPI_TalonSRX(RobotMap.WINCH_TALON_PORT);
			RobotMap.winchTalon.setInverted(true);
		} catch(Exception e) {
			(new LogEntry(0, "RobotMap", "initWinch", "Exception was thrown! Message: " + e.getMessage())).addTag("Winch Subsystem").put();
		}
	}
	
	public static void initGrabber() {
		try {
			RobotMap.grabberLeftTalon  = new WPI_TalonSRX(RobotMap.GRABBER_LEFT_TALON_PORT);
			RobotMap.grabberRightTalon = new WPI_TalonSRX(RobotMap.GRABBER_RIGHT_TALON_PORT);
			RobotMap.grabberRightTalon.follow(RobotMap.grabberLeftTalon);
		} catch(Exception e) {
			(new LogEntry(0, "RobotMap", "initGrabber", "Exception was thrown! Message: " + e.getMessage())).addTag("Grabber Subsystem").put();
		}
	}
	
	public static void initCamera() {
		try {
			RobotMap.camera = CameraServer.getInstance().startAutomaticCapture();
			RobotMap.camera.setResolution(320, 240);
			RobotMap.camera.setFPS(20);
		} catch(Exception e) {
			(new LogEntry(1, "RobotMap", "initCamera", "Exception was thrown! Message: " + e.getMessage())).put();
		}
	}
	
	public static void initSensors() {
		try {
			RobotMap.gyro = new ADXRS450_Gyro();
			SmartDashboard.putData("Gyro", RobotMap.gyro);
		} catch (Exception e) {
			(new LogEntry(1, "RobotMap", "initSensors", "Exception was thrown when initializng gyro! Message: " + e.getMessage())).put();
		}
		
		try {
			RobotMap.leftEncoder = new Encoder(0, 1);
			RobotMap.leftEncoder = new Encoder(2, 3);
		} catch (Exception e) {
			(new LogEntry(1, "RobotMap", "initSensors", "Exception was thrown when initializng encoders! Message: " + e.getMessage())).put();
		}
	}

}
