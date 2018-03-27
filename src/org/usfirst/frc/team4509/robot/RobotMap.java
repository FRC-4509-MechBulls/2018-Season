package org.usfirst.frc.team4509.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @author FRC Team 4509
 */
public class RobotMap {
	
	public static final double GYRO_PRECISION = 1;
	public static final int FEET_PER_SECOND = 2;
	public static final int PIXY_MAX_X = 319;
	public static final int PIXY_MAX_Y = 199;
	

	public static final int LEFT_FRONT_DRIVE_TALON_PORT  = 2;
	public static final int LEFT_BACK_DRIVE_TALON_PORT   = 1;
	public static final int RIGHT_FRONT_DRIVE_TALON_PORT = 3;
	public static final int RIGHT_BACK_DRIVE_TALON_PORT  = 4;
	public static final int GRABBER_LEFT_TALON_PORT      = 5;
	public static final int GRABBER_RIGHT_TALON_PORT     = 6;
	public static final int WINCH_TALON_PORT             = 7;
	public static final int LEFT_DRIVE_ENCODER_PORT_1  = 2;
	public static final int LEFT_DRIVE_ENCODER_PORT_2  = 3;
	public static final int RIGHT_DRIVE_ENCODER_PORT_1 = 0;
	public static final int RIGHT_DRIVE_ENCODER_PORT_2 = 1;
	public static final SPI.Port GYRO_PORT = SPI.Port.kOnboardCS0;
	public static final SerialPort.Port ARDUINO_PORT = SerialPort.Port.kUSB1;
	
	
	public static WPI_TalonSRX leftFrontDriveTalon;
	public static WPI_TalonSRX leftBackDriveTalon;
	public static WPI_TalonSRX rightFrontDriveTalon;
	public static WPI_TalonSRX rightBackDriveTalon;
	public static WPI_TalonSRX grabberLeftTalon;
	public static WPI_TalonSRX grabberRightTalon;
	public static WPI_TalonSRX winchTalon;
	public static DifferentialDrive drive;
	public static Encoder leftDriveEncoder;
	public static Encoder rightDriveEncoder;
	public static ADXRS450_Gyro gyro;
	public static SerialPort arduino;
	public static UsbCamera camera;
	
  
	public static void initDrive() {
		// initialize the talons
		RobotMap.leftFrontDriveTalon   = new WPI_TalonSRX(RobotMap.LEFT_FRONT_DRIVE_TALON_PORT);
		RobotMap.leftFrontDriveTalon.setNeutralMode(NeutralMode.Coast);
		RobotMap.leftBackDriveTalon   = new WPI_TalonSRX(RobotMap.LEFT_BACK_DRIVE_TALON_PORT);
		RobotMap.leftBackDriveTalon.setNeutralMode(NeutralMode.Coast);
		RobotMap.rightFrontDriveTalon  = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_DRIVE_TALON_PORT);
		RobotMap.rightFrontDriveTalon.setNeutralMode(NeutralMode.Coast);
		RobotMap.rightBackDriveTalon  = new WPI_TalonSRX(RobotMap.RIGHT_BACK_DRIVE_TALON_PORT);
		RobotMap.rightBackDriveTalon.setNeutralMode(NeutralMode.Coast);
		
		RobotMap.leftBackDriveTalon.follow(RobotMap.leftFrontDriveTalon);
		RobotMap.rightBackDriveTalon.follow(RobotMap.rightFrontDriveTalon);
		
		RobotMap.drive = new DifferentialDrive(RobotMap.leftFrontDriveTalon, RobotMap.rightFrontDriveTalon);
		
		// create the encoders
		RobotMap.leftDriveEncoder  = new Encoder(new DigitalInput(RobotMap.LEFT_DRIVE_ENCODER_PORT_1), new DigitalInput(RobotMap.LEFT_DRIVE_ENCODER_PORT_2), true);
		RobotMap.leftDriveEncoder.reset();
		RobotMap.rightDriveEncoder = new Encoder(new DigitalInput(RobotMap.RIGHT_DRIVE_ENCODER_PORT_1), new DigitalInput(RobotMap.RIGHT_DRIVE_ENCODER_PORT_2));
		RobotMap.rightDriveEncoder.reset();
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
	
	public static void initSensors() {
		RobotMap.gyro = new ADXRS450_Gyro(RobotMap.GYRO_PORT);
		RobotMap.gyro.calibrate();
	}
	
	public static void initArduino() {
		RobotMap.arduino = new SerialPort(9600, RobotMap.ARDUINO_PORT);
		RobotMap.arduino.setReadBufferSize(255);
	}
	
	public static void initCamera() {
		RobotMap.camera = CameraServer.getInstance().startAutomaticCapture();
		RobotMap.camera.setResolution(320, 240);
		RobotMap.camera.setFPS(20);
	}

}
