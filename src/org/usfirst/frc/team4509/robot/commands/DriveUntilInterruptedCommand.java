package org.usfirst.frc.team4509.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.GenericHID;

import org.usfirst.frc.team4509.robot.Robot;

/**
 *
 */
public class DriveUntilInterruptedCommand extends Command {
	
	boolean isInterupted = false;
	
    public DriveUntilInterruptedCommand() {
        requires(Robot.drivingSubsystem);
    }
    
    protected void initialize() {
    }
    
    protected void execute() {
    	//Robot.drivingSubsystem.teleOpDriving(Robot.oi.controller.getY(GenericHID.Hand.kLeft),
    	//		                             Robot.oi.controller.getY(GenericHID.Hand.kRight),
    	//		                             Robot.oi.controller.getX(GenericHID.Hand.kRight));
    	Robot.drivingSubsystem.teleOpArcade((-1 * Robot.oi.controller.getTriggerAxis(GenericHID.Hand.kLeft)) + Robot.oi.controller.getTriggerAxis(GenericHID.Hand.kRight),
    	                                    Robot.oi.controller.getX(GenericHID.Hand.kRight));
    }

    protected boolean isFinished() {
    	return this.isInterupted;
    }

    protected void end() {
    	Robot.drivingSubsystem.stop();
    }

    protected void interrupted() {
    	this.isInterupted = true;
    }
}
