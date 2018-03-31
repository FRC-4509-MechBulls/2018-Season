package org.usfirst.frc.team4509.robot;

import org.usfirst.frc.team4509.robot.commands.DriveForSecondsTimedCommand;
import org.usfirst.frc.team4509.robot.commands.auto.IdiotAutoCommandGroup;
import org.usfirst.frc.team4509.robot.commands.auto.LeftSwitchAuto;
import org.usfirst.frc.team4509.robot.subsystems.DrivingSubsystem;
import org.usfirst.frc.team4509.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team4509.robot.subsystems.WinchSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


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

	//public static final CameraSubsystem  cameraSubsystem  = new CameraSubsystem();
	public static final DrivingSubsystem drivingSubsystem = new DrivingSubsystem();
	public static final GrabberSubsystem grabberSubsystem = new GrabberSubsystem();
	public static final WinchSubsystem   winchSubsystem   = new WinchSubsystem();

	public static boolean hasAutoRun = false;
	
	public static OI oi;
	
	public static VisualCube[] cubes;

	public static char[] gameData;
	public static int startPosition = 0;
	
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	SendableChooser<Integer> sideChooser = new SendableChooser<>();
	
	Command a = new DriveForSecondsTimedCommand(1, 15 * RobotMap.SECONDS_PER_FOOT);

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override	public void robotInit() {
		RobotMap.initDrive();
		RobotMap.initSensors();
		RobotMap.initWinch();
		RobotMap.initGrabber();
		//RobotMap.initArduino();
		RobotMap.initCamera();
		
		//Robot.cameraSubsystem.setPort(RobotMap.arduino);
		
		Robot.oi = new OI();
		Robot.oi.setTriggers();

		chooser.addObject( "None",   null);
		chooser.addObject( "Idiot",  new IdiotAutoCommandGroup());
		chooser.addDefault("Switch (Center)", new LeftSwitchAuto());
		SmartDashboard.putData("Auto Mode", chooser);
		
		sideChooser.addObject( "Left",  -1);
		sideChooser.addDefault("Center", 0);
		sideChooser.addObject( "Right",  1);
		SmartDashboard.putData("Starting Side", sideChooser);
	}
	
	@Override
	public void robotPeriodic() {}

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
		Robot.drivingSubsystem.setDriveSpeedMode(DrivingSubsystem.DriveSpeedMode.Disabled);
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
		Robot.drivingSubsystem.setDriveSpeedMode(DrivingSubsystem.DriveSpeedMode.Auto);

		do {
			Robot.gameData = DriverStation.getInstance().getGameSpecificMessage().toCharArray();
		} while(Robot.gameData == null);

		SmartDashboard.putString("Game Data", String.valueOf(Robot.gameData));

		if(sideChooser.getSelected() != null)
			Robot.startPosition = sideChooser.getSelected();
		else
			Robot.startPosition = 0;

		//autonomousCommand = chooser.getSelected();
		//if(autonomousCommand != null)
			//autonomousCommand.start();
		/*if(Robot.gameData[0] == 'L')
			(new LeftSwitchAuto()).start();
		else if(Robot.gameData[0] == 'R')
			(new RightSwitchAuto()).start();*/
		a.start();
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
		Robot.drivingSubsystem.setDriveSpeedMode(DrivingSubsystem.DriveSpeedMode.TeleOp);
		if(autonomousCommand != null)
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
	
	/**
	 * Returns a character corresponding to the robot's start position
	 * 
	 * @return 'L', 'C', or 'R'
	 */
	public static char getStartingPosition() {
		if(Robot.startPosition == -1)
			return 'L';
		else if(Robot.startPosition == 1)
			return 'R';
		return 'C';
	}
	
}