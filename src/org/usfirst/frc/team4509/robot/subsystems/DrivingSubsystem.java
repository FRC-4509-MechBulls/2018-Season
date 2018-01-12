package org.usfirst.frc.team4509.robot.subsystems;


import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 *
 */
public class DrivingSubsystem extends Subsystem {
	
	double angle = 0;	
	float leftSpeed = 3;
	float rightSpeed = -3;
	boolean isChanged = false;
	public boolean isFinishedTurning = false;
	
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void getGyroAngle() {
    	this.angle = RobotMap.navX.getAngle();
    }
    
    public void teleOpDriving(double leftYAxis, double rightYAxis, double rightXAxis) {
		RobotMap.drive.tankDrive(leftYAxis * -1, rightYAxis);
		RobotMap.driveTalonMiddleLeft.set(RobotMap.rightJoystick.getX());
    }
    
    public void drive(boolean goForward, float speed) {
    	if(goForward) {
    		leftSpeed = speed;
    		rightSpeed = -speed;
    		while(RobotMap.navX.getAngle() - angle < -.25d) // If the angle is too much to the left (.5 degree margin of error allowed)
			{
				if(isChanged) // Check isChanged. This makes sure that the drive values don't change too drastically.
				{
					leftSpeed += 1;
					rightSpeed += 1;
					isChanged = false;
				}
				
				// Set the talons' speed based on the corrective values
				RobotMap.driveTalonBackLeft.set(leftSpeed);
				RobotMap.driveTalonFrontLeft.set(leftSpeed);
				RobotMap.driveTalonBackRight.set(rightSpeed);
				RobotMap.driveTalonFrontRight.set(rightSpeed);
			}
			while(RobotMap.navX.getAngle() - angle > .25d) // If the angle is too far to the right (.5 degree margin of error allowed)
			{
				//Slow down right side and speed up left
				if(isChanged) // Check isChanged. This makes sure that the drive values don't change too drastically.
				{
					leftSpeed -= 1;
					rightSpeed-= 1;
					isChanged = false;
				}
				
				// Set the talons' speed based on the corrective values
				RobotMap.driveTalonBackLeft.set(leftSpeed);
				RobotMap.driveTalonFrontLeft.set(leftSpeed);
				RobotMap.driveTalonBackRight.set(rightSpeed);
				RobotMap.driveTalonFrontRight.set(rightSpeed);

			}
			// Set the speeds back to normal. Called when the robot's angle is within the margin of error
			leftSpeed = 3;
			rightSpeed = -3;
			isChanged = true;
			RobotMap.driveTalonBackLeft.set(leftSpeed);
			RobotMap.driveTalonFrontLeft.set(leftSpeed);
			RobotMap.driveTalonBackRight.set(rightSpeed);
			RobotMap.driveTalonFrontRight.set(rightSpeed);
    	} else {
    		leftSpeed = -speed;
    		rightSpeed = speed;
    		while(RobotMap.navX.getAngle() - angle < -.5d) // If the angle is too much to the left (.5 degree margin of error allowed)
			{
				if(isChanged) // Check isChanged. This makes sure that the drive values don't change too drastically.
				{
					leftSpeed += 1;
					rightSpeed += 1;
					isChanged = false;
				}
				RobotMap.driveTalonBackLeft.set(leftSpeed);
				RobotMap.driveTalonFrontLeft.set(leftSpeed);
				RobotMap.driveTalonBackRight.set(rightSpeed);
				RobotMap.driveTalonFrontRight.set(rightSpeed);
			}
			while(RobotMap.navX.getAngle() - angle > .5d) // If the angle is too much to the right (.5 degree margin of error allowed)
			{
				if(isChanged) // Check isChanged. This makes sure that the drive values don't change too drastically.
				{
					leftSpeed -= 1;
					rightSpeed -= 1;
					isChanged = false;
				}
				RobotMap.driveTalonBackLeft.set(leftSpeed);
				RobotMap.driveTalonFrontLeft.set(leftSpeed);
				RobotMap.driveTalonBackRight.set(rightSpeed);
				RobotMap.driveTalonFrontRight.set(rightSpeed);
			}
			// Set the speeds back to normal. Called when the robot's angle is within the margin of error
			leftSpeed = -3;
			rightSpeed = 3;
			isChanged = true;
			RobotMap.driveTalonBackLeft.set(leftSpeed);
			RobotMap.driveTalonFrontLeft.set(leftSpeed);
			RobotMap.driveTalonBackRight.set(rightSpeed);
			RobotMap.driveTalonFrontRight.set(rightSpeed);
    	}
    }
    
    /**
     * 
     * @param direction the direction to turn. -1 is left, 1 is right
     * @param speed
     */
    public void turn(int direction, double speed) {
		RobotMap.driveTalonBackLeft.set(direction * speed);
		RobotMap.driveTalonFrontLeft.set(direction * speed);
		RobotMap.driveTalonBackRight.set(-direction * speed);
		RobotMap.driveTalonFrontRight.set(-direction * speed);
    }
    
    public void stop() {
    	RobotMap.driveTalonFrontRight.set(0);
    	RobotMap.driveTalonBackRight.set(0);
    	RobotMap.driveTalonFrontLeft.set(0);
    	RobotMap.driveTalonBackLeft.set(0);
    	SmartDashboard.putString("DB/String 0", "Stopping");
    }
}