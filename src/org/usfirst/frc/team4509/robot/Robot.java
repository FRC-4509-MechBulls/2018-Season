package org.usfirst.frc.team4509.robot;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4509.robot.subsystems.*;
import org.usfirst.frc.team4509.robot.commands.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 
 * @author FRC Team 4509
 */
public class Robot extends IterativeRobot {

	public static final CameraSubsystem  cameraSubsystem  = new CameraSubsystem();
	public static final DrivingSubsystem drivingSubsystem = new DrivingSubsystem();
	public static final GrabberSubsystem grabberSubsystem = new GrabberSubsystem();
	public static final WinchSubsystem   winchSubsystem   = new WinchSubsystem();

	public static OI oi;
	
	public static Cube[] cubes;

	public static char[] gameData;
	
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.initDrive();
		RobotMap.initSensors();
		RobotMap.initWinch();
		RobotMap.initGrabber();
		RobotMap.initArduino();
		RobotMap.initCamera();
		
		Robot.cameraSubsystem.setPort(RobotMap.arduino);
		
		Robot.oi = new OI();
		Robot.oi.setTriggers();
		
		chooser.addDefault("Pass the line (Default)", new DriveForFeetCommand(15));
		SmartDashboard.putData("Auto Mode", chooser);
		
		SmartDashboard.putData("Scheduler", Scheduler.getInstance());
		
		SmartDashboard.putData("Camera Subsystem",  Robot.cameraSubsystem);
		SmartDashboard.putData("Driving Subsystem", Robot.drivingSubsystem);
		SmartDashboard.putData("Winch Subsystem",   Robot.winchSubsystem);
		SmartDashboard.putData("Grabber Subsystem", Robot.grabberSubsystem);
	}
	
	@Override
	public void robotPeriodic() {
		SmartDashboard.putNumber("Left",    RobotMap.leftDriveTalon.get()  *  2);
		SmartDashboard.putNumber("Middle",  RobotMap.slideDriveTalon.get() * -2);
		SmartDashboard.putNumber("Right",   RobotMap.rightDriveTalon.get() *  2);
		SmartDashboard.putNumber("Winch",   RobotMap.winchTalon.get()   * 2);
		SmartDashboard.putNumber("Grabber", RobotMap.grabberTalon.get() * 2);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Robot.drivingSubsystem.stop();
		Robot.winchSubsystem.stop();
		Robot.grabberSubsystem.stop();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard.
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings and commands.
	 */
	@Override
	public void autonomousInit() {
		Robot.gameData = DriverStation.getInstance().getGameSpecificMessage().toCharArray();
		autonomousCommand = chooser.getSelected();

		// schedule the autonomous command
		if(autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {  }
	
}