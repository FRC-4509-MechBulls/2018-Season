package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.command.TimedCommand;


public class GrabberOutputForSecondsCommand extends TimedCommand {

	public GrabberOutputForSecondsCommand(double timeout) {
		super(timeout);
		requires(Robot.grabberSubsystem);
	}
	
	public void execute() {
		RobotMap.grabberTalon.set(1);
	}
	
	public void end() {
		RobotMap.grabberTalon.set(0);
	}

}
