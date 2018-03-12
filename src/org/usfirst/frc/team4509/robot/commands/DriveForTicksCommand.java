package org.usfirst.frc.team4509.robot.commands;


import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * Drive for a given number of ticks
 * 
 * @author FRC Team 4509
 */
public class DriveForTicksCommand extends Command {

	int initTicks;
	int distance; // in ticks
	
	public DriveForTicksCommand(int distance) {
		requires(Robot.drivingSubsystem);
		this.distance = distance;
	}

	protected void initialize() {
		this.initTicks = (int)Robot.drivingSubsystem.getAverageEncoderTicks();
	}
	
	protected void execute() {
		SmartDashboard.putNumber("Distance Left", (Robot.drivingSubsystem.getAverageEncoderTicks() - this.initTicks) - this.distance);
		if(Robot.drivingSubsystem.getAverageEncoderTicks() - this.initTicks < this.distance) {
			Robot.drivingSubsystem.drive(1);
		}
	}
	
	protected boolean isFinished() {
		return (Robot.drivingSubsystem.getAverageEncoderTicks() - this.initTicks) >= this.distance;
	}
	
	protected void end() {
		Robot.drivingSubsystem.stop();
	}
	
}
