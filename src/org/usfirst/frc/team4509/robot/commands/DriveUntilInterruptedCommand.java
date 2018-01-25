package org.usfirst.frc.team4509.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4509.robot.Robot;

/**
 *
 */
public class DriveUntilInterruptedCommand extends Command {
	
	private double speed, turn, slide;
	
    public DriveUntilInterruptedCommand(double speed, double turn, double slide) {
    	this.speed = speed;
    	this.turn = turn;
    	this.slide = slide;
        requires(Robot.drivingSubsystem);
    }
    
    protected void initialize() {  }
    
    protected void execute() {
    	Robot.drivingSubsystem.drive(this.speed, this.turn, this.slide);
    }

    protected boolean isFinished() {
    	return false;
    }

    protected void end() {
    	Robot.drivingSubsystem.stop();
    }

    protected void interrupted() {  }
    
}
