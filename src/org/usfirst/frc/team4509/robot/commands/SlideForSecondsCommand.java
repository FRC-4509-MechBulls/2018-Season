package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;


/**
 * Slides at the given speed for the given number of seconds.
 * 
 * @author FRC Team 4509
 */
public class SlideForSecondsCommand extends TimedCommand {

	double speed;
	
	public SlideForSecondsCommand(double speed, double timeout) {
		super(timeout);
		requires(Robot.drivingSubsystem);
		this.speed = speed;
	}
	
	public void execute() {
		Robot.drivingSubsystem.drive(0, 0, speed);
	}
	
	public void end() {
		Robot.drivingSubsystem.stop();
	}

}
