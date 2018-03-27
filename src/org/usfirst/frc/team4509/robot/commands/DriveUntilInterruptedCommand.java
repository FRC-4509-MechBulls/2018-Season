package org.usfirst.frc.team4509.robot.commands;


import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4509.robot.Robot;


/**
 * Drive with a given speed until an interrupt is received
 * 
 * @author FRC Team 4509
 */
public class DriveUntilInterruptedCommand extends Command {
	
	private double speed, turn;
	
	public DriveUntilInterruptedCommand(double speed, double turn) {
		this.speed = speed;
		this.turn = turn;
		requires(Robot.drivingSubsystem);
	}

	protected void initialize() {  }

	protected void execute() {
		Robot.drivingSubsystem.drive(this.speed, this.turn);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.drivingSubsystem.stop();
	}

	protected void interrupted() {
		this.end();
	}

}
