package org.usfirst.frc.team4509.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4509.robot.commands.*;
import org.usfirst.frc.team4509.robot.subsystems.*;

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

	public static final DrivingSubsystem drivingSubsystem = new DrivingSubsystem();
	public static final ArduinoSubsystem testArduinoSubsystem = new ArduinoSubsystem(RobotMap.arduino);

	public static OI oi;

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
		//RobotMap.initArduino();
		
		Robot.oi = new OI();
		// chooser.addDefault("Default Auto", new TurnRight(90));
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		this.drivingSubsystem.stop();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		Robot.gameData = DriverStation.getInstance().getGameSpecificMessage().toCharArray();
		autonomousCommand = chooser.getSelected();
		Scheduler.getInstance().add(new DriveForTicksCommand(50));

		// schedule the autonomous command (example)
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
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, removeblue
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		//Scheduler.getInstance().add(new DriveUntilInterruptedCommand(Robot.oi.controller.getDrive(), Robot.oi.controller.getTurn(), Robot.oi.controller.getSlide()));
		if(this.oi.controller.getDrive() > -1)
			Scheduler.getInstance().add(new DriveForTicksCommand(10));
		Scheduler.getInstance().run();
		//SmartDashboard.putNumber("Arduino", (char)this.testArduinoSubsystem.readByte());
	}

	@Override
	public void testPeriodic() {  }
	
}