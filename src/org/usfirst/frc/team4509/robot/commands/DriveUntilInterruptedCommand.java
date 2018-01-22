package org.usfirst.frc.team4509.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;

/**
 *
 */
public class DriveUntilInterruptedCommand extends Command {
	
	private double speed, turn;
	
    public DriveUntilInterruptedCommand(double speed, double turn) {
    	this.speed = speed;
    	this.turn = turn;
        requires(Robot.drivingSubsystem);
    }
    
    protected void initialize() {
    }
    
    protected void execute() {
    	RobotMap.drive.arcadeDrive(this.speed, this.turn);
    }

    protected boolean isFinished() {
    	return false;
    }

    protected void end() {
    	Robot.drivingSubsystem.stop();
    }

    protected void interrupted() {
    	//this.end();
    }
}
