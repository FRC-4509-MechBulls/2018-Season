package org.usfirst.frc.team4509.robot.subsystems;


import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * Controls the winch talon
 * 
 * @author FRC Team 4509
 */
public class WinchSubsystem extends Subsystem {

	@Override
	protected void initDefaultCommand() {  }
	
	public void set(double speed) {
		RobotMap.winchTalon.set(Preferences.getInstance().getDouble("BASE_WINCH_SPEED", 0.5) * speed);
	}
	
	public void stop() {
		RobotMap.winchTalon.set(0);
	}

}
