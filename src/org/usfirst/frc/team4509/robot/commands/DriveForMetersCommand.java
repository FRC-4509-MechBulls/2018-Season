package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author FRC Team 4509
 */
public class DriveForMetersCommand extends Command {

	boolean isInterrupted = false;
	double distance; // in meters
	
    public DriveForMetersCommand(double distance) {
        requires(Robot.drivingSubsystem);
        this.distance = distance * RobotMap.TICKS_PER_METER;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.driveLeftEncoder.reset();
    	RobotMap.driveRightEncoder.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(Robot.drivingSubsystem.getEncoderTicks()) < Math.abs(this.distance)) {
    		Robot.drivingSubsystem.drive(0.25 * (this.distance / Math.abs(this.distance)));
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isInterrupted || Math.abs(Robot.drivingSubsystem.getEncoderTicks() - this.distance) < 0.5;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivingSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.isInterrupted = true;
    	this.end();
    }

}
