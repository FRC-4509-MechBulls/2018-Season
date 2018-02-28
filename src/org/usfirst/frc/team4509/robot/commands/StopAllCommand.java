package org.usfirst.frc.team4509.robot.commands;


import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


/**
 * Keep all subsystems which contain talon controls stopped
 * 
 * @author FRC Team 4509
 */
public class StopAllCommand extends Command {
	
	public StopAllCommand() {
		this.setInterruptible(false);
		requires(Robot.drivingSubsystem);
		requires(Robot.winchSubsystem);
		requires(Robot.grabberSubsystem);
	}
	
	@Override
	protected void execute() {
		Robot.drivingSubsystem.stop();
		Robot.winchSubsystem.stop();
		Robot.grabberSubsystem.stop();
	}

	@Override
	protected boolean isFinished() { return false; }

}
