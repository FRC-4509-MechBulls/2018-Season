package org.usfirst.frc.team4509.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class RobotMap {

	public static final int LEFT_FRONT_DRIVE_TALON_PORT  = 2;
	public static final int LEFT_BACK_DRIVE_TALON_PORT   = 1;
	public static final int RIGHT_FRONT_DRIVE_TALON_PORT = 3;
	public static final int RIGHT_BACK_DRIVE_TALON_PORT  = 4;
	public static final int GRABBER_LEFT_TALON_PORT      = 5;
	public static final int GRABBER_RIGHT_TALON_PORT     = 6;
	public static final int WINCH_TALON_PORT             = 7;
	
	public static WPI_TalonSRX leftFrontDriveTalon;
	public static WPI_TalonSRX leftBackDriveTalon;
	public static WPI_TalonSRX rightFrontDriveTalon;
	public static WPI_TalonSRX rightBackDriveTalon;
	public static WPI_TalonSRX grabberLeftTalon;
	public static WPI_TalonSRX grabberRightTalon;
	public static WPI_TalonSRX winchTalon;
	public static DifferentialDrive drive;
	public static UsbCamera camera;
	
	public static void initDrive() {
		RobotMap.leftFrontDriveTalon = new WPI_TalonSRX(RobotMap.LEFT_FRONT_DRIVE_TALON_PORT);
		RobotMap.leftFrontDriveTalon.setNeutralMode(NeutralMode.Coast);
		RobotMap.leftBackDriveTalon = new WPI_TalonSRX(RobotMap.LEFT_BACK_DRIVE_TALON_PORT);
		RobotMap.leftBackDriveTalon.setNeutralMode(NeutralMode.Coast);
		RobotMap.rightFrontDriveTalon = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_DRIVE_TALON_PORT);
		RobotMap.rightFrontDriveTalon.setNeutralMode(NeutralMode.Coast);
		RobotMap.rightBackDriveTalon = new WPI_TalonSRX(RobotMap.RIGHT_BACK_DRIVE_TALON_PORT);
		RobotMap.rightBackDriveTalon.setNeutralMode(NeutralMode.Coast);
		
		RobotMap.leftBackDriveTalon.follow(RobotMap.leftFrontDriveTalon);
		RobotMap.rightBackDriveTalon.follow(RobotMap.rightFrontDriveTalon);
		
		RobotMap.drive = new DifferentialDrive(RobotMap.leftFrontDriveTalon, RobotMap.rightFrontDriveTalon);
	}
	
	public static void initWinch() {
		RobotMap.winchTalon = new WPI_TalonSRX(RobotMap.WINCH_TALON_PORT);
		RobotMap.winchTalon.setInverted(true);
	}
	
	public static void initGrabber() {
		RobotMap.grabberLeftTalon = new WPI_TalonSRX(RobotMap.GRABBER_LEFT_TALON_PORT);
		RobotMap.grabberRightTalon = new WPI_TalonSRX(RobotMap.GRABBER_RIGHT_TALON_PORT);
		RobotMap.grabberRightTalon.follow(RobotMap.grabberLeftTalon);
	}
	
	public static void initCamera() {
		RobotMap.camera = CameraServer.getInstance().startAutomaticCapture();
		RobotMap.camera.setResolution(320, 240);
		RobotMap.camera.setFPS(20);
	}

}
