package org.usfirst.frc.team4509.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CustomCommandClass extends Command 
{
	boolean isFirstTimeExecuted = true;

    public CustomCommandClass() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	isFirstTimeExecuted = true;
    }

    protected void runOnceWhenExecuted()
    {
    	isFirstTimeExecuted = false;
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	if(isFirstTimeExecuted)
    	{
    		runOnceWhenExecuted();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	isFirstTimeExecuted = true;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
