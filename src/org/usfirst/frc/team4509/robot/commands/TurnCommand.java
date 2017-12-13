package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class TurnCommand extends Command {

	double oldDegrees, targetDegrees;
	boolean isFinished = false;

    public TurnCommand(double targetDegrees) {
        requires(Robot.drivingSubsystem);
        this.oldDegrees = RobotMap.navX.getAngle();
        this.targetDegrees = targetDegrees;
    }

    protected void initialize() {
    }
    
    protected void execute() {
    	if(!this.isFinished) {
    		if((this.targetDegrees - this.oldDegrees) - RobotMap.navX.getAngle() > 0)
    		Robot.drivingSubsystem.turn(1, 1);
    	}
    }

    protected boolean isFinished() {
        return Math.abs((this.targetDegrees - this.oldDegrees) - RobotMap.navX.getAngle()) < 0.5;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
