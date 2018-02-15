package org.usfirst.frc.team4509.robot.subsystems;


import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;
import org.usfirst.frc.team4509.robot.commands.DirectDriveCommand;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author FRC Team 4509
 */
public class DrivingSubsystem extends Subsystem {
	
    public void initDefaultCommand() {
    	this.setDefaultCommand(new DirectDriveCommand(Robot.oi.controller));
    }
    
    /**
     * @param leftYAxis used to drive the left side of the robot
     * @param rightYAxis used to drive the right side of the robot
     */
    public void tankDriving(double leftYAxis, double rightYAxis) {
		RobotMap.drive.tankDrive(leftYAxis, rightYAxis);
    }
    
    public void drive(double speed) {
    	RobotMap.drive.arcadeDrive(speed, 0);
    }
    
    public void drive(double zSpeed, double rotation, double xSpeed) {
    	RobotMap.drive.arcadeDrive(zSpeed, rotation);
    	RobotMap.driveTalonMiddle.set(xSpeed);
    }
    
    /**
     * 
     * @param direction the direction to turn. -1 is left, 1 is right
     */
    public void turn(int direction) {
		RobotMap.drive.arcadeDrive(0, direction);
    }
        
    public void stop() {
    	RobotMap.driveTalonFrontRight.set(0);
    	RobotMap.driveTalonFrontLeft.set(0);
    	RobotMap.driveTalonMiddle.set(0);
    }
    
    public int getEncoderTicks() {
    	return (int)((RobotMap.driveLeftEncoder.get() + RobotMap.driveRightEncoder.get()) / 2);
    }
    
    public int getEncoderDistance() {
    	return (int)((RobotMap.driveLeftEncoder.getDistance() + RobotMap.driveRightEncoder.getDistance()) / 2);
    }
}