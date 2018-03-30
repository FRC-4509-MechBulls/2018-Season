package org.usfirst.frc.team4509.robot.commands.auto;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;
import org.usfirst.frc.team4509.robot.commands.TimedDriveCommand;
import org.usfirst.frc.team4509.robot.commands.TimedGrabberCommand;
import org.usfirst.frc.team4509.robot.commands.TimedWinchCommand;
import org.usfirst.frc.team4509.robot.commands.TurnCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScaleAuto extends CommandGroup {
	
	public ScaleAuto() {
		requires(Robot.drivingSubsystem);
		requires(Robot.grabberSubsystem);
		requires(Robot.winchSubsystem);
	}
	
	protected void initialize() {
		if(Robot.getStartingPosition() == 'C') {
			DriverStation.reportError("ScaleAuto was told to run, but the robot is in the center!", false);
		}
		int turnMultiplier;
		if(Robot.gameData[0] == 'L') {
			turnMultiplier = -1;
		} else if(Robot.gameData[0] == 'R') {
			turnMultiplier = 1;
		} else {
			DriverStation.reportError("ScaleAuto doesn't know which way to turn!", false);
			return;
		}
		if(Robot.gameData[0] == Robot.getStartingPosition()) {
			addSequential(new TimedDriveCommand((306 / 12) * RobotMap.SECONDS_PER_FOOT, 1));
			addSequential(new TimedWinchCommand(2, 0.75));
			addSequential(new TurnCommand(-90 * turnMultiplier));
			addSequential(new TimedGrabberCommand(2, -1));
		} else {
			addSequential(new TimedDriveCommand((215 / 12) * RobotMap.SECONDS_PER_FOOT, 1));
			addSequential(new TurnCommand(-90 * turnMultiplier));
			addSequential(new TimedDriveCommand((264 / 12) * RobotMap.SECONDS_PER_FOOT, 1));
			addSequential(new TurnCommand(90 * turnMultiplier));
			addSequential(new TimedDriveCommand((91 / 12) * RobotMap.SECONDS_PER_FOOT, 1));
			addSequential(new TimedWinchCommand(2, 0.75));
			addSequential(new TurnCommand(90 * turnMultiplier));
			addSequential(new TimedGrabberCommand(2, -1));
		}
	}
	
}
