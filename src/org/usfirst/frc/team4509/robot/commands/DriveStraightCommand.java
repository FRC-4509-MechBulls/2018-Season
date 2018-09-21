package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraightCommand extends Command {

	double timeout;
	
	public DriveStraightCommand(double timeout) {
		super(timeout);
		this.timeout = timeout; // store the timeout so we can calculate the time remaining later.
	}

	// This method is called right before execute is run for the first time.
	protected void initialize() {
		System.out.println("initialize();");
		
		Robot.drivingSubsystem.stop(); // make sure we aren't doing any weird stuff before we start driving.
	}

	// This method is called every time the main loop runs, until isFinished returns true.
	protected void execute() {
		System.out.println("execute();");
		
		Robot.drivingSubsystem.drive(1); // just drive.
	}
	
	// This method is run after execute has finished running every loop. If it returns true, the command stop process starts.
	protected boolean isFinished() {
		System.out.println("isFinished(); " + (this.timeout - this.timeSinceInitialized()) + " seconds left.");
		
		return this.isTimedOut(); // returns true if the command has run longer than the timeout.
	}

	// This method is called at the end of the command stop process, if isFinished returned true.
	protected void end() {
		System.out.println("end();");
		
		Robot.drivingSubsystem.stop(); // make sure we don't keep driving after the command stops.
	}
	
	// This method is called at the end of the command stop process, if the command was interrupted (isFinished did not return true, but the command was told to stop anyway).
	protected void interrupted() {
		System.out.println("interrupted();");
		
		this.end();
	}
	
}