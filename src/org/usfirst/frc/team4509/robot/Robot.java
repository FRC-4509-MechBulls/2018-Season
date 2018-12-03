package org.usfirst.frc.team4509.robot;

import org.usfirst.frc.team4509.robot.motionprofiling.*;
import org.usfirst.frc.team4509.robot.subsystems.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * The "main" class. Links RobotMap, OI, and subsystems.
 */
public class Robot extends IterativeRobot {
	
	public enum AUTO_START_POSITION { LEFT, MIDDLE, RIGHT }
	public enum AUTO_GOAL { LINE, SWITCH, SCALE }

	// Subsystems
	public static final DrivingSubsystem drivingSubsystem = new DrivingSubsystem();
	public static final GrabberSubsystem grabberSubsystem = new GrabberSubsystem();
	public static final WinchSubsystem   winchSubsystem   = new WinchSubsystem();
	public static final MotionProfilingSubsystem motionProfilingSubsystem = new MotionProfilingSubsystem();
	
	// OI = "Operator Input"
	public static OI oi;

	public static char[] gameData;
	
	Command autoCommand;
	SendableChooser<AUTO_START_POSITION> autoStartPositionChooser;
	SendableChooser<AUTO_GOAL> autoGoalChooser;

	@Override
	public void robotInit() {
		// Hardware init
		RobotMap.initDrive();
		RobotMap.initWinch();
		RobotMap.initGrabber();
		RobotMap.initCamera();
		RobotMap.initSensors();
		
		// Inits the controller
		Robot.oi = new OI();
		Robot.oi.setTriggers();
		
		SmartDashboard.putData("Scheduler", Scheduler.getInstance());
		SmartDashboard.putData("Driving", Robot.drivingSubsystem);
		SmartDashboard.putData("Grabber", Robot.grabberSubsystem);
		SmartDashboard.putData("Winch",   Robot.winchSubsystem);
		SmartDashboard.putData("Motion Profiling", Robot.motionProfilingSubsystem);
		
		this.autoStartPositionChooser = new SendableChooser<AUTO_START_POSITION>();
		this.autoStartPositionChooser.addObject("Left",    AUTO_START_POSITION.LEFT);
		this.autoStartPositionChooser.addDefault("Middle", AUTO_START_POSITION.MIDDLE);
		this.autoStartPositionChooser.addObject("Right",   AUTO_START_POSITION.RIGHT);
		SmartDashboard.putData("Auto Start Chooser", this.autoStartPositionChooser);
		
		this.autoGoalChooser = new SendableChooser<AUTO_GOAL>();
		this.autoGoalChooser.addDefault("Line",  AUTO_GOAL.LINE);
		this.autoGoalChooser.addObject("Switch", AUTO_GOAL.SWITCH);
		this.autoGoalChooser.addObject("Scale",  AUTO_GOAL.SCALE);
		SmartDashboard.putData("Auto Goal Chooser", this.autoGoalChooser);
		
		Robot.motionProfilingSubsystem.addProfile(MotionProfileStorage.getStopProfile());
		Robot.motionProfilingSubsystem.addProfile(MotionProfileStorage.getProfile("turn_left_90"));
		
		SmartDashboard.putData("Next Profile", new SelectNextProfileCommand());
		SmartDashboard.putData("Last Profile", new SelectLastProfileCommand());
		
		SmartDashboard.putString("Profile to load", "");
		SmartDashboard.putData("Load Profile", new LoadProfileCommand());
	}
	
	@Override
	public void robotPeriodic() {}

	@Override
	public void disabledInit() {
		Robot.drivingSubsystem.setDriveSpeedMode(DrivingSubsystem.DriveSpeedMode.Disabled);
		Robot.drivingSubsystem.stop();
		Robot.winchSubsystem.stop();
		Robot.grabberSubsystem.stop();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		Robot.drivingSubsystem.setDriveSpeedMode(DrivingSubsystem.DriveSpeedMode.Auto);
		Robot.gameData = DriverStation.getInstance().getGameSpecificMessage().toCharArray();
		if(autoCommand != null) {
			autoCommand.start();
		}
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
	public void testInit() {}
	
	@Override
	public void testPeriodic() {}
	
}