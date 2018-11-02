package org.usfirst.frc.team4509.robot;

import org.usfirst.frc.team4509.robot.MotionProfiling.*;
import org.usfirst.frc.team4509.robot.subsystems.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * The "main" class. Links RobotMap, OI, and subsystems.
 */
public class Robot extends IterativeRobot {

	// Subsystems
	public static final DrivingSubsystem drivingSubsystem = new DrivingSubsystem();
	public static final GrabberSubsystem grabberSubsystem = new GrabberSubsystem();
	public static final WinchSubsystem   winchSubsystem   = new WinchSubsystem();
	public static final MotionProfilingSubsystem motionProfilingSubsystem = new MotionProfilingSubsystem();
	
	// OI = "Operator Input"
	public static OI oi;

	public static char[] gameData;
	
	Command autoCommand;
	
	MotionProfile profile;

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
		
		//autoCommand = new AutoTestCommandGroup();
		
		Robot.motionProfilingSubsystem.addProfile(MotionProfileStorage.getStopProfile());
	}
	
	@Override
	// Runs in the main loop regardless of robot status
	public void robotPeriodic() {}

	@Override
	public void disabledInit() {
		Robot.drivingSubsystem.setDriveSpeedMode(DrivingSubsystem.DriveSpeedMode.Disabled);
		// Tell all motors to stop
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
	// This runs every main loop during teleop
	public void teleopPeriodic() {
		Scheduler.getInstance().run(); // This runs the Scheduler, which executes methods in active commands.
	}

	@Override
	public void testPeriodic() {}
	
}