package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;


/**
 * Run the winch at the given speed for a given number of seconds.
 * 
 * @author FRC Team 4509
 */
public class TimedWinchCommand extends TimedCommand {

	double speed;
	
	public TimedWinchCommand(double timeout, double speed) {
		super(timeout);
		requires(Robot.winchSubsystem);
		this.speed = speed;
	}
	
	public void execute() {
		Robot.winchSubsystem.set(this.speed);
	}
	
	public void end() {
		Robot.winchSubsystem.stop();
	}

}
