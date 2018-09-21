package org.usfirst.frc.team4509.robot;

import org.usfirst.frc.team4509.robot.subsystems.DrivingSubsystem;
import org.usfirst.frc.team4509.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team4509.robot.subsystems.WinchSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

/*
 * The "main" class. Links RobotMap, OI, and subsystems.
 */
public class Robot extends IterativeRobot {

	// Subsystems
	public static final DrivingSubsystem drivingSubsystem = new DrivingSubsystem();
	public static final GrabberSubsystem grabberSubsystem = new GrabberSubsystem();
	public static final WinchSubsystem   winchSubsystem   = new WinchSubsystem();
	
	// OI = "Operator Input"
	public static OI oi;

	public static char[] gameData;

	@Override
	public void robotInit() {
		// Hardware init
		RobotMap.initDrive();
		RobotMap.initWinch();
		RobotMap.initGrabber();
		RobotMap.initCamera();
		
		// Inits the controller
		Robot.oi = new OI();
		Robot.oi.setTriggers();
	}
	
	@Override
	// Runs in the main loop regardless of robot status
	public void robotPeriodic() {}

	@Override
	public void disabledInit() {
		// Tell all motors to stop
		Robot.drivingSubsystem.stop();
		Robot.winchSubsystem.stop();
		Robot.grabberSubsystem.stop();
		Robot.drivingSubsystem.setDriveSpeedMode(DrivingSubsystem.DriveSpeedMode.Disabled); // Tell the DrivingSubsystem to multiply all speeds given to it by 0.
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
	// This runs every main loop during teleop
	public void teleopPeriodic() {
		Scheduler.getInstance().run(); // This runs the Scheduler, which executes methods in active commands.
	}

	@Override
	public void testPeriodic() {}
	
}