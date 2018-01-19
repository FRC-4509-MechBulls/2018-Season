package org.usfirst.frc.team4509.robot.subsystems;


import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DrivingSubsystem extends Subsystem {
	
	double angle = 0;	
	double leftSpeed = 3;
	double rightSpeed = -3;
	boolean isChanged = false;
	public boolean isFinishedTurning = false;
	
    public void initDefaultCommand() {
    }
    
    public void getGyroAngle() {
    	this.angle = RobotMap.navX.getAngle();
    }
    
    public void teleOpDriving(double leftYAxis, double rightYAxis, double rightXAxis) {
		RobotMap.drive.tankDrive(leftYAxis, rightYAxis);
		RobotMap.driveTalonMiddleLeft.set(rightXAxis);
    }
    
    public void drive(double speed) {
    	RobotMap.drive.arcadeDrive(speed, 0);
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
    	RobotMap.driveTalonBackRight.set(0);
    	RobotMap.driveTalonFrontLeft.set(0);
    	RobotMap.driveTalonBackLeft.set(0);
    	SmartDashboard.putString("DB/String 0", "Stopping");
    }
}