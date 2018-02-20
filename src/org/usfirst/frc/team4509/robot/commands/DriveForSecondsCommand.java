package org.usfirst.frc.team4509.robot.commands;


import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * @author FRC Team 4509
 */
public class DriveForSecondsCommand extends Command {
	
	double xSpeed, ySpeed;
	
	public DriveForSecondsCommand(double seconds, double xSpeed, double ySpeed) {
		requires(Robot.drivingSubsystem);
		this.setTimeout(seconds);
		this.setInterruptible(false);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	@Override
	protected void initialize() {  }

	@Override
	protected void execute() {
		Robot.drivingSubsystem.drive(this.xSpeed, 0, this.ySpeed);
	}

	@Override
	protected void end() {
		Robot.drivingSubsystem.stop();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
