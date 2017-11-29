package org.usfirst.frc.team4509.robot.subsystems;


import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// These are the talons that are created in the robot map. Instantiating them like this allows us to keep track of just one set of talons,
	// rather than instantiating a new one in each file, and hoping that the instructions don't contradict each other.
	CANTalon TalonDriveFrontRight = RobotMap.TalonDriveFrontRight; // The front right driving Talon
	CANTalon TalonDriveBackRight = RobotMap.TalonDriveBackRight; // The back right driving Talon
	CANTalon TalonDriveFrontLeft = RobotMap.TalonDriveFrontLeft; // The front left driving Talon
	CANTalon TalonDriveBackLeft = RobotMap.TalonDriveBackLeft; // The back left driving Talon 
	ADXRS450_Gyro Gyro = RobotMap.Gyro;
	
	double angle = 0;	
	float leftSpeed = 3;
	float rightSpeed = -3;
	boolean isChanged = false;
	public boolean isFinishedTurning = false;
	
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void getGyroAngle()
    {
    	angle = Gyro.getAngle();
    }
    
    public void drive(boolean goForward, float speed)
    {
    	if(goForward)
    	{
    		leftSpeed = speed;
    		rightSpeed = -speed;
    		while(Gyro.getAngle() - angle < -.25d) // If the angle is too much to the left (.5 degree margin of error allowed)
			{
				if(isChanged) // Check isChanged. This makes sure that the drive values don't change too drastically.
				{
					leftSpeed += 1;
					rightSpeed += 1;
					isChanged = false;
				}
				
				// Set the talons' speed based on the corrective values
				TalonDriveBackLeft.set(leftSpeed);
				TalonDriveFrontLeft.set(leftSpeed);
				TalonDriveBackRight.set(rightSpeed);
				TalonDriveFrontRight.set(rightSpeed);
			}
			while(Gyro.getAngle() - angle > .25d) // If the angle is too far to the right (.5 degree margin of error allowed)
			{
				//Slow down right side and speed up left
				if(isChanged) // Check isChanged. This makes sure that the drive values don't change too drastically.
				{
					leftSpeed -= 1;
					rightSpeed-= 1;
					isChanged = false;
				}
				
				// Set the talons' speed based on the corrective values
				TalonDriveBackLeft.set(leftSpeed);
				TalonDriveFrontLeft.set(leftSpeed);
				TalonDriveBackRight.set(rightSpeed);
				TalonDriveFrontRight.set(rightSpeed);

			}
			// Set the speeds back to normal. Called when the robot's angle is within the margin of error
			leftSpeed = 3;
			rightSpeed = -3;
			isChanged = true;
			TalonDriveBackLeft.set(leftSpeed);
			TalonDriveFrontLeft.set(leftSpeed);
			TalonDriveBackRight.set(rightSpeed);
			TalonDriveFrontRight.set(rightSpeed);
    	}
    	else
    	{
    		leftSpeed = -speed;
    		rightSpeed = speed;
    		while(Gyro.getAngle() - angle < -.5d) // If the angle is too much to the left (.5 degree margin of error allowed)
			{
				if(isChanged) // Check isChanged. This makes sure that the drive values don't change too drastically.
				{
					leftSpeed += 1;
					rightSpeed += 1;
					isChanged = false;
				}
				TalonDriveBackLeft.set(leftSpeed);
				TalonDriveFrontLeft.set(leftSpeed);
				TalonDriveBackRight.set(rightSpeed);
				TalonDriveFrontRight.set(rightSpeed);
			}
			while(Gyro.getAngle() - angle > .5d) // If the angle is too much to the right (.5 degree margin of error allowed)
			{
				if(isChanged) // Check isChanged. This makes sure that the drive values don't change too drastically.
				{
					leftSpeed -= 1;
					rightSpeed -= 1;
					isChanged = false;
				}
				TalonDriveBackLeft.set(leftSpeed);
				TalonDriveFrontLeft.set(leftSpeed);
				TalonDriveBackRight.set(rightSpeed);
				TalonDriveFrontRight.set(rightSpeed);
			}
			// Set the speeds back to normal. Called when the robot's angle is within the margin of error
			leftSpeed = -3;
			rightSpeed = 3;
			isChanged = true;
			TalonDriveBackLeft.set(leftSpeed);
			TalonDriveFrontLeft.set(leftSpeed);
			TalonDriveBackRight.set(rightSpeed);
			TalonDriveFrontRight.set(rightSpeed);
    	}
    }
    
    public void turn(boolean TurnRight, double Degrees, double Speed)
    {
    	isFinishedTurning = false;
    	if(!TurnRight)
    	{
    		Degrees = Degrees * -1;
    	}
    	double startingAngle = Gyro.getAngle(); // The angle that the robot starts at
		double targetAngle = Gyro.getAngle() + Degrees; // The angle the robot wants to end at
		while(startingAngle + Degrees -.25d > Gyro.getAngle() || startingAngle + Degrees + .25d < Gyro.getAngle()) // if the angle isn't the correct angle
		{
			if(Gyro.getAngle() - targetAngle > .25f) // If the current angle is too far right
			{
				TalonDriveBackLeft.set(-Speed);
				TalonDriveFrontLeft.set(-Speed);
				TalonDriveBackRight.set(-Speed);
				TalonDriveFrontRight.set(-Speed);
			}
			else if(Gyro.getAngle() - targetAngle < -.25f) // If the current angle is too far left
			{
				TalonDriveBackLeft.set(Speed);
				TalonDriveFrontLeft.set(Speed);
				TalonDriveBackRight.set(Speed);
				TalonDriveFrontRight.set(Speed);
			}
		}
		
		isFinishedTurning = true;
		Stop();
    }
    
    public void Stop()
    {
    	TalonDriveFrontRight.set(0);
    	TalonDriveBackRight.set(0);
    	TalonDriveFrontLeft.set(0);
    	TalonDriveBackLeft.set(0);
    	SmartDashboard.putString("DB/String 0", "Stopping");
    }
}