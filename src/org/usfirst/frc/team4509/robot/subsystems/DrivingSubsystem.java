package org.usfirst.frc.team4509.robot.subsystems;


import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author FRC Team 4509
 */
public class DrivingSubsystem extends Subsystem {
	
    public void initDefaultCommand() {  }
    
    public double getGyroAngle() {
    	return RobotMap.navX.getAngle();
    }
    
    public void teleOpDriving(double leftYAxis, double rightYAxis, double rightXAxis) {
		RobotMap.drive.tankDrive(leftYAxis, rightYAxis);
		RobotMap.driveTalonMiddleLeft.set(rightXAxis);
    }
    
    public void drive(double speed) {
    	RobotMap.drive.arcadeDrive(speed, 0);
    }
    
    public void drive(double zSpeed, double rotation, double xSpeed) {
    	RobotMap.drive.arcadeDrive(zSpeed, rotation);
    	RobotMap.driveTalonMiddleLeft.set(xSpeed);
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
    	RobotMap.driveTalonMiddleLeft.set(0);
    	RobotMap.driveTalonMiddleRight.set(0);
    	SmartDashboard.putString("DB/String 0", "Stopping");
    }
}