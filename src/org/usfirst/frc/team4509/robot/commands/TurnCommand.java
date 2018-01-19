package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A command to turn the robot an amount given in degrees 
 * @author FRC Team 4509
 *
 */
public class TurnCommand extends Command {

	double oldDegrees, turnDegrees;
	boolean isFinished = false;

    public TurnCommand(double turnDegrees) {
        requires(Robot.drivingSubsystem);
        this.oldDegrees = RobotMap.navX.getAngle();
        this.turnDegrees = turnDegrees;
    }

    protected void initialize() {
    }
    
    protected void execute() {
    	if(!this.isFinished) {
    		if(this.turnDegrees + this.oldDegrees - RobotMap.navX.getAngle() > 0)
    			Robot.drivingSubsystem.turn(1, 1);
    		else
    			Robot.drivingSubsystem.turn(-1, 1);
    	}
    }

    protected boolean isFinished() {
        return Math.abs(this.turnDegrees + this.oldDegrees - RobotMap.navX.getAngle()) < 0.5;
    }

    protected void end() {
    	Robot.drivingSubsystem.stop();
    }

    protected void interrupted() {
    }
    
}
