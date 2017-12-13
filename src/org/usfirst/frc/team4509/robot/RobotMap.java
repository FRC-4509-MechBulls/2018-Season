package org.usfirst.frc.team4509.robot;

import com.ctre.CANTalon;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI.Port;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	
	public static final int DRIVE_TALON_FRONT_LEFT_PORT = 8;
	public static final int DRIVE_TALON_FRONT_RIGHT_PORT = 0;
	public static final int DRIVE_TALON_BACK_LEFT_PORT = 5;
	public static final int DRIVE_TALON_BACK_RIGHT_PORT = 3;
	public static final Port NAVX_PORT = Port.kMXP;
	
	public static CANTalon driveTalonFrontLeft;
	public static CANTalon driveTalonFrontRight;
	public static CANTalon driveTalonBackLeft;
	public static CANTalon driveTalonBackRight;
	public static AHRS navX;
	
	public static void initTalons() {
		RobotMap.driveTalonFrontLeft   = new CANTalon(RobotMap.DRIVE_TALON_FRONT_LEFT_PORT);
		RobotMap.driveTalonFrontRight  = new CANTalon(RobotMap.DRIVE_TALON_FRONT_RIGHT_PORT);
		RobotMap.driveTalonBackLeft    = new CANTalon(RobotMap.DRIVE_TALON_BACK_LEFT_PORT);
		RobotMap.driveTalonBackRight   = new CANTalon(RobotMap.DRIVE_TALON_BACK_RIGHT_PORT);
		
		RobotMap.setTalonControlMode();
	}
	
	public static void setTalonControlMode() {
		RobotMap.driveTalonFrontRight.changeControlMode(CANTalon.TalonControlMode.Voltage);
		RobotMap.driveTalonBackRight.changeControlMode(CANTalon.TalonControlMode.Voltage);
		RobotMap.driveTalonFrontLeft.changeControlMode(CANTalon.TalonControlMode.Voltage);
		RobotMap.driveTalonBackLeft.changeControlMode(CANTalon.TalonControlMode.Voltage);
	}
	
	public static void initSensors() {
		RobotMap.navX = new AHRS(Port.kMXP);
	}

}
