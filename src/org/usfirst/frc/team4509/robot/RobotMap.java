package org.usfirst.frc.team4509.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
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

	public static final int TICKS_PER_METER = 0;

	public static final int DRIVE_TALON_FRONT_LEFT_PORT   = 8;
	public static final int DRIVE_TALON_FRONT_RIGHT_PORT  = 0;
	public static final int DRIVE_TALON_BACK_LEFT_PORT    = 5;
	public static final int DRIVE_TALON_BACK_RIGHT_PORT   = 3;
	public static final int DRIVE_TALON_MIDDLE_LEFT_PORT  = 1;
	public static final int DRIVE_TALON_MIDDLE_RIGHT_PORT = 7;
	public static final int DRIVE_LEFT_ENCODER_PORT_1   = 0;
	public static final int DRIVE_LEFT_ENCODER_PORT_2   = 1;
	public static final int DRIVE_RIGHT_ENCODER_PORT_1  = 2;
	public static final int DRIVE_RIGHT_ENCODER_PORT_2  = 3;
	public static final int DRIVE_MIDDLE_ENCODER_PORT_1 = 4;
	public static final int DRIVE_MIDDLE_ENCODER_PORT_2 = 5;
	public static final SPI.Port NAVX_PORT = SPI.Port.kMXP;
	public static final SerialPort.Port ARDUINO_PORT = SerialPort.Port.kUSB1;
	
	public static WPI_TalonSRX driveTalonFrontLeft;
	public static WPI_TalonSRX driveTalonFrontRight;
	public static WPI_TalonSRX driveTalonBackLeft;
	public static WPI_TalonSRX driveTalonBackRight;
	public static WPI_TalonSRX driveTalonMiddleLeft;
	public static WPI_TalonSRX driveTalonMiddleRight;
	public static DifferentialDrive drive;
	public static Encoder driveLeftEncoder;
	public static Encoder driveRightEncoder;
	public static Encoder driveMiddleEncoder;
	public static AHRS navX;
	public static SerialPort arduino;
  
	public static void initDrive() {
		RobotMap.driveTalonFrontLeft   = new WPI_TalonSRX(RobotMap.DRIVE_TALON_FRONT_LEFT_PORT);
		RobotMap.driveTalonFrontRight  = new WPI_TalonSRX(RobotMap.DRIVE_TALON_FRONT_RIGHT_PORT);
		RobotMap.driveTalonBackLeft    = new WPI_TalonSRX(RobotMap.DRIVE_TALON_BACK_LEFT_PORT);
		RobotMap.driveTalonBackRight   = new WPI_TalonSRX(RobotMap.DRIVE_TALON_BACK_RIGHT_PORT);
		RobotMap.driveTalonMiddleLeft  = new WPI_TalonSRX(RobotMap.DRIVE_TALON_MIDDLE_LEFT_PORT);
		RobotMap.driveTalonMiddleRight = new WPI_TalonSRX(RobotMap.DRIVE_TALON_MIDDLE_RIGHT_PORT);
		
		RobotMap.driveTalonBackLeft.follow(driveTalonFrontLeft);
		RobotMap.driveTalonBackRight.follow(driveTalonFrontRight);
		RobotMap.driveTalonMiddleRight.follow(driveTalonMiddleLeft);
		
		RobotMap.drive = new DifferentialDrive(RobotMap.driveTalonFrontLeft, RobotMap.driveTalonFrontRight);
		
		RobotMap.driveLeftEncoder = new Encoder(new DigitalInput(RobotMap.DRIVE_LEFT_ENCODER_PORT_1), new DigitalInput(RobotMap.DRIVE_LEFT_ENCODER_PORT_2));
		RobotMap.driveRightEncoder = new Encoder(new DigitalInput(RobotMap.DRIVE_RIGHT_ENCODER_PORT_1), new DigitalInput(RobotMap.DRIVE_RIGHT_ENCODER_PORT_2));
		RobotMap.driveMiddleEncoder = new Encoder(new DigitalInput(RobotMap.DRIVE_MIDDLE_ENCODER_PORT_1), new DigitalInput(RobotMap.DRIVE_MIDDLE_ENCODER_PORT_2));
    
		SmartDashboard.putBoolean("init/Drive Initialized", true);
	}
	
	public static void initSensors() {
		RobotMap.navX = new AHRS(RobotMap.NAVX_PORT);
    
		SmartDashboard.putBoolean("init/Sensors Initialized", true);
	}
	
	public static void initArduino() {
		RobotMap.arduino = new SerialPort(9600, RobotMap.ARDUINO_PORT);
		RobotMap.arduino.write(new byte[]{ '4', '2', '0' }, 3);
		
		SmartDashboard.putBoolean("init/Arduino Initialized", true);
	}

}
