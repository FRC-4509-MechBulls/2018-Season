package org.usfirst.frc.team4509.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @author FRC Team 4509
 */
public class RobotMap {
	
	public static final double GYRO_PRECISION = 0.5;
	public static final int TICKS_PER_METER = 2362;
	public static final int TICKS_PER_INCH = 60;
	public static final int PIXY_MAX_X = 319;
	public static final int PIXY_MAX_Y = 199;
	

	public static final int LEFT_DRIVE_TALON_PORT       = 1;
	public static final int SLIDE_DRIVE_TALON_PORT      = 5;
	public static final int RIGHT_DRIVE_TALON_PORT      = 2;
	public static final int WINCH_TALON_PORT            = 3;
	public static final int GRABBER_TALON_PORT          = 4;
	public static final int LEFT_DRIVE_ENCODER_PORT_1   = 2;
	public static final int LEFT_DRIVE_ENCODER_PORT_2   = 3;
	public static final int RIGHT_DRIVE_ENCODER_PORT_1  = 0;
	public static final int RIGHT_DRIVE_ENCODER_PORT_2  = 1;
	public static final SPI.Port GYRO_PORT = SPI.Port.kOnboardCS0;
	public static final SerialPort.Port ARDUINO_PORT = SerialPort.Port.kUSB1;
	
	
	public static WPI_TalonSRX leftDriveTalon;
	//public static WPI_TalonSRX slideDriveTalon;
	public static WPI_TalonSRX rightDriveTalon;
	public static WPI_TalonSRX winchTalon;
	public static WPI_TalonSRX grabberTalon;
	public static DifferentialDrive drive;
	public static Encoder leftDriveEncoder;
	public static Encoder rightDriveEncoder;
	public static ADXRS450_Gyro gyro;
	public static SerialPort arduino;
	public static UsbCamera camera;
	
  
	public static void initDrive() {
		// initialize the talons
		RobotMap.leftDriveTalon   = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_TALON_PORT);
		RobotMap.leftDriveTalon.setNeutralMode(NeutralMode.Coast);
		//RobotMap.slideDriveTalon  = new WPI_TalonSRX(RobotMap.SLIDE_DRIVE_TALON_PORT);
		//RobotMap.slideDriveTalon.setNeutralMode(NeutralMode.Coast);
		RobotMap.rightDriveTalon  = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_TALON_PORT);
		RobotMap.rightDriveTalon.setNeutralMode(NeutralMode.Coast);
		
		RobotMap.drive = new DifferentialDrive(RobotMap.leftDriveTalon, RobotMap.rightDriveTalon);
		
		// create the encoders
		RobotMap.leftDriveEncoder  = new Encoder(new DigitalInput(RobotMap.LEFT_DRIVE_ENCODER_PORT_1), new DigitalInput(RobotMap.LEFT_DRIVE_ENCODER_PORT_2));
		RobotMap.leftDriveEncoder.setReverseDirection(true);
		RobotMap.rightDriveEncoder = new Encoder(new DigitalInput(RobotMap.RIGHT_DRIVE_ENCODER_PORT_1), new DigitalInput(RobotMap.RIGHT_DRIVE_ENCODER_PORT_2));
		
		// add the encoders to the LiveWindow
		SmartDashboard.putData("Left Drive Encoder",  RobotMap.leftDriveEncoder);
		SmartDashboard.putData("Right Drive Encoder", RobotMap.rightDriveEncoder);
	}
	
	public static void initWinch() {
		RobotMap.winchTalon = new WPI_TalonSRX(RobotMap.WINCH_TALON_PORT);
		RobotMap.winchTalon.setInverted(true);
	}
	
	public static void initGrabber() {
		RobotMap.grabberTalon = new WPI_TalonSRX(RobotMap.GRABBER_TALON_PORT);
		RobotMap.grabberTalon.setInverted(true);
	}
	
	public static void initSensors() {
		RobotMap.gyro = new ADXRS450_Gyro(RobotMap.GYRO_PORT);
		SmartDashboard.putData("Gyro", RobotMap.gyro);
	}
	
	public static void initArduino() {
		RobotMap.arduino = new SerialPort(9600, RobotMap.ARDUINO_PORT);
		RobotMap.arduino.setReadBufferSize(255);
	}
	
	public static void initCamera() {
		RobotMap.camera = CameraServer.getInstance().startAutomaticCapture();
		RobotMap.camera.setResolution(640, 480);
	}

}
