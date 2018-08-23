package org.usfirst.frc.team4509.robot;

import org.usfirst.frc.team4509.robot.subsystems.DrivingSubsystem;
import org.usfirst.frc.team4509.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team4509.robot.subsystems.WinchSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;


public class Robot extends IterativeRobot {

	public static final DrivingSubsystem drivingSubsystem = new DrivingSubsystem();
	public static final GrabberSubsystem grabberSubsystem = new GrabberSubsystem();
	public static final WinchSubsystem   winchSubsystem   = new WinchSubsystem();
	
	public static OI oi;

	public static char[] gameData;

	@Override	public void robotInit() {
		RobotMap.initDrive();
		RobotMap.initWinch();
		RobotMap.initGrabber();
		RobotMap.initCamera();
		
		Robot.oi = new OI();
		Robot.oi.setTriggers();
	}
	
	@Override
	public void robotPeriodic() {}

	@Override
	public void disabledInit() {
		Robot.drivingSubsystem.stop();
		Robot.winchSubsystem.stop();
		Robot.grabberSubsystem.stop();
		Robot.drivingSubsystem.setDriveSpeedMode(DrivingSubsystem.DriveSpeedMode.Disabled);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		Robot.drivingSubsystem.setDriveSpeedMode(DrivingSubsystem.DriveSpeedMode.Auto);
	}
	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		Robot.drivingSubsystem.setDriveSpeedMode(DrivingSubsystem.DriveSpeedMode.TeleOp);
	}
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {}
	
}