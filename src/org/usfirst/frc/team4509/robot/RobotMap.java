package org.usfirst.frc.team4509.robot;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
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
	

	public static final int DRIVE_TALON_FRONT_LEFT_PORT   = 1;
	public static final int DRIVE_TALON_FRONT_RIGHT_PORT  = 3;
	public static final int DRIVE_TALON_MIDDLE_PORT       = 5;
	public static final int DRIVE_TALON_BACK_LEFT_PORT    = 2;
	public static final int DRIVE_TALON_BACK_RIGHT_PORT   = 4;
	public static final int WINCH_TALON_PORT              = 7;
	public static final int GRABBER_TALON_PORT            = 8;
	public static final int DRIVE_LEFT_ENCODER_PORT_1   = 2;
	public static final int DRIVE_LEFT_ENCODER_PORT_2   = 3;
	public static final int DRIVE_RIGHT_ENCODER_PORT_1  = 0;
	public static final int DRIVE_RIGHT_ENCODER_PORT_2  = 1;
	public static final SPI.Port GYRO_PORT = SPI.Port.kOnboardCS0;
	public static final SerialPort.Port ARDUINO_PORT = SerialPort.Port.kUSB1;
	
	
	public static WPI_TalonSRX driveTalonFrontLeft;
	public static WPI_TalonSRX driveTalonFrontRight;
	public static WPI_TalonSRX driveTalonBackLeft;
	public static WPI_TalonSRX driveTalonBackRight;
	public static WPI_TalonSRX driveTalonMiddle;
	public static WPI_TalonSRX winchTalon;
	public static WPI_TalonSRX grabberTalon;
	public static DifferentialDrive drive;
	public static Encoder driveLeftEncoder;
	public static Encoder driveRightEncoder;
	public static ADXRS450_Gyro gyro;
	public static SerialPort arduino;
	
  
	public static void initDrive() {
		// initialize the talons
		RobotMap.driveTalonFrontLeft   = new WPI_TalonSRX(RobotMap.DRIVE_TALON_FRONT_LEFT_PORT);
		RobotMap.driveTalonFrontRight  = new WPI_TalonSRX(RobotMap.DRIVE_TALON_FRONT_RIGHT_PORT);
		RobotMap.driveTalonBackLeft    = new WPI_TalonSRX(RobotMap.DRIVE_TALON_BACK_LEFT_PORT);
		RobotMap.driveTalonBackRight   = new WPI_TalonSRX(RobotMap.DRIVE_TALON_BACK_RIGHT_PORT);
		RobotMap.driveTalonMiddle      = new WPI_TalonSRX(RobotMap.DRIVE_TALON_MIDDLE_PORT);
		
		// add the talons to the LiveWindow
		/*SmartDashboard.putData("Front Left Drive Talon",   RobotMap.driveTalonFrontLeft);
		SmartDashboard.putData("Front Right Drive Talon",  RobotMap.driveTalonFrontRight);
		SmartDashboard.putData("Back Left Drive Talon",    RobotMap.driveTalonBackLeft);
		SmartDashboard.putData("Back Right Drive Talon",   RobotMap.driveTalonBackRight);
		SmartDashboard.putData("Middle Drive Talon",       RobotMap.driveTalonMiddle);*/
		
		// set the back talons to match the front
		RobotMap.driveTalonBackLeft.follow(driveTalonFrontLeft);
		RobotMap.driveTalonBackRight.follow(driveTalonFrontRight);
		
		RobotMap.drive = new DifferentialDrive(RobotMap.driveTalonFrontLeft, RobotMap.driveTalonFrontRight);
		SmartDashboard.putData("Drive", RobotMap.drive);
		
		// create the encoders
		RobotMap.driveLeftEncoder  = new Encoder(new DigitalInput(RobotMap.DRIVE_LEFT_ENCODER_PORT_1), new DigitalInput(RobotMap.DRIVE_LEFT_ENCODER_PORT_2));
		RobotMap.driveLeftEncoder.setReverseDirection(true);
		RobotMap.driveRightEncoder = new Encoder(new DigitalInput(RobotMap.DRIVE_RIGHT_ENCODER_PORT_1), new DigitalInput(RobotMap.DRIVE_RIGHT_ENCODER_PORT_2));
		
		// add the encoders to the LiveWindow
		SmartDashboard.putData("Left Drive Encoder",  RobotMap.driveLeftEncoder);
		SmartDashboard.putData("Right Drive Encoder", RobotMap.driveRightEncoder);
	}
	
	public static void initWinch() {
		RobotMap.winchTalon = new WPI_TalonSRX(RobotMap.WINCH_TALON_PORT);
	}
	
	public static void initGrabber() {
		RobotMap.grabberTalon = new WPI_TalonSRX(RobotMap.GRABBER_TALON_PORT);
	}
	
	public static void initSensors() {
		RobotMap.gyro = new ADXRS450_Gyro(RobotMap.GYRO_PORT);
		SmartDashboard.putData("Gyro", RobotMap.gyro);
	}
	
	public static void initArduino() {
		RobotMap.arduino = new SerialPort(9600, RobotMap.ARDUINO_PORT);
		RobotMap.arduino.setReadBufferSize(255);
	}

}
