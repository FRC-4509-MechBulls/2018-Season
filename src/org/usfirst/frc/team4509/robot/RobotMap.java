package org.usfirst.frc.team4509.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap 
{
	
	public static CANTalon TalonDriveFrontRight = new CANTalon(0); // The front right driving Talon
	public static CANTalon TalonDriveBackRight = new CANTalon(3); // The back right driving Talon
	public static CANTalon TalonDriveFrontLeft = new CANTalon(8); // The front left driving Talon
	public static CANTalon TalonDriveBackLeft = new CANTalon(5); // The back left driving Talon 
	public static ADXRS450_Gyro Gyro = new ADXRS450_Gyro();
	
	public static void SetUpTalonControlMode()
	{
		TalonDriveFrontRight.changeControlMode(CANTalon.TalonControlMode.Voltage);
		TalonDriveBackRight.changeControlMode(CANTalon.TalonControlMode.Voltage);
		TalonDriveFrontLeft.changeControlMode(CANTalon.TalonControlMode.Voltage);
		TalonDriveBackLeft.changeControlMode(CANTalon.TalonControlMode.Voltage);
	}
	
	public static void InitializeGyroValues()
	{
		
	}
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
