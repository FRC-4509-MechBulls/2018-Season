package org.usfirst.frc.team4509.robot.subsystems;

import org.usfirst.frc.team4509.robot.RobotMap;
import org.usfirst.frc.team4509.robot.commands.HoldWinchCommand;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;


public class WinchSubsystem extends Subsystem {

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new HoldWinchCommand());
	}
	
	public void set(double speed) {
		RobotMap.winchTalon.set(Preferences.getInstance().getDouble("BASE_WINCH_SPEED", 0.75) * speed);
	}
	
	public void stop() {
		RobotMap.winchTalon.set(0);
	}

}