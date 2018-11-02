package org.usfirst.frc.team4509.robot;

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
		// Init talons and set the neutral mode (what it does when told to stop)
		RobotMap.leftFrontDriveTalon = new WPI_TalonSRX(RobotMap.LEFT_FRONT_DRIVE_TALON_PORT);
		RobotMap.leftFrontDriveTalon.setNeutralMode(NeutralMode.Coast);
		RobotMap.leftBackDriveTalon = new WPI_TalonSRX(RobotMap.LEFT_BACK_DRIVE_TALON_PORT);
		RobotMap.leftBackDriveTalon.setNeutralMode(NeutralMode.Coast);
		RobotMap.rightFrontDriveTalon = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_DRIVE_TALON_PORT);
		RobotMap.rightFrontDriveTalon.setNeutralMode(NeutralMode.Coast);
		RobotMap.rightBackDriveTalon = new WPI_TalonSRX(RobotMap.RIGHT_BACK_DRIVE_TALON_PORT);
		RobotMap.rightBackDriveTalon.setNeutralMode(NeutralMode.Coast);
		
		RobotMap.leftEncoder = new Encoder(0, 1);
		RobotMap.leftEncoder = new Encoder(2, 3);
		
		// Tell the back talons to do whatever the front ones do.
		RobotMap.leftBackDriveTalon.follow(RobotMap.leftFrontDriveTalon);
		RobotMap.rightBackDriveTalon.follow(RobotMap.rightFrontDriveTalon);
		
		// Create the software drivetrain, giving us access to higher-level driving functions.
		RobotMap.drive = new DifferentialDrive(RobotMap.leftFrontDriveTalon, RobotMap.rightFrontDriveTalon);
	}
	
	public static void initWinch() {
		RobotMap.winchTalon = new WPI_TalonSRX(RobotMap.WINCH_TALON_PORT);
		RobotMap.winchTalon.setInverted(true);
	}
	
	public static void initGrabber() {
		RobotMap.grabberLeftTalon  = new WPI_TalonSRX(RobotMap.GRABBER_LEFT_TALON_PORT);
		RobotMap.grabberRightTalon = new WPI_TalonSRX(RobotMap.GRABBER_RIGHT_TALON_PORT);
		RobotMap.grabberRightTalon.follow(RobotMap.grabberLeftTalon);
	}
	
	// Initialize the human vision camera and configure it
	public static void initCamera() {
		RobotMap.camera = CameraServer.getInstance().startAutomaticCapture();
		// limit the resolution and frames per second to reduce latency
		RobotMap.camera.setResolution(640, 480);
		RobotMap.camera.setFPS(24);
	}
	
	// Initialize any extra sensors
	public static void initSensors() {
		RobotMap.gyro = new ADXRS450_Gyro();
		SmartDashboard.putData("Gyro", RobotMap.gyro);
		SmartDashboard.setPersistent("Gyro");
	}

}
