package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class DriveForwardCommand extends TimedCommand {
	
	public DriveForwardCommand(double timeout) {
		super(timeout);
	}

	// This method is called right before execute is run for the first time.
	protected void initialize() {
		Robot.drivingSubsystem.stop(); // make sure we aren't doing any weird stuff before we start driving.
	}

	// This method is called every time the main loop runs, until isFinished returns true.
	protected void execute() {
		//System.out.println(RobotMap.leftEncoder.get() + ", " + RobotMap.rightEncoder.get());
		Robot.drivingSubsystem.drive(0.5); // just drive.
	}

	// This method is called at the end of the command stop process, if isFinished returned true.
	protected void end() {
		Robot.drivingSubsystem.stop(); // make sure we don't keep driving after the command stops.
	}
	
}