package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnRight extends CustomCommandClass 
{
	double DegreesToTurn = 0;

    public TurnRight(double Degrees) 
    {
        requires(Robot.driveTrainSubsystem);
        DegreesToTurn = Degrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    protected void runOnceWhenExecuted()
    {
    	super.runOnceWhenExecuted();
    	Robot.driveTrainSubsystem.turn(true, DegreesToTurn, 3.5d);
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	super.execute();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return Robot.driveTrainSubsystem.isFinishedTurning;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
