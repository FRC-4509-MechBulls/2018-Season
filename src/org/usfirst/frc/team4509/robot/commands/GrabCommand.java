package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GrabCommand extends Command {

	double initTicks;
	
	public GrabCommand() {
		requires(Robot.drivingSubsystem);
		requires(Robot.grabberSubsystem);
		this.setTimeout(5);
	}
	
	protected void initialize() {
		this.initTicks = Robot.drivingSubsystem.getEncoderTicks();
	}

	protected void execute() {
		Robot.grabberSubsystem.set(-1);
		Robot.drivingSubsystem.drive(0.5);
	}
	
	protected boolean isFinished() {
		return Math.abs(this.initTicks - Robot.drivingSubsystem.getEncoderTicks()) > RobotMap.TICKS_PER_INCH * 12;
	}
	
	protected void end() {
		while(Math.abs(this.initTicks - Robot.drivingSubsystem.getEncoderTicks()) <= 1)
			Robot.drivingSubsystem.drive(-1);
		
		Robot.drivingSubsystem.stop();
		Robot.grabberSubsystem.stop();
	}
	
}
