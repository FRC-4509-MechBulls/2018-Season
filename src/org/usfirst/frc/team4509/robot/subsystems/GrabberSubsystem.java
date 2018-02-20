package org.usfirst.frc.team4509.robot.subsystems;


import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;


public class GrabberSubsystem extends Subsystem {

	@Override
	protected void initDefaultCommand() {  }
	
	public void set(double speed) {
		RobotMap.grabberTalon.set(Preferences.getInstance().getDouble("BASE_GRABBER_SPEED", 0.5) * speed);
	}
	
	public void stop() {
		RobotMap.grabberTalon.set(0);
	}

}
