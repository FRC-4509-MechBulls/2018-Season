package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.InstantCommand;


public class SwitchNeutralModeCommand extends InstantCommand {
	
	public void execute() {
		if(Robot.drivingSubsystem.getNeutralMode() == NeutralMode.Coast) {
			Robot.drivingSubsystem.setNeutralMode(NeutralMode.Brake);
		} else if(Robot.drivingSubsystem.getNeutralMode() == NeutralMode.Brake) {
			Robot.drivingSubsystem.setNeutralMode(NeutralMode.Coast);
		}
	}

}