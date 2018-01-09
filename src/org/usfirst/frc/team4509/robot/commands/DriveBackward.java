package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class DriveBackward extends CustomCommandClass
{
	boolean isInterupted = false;
	
    public DriveBackward(float secondsUntilStop) 
    {
        requires(Robot.drivingSubsystem);
        setTimeout(secondsUntilStop);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    protected void runOnceWhenExecuted()
    {
    	super.runOnceWhenExecuted();
    	Robot.drivingSubsystem.getGyroAngle();
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	super.execute();
    	Robot.drivingSubsystem.drive(false, 4f);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	if(isTimedOut() || isInterupted)
    	{
    		isInterupted = false;
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.drivingSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	isInterupted = true;
    }
}
