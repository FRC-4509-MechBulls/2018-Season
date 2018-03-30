package org.usfirst.frc.team4509.robot.commands.auto;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;
import org.usfirst.frc.team4509.robot.commands.TimedDriveCommand;
import org.usfirst.frc.team4509.robot.commands.TimedGrabberCommand;
import org.usfirst.frc.team4509.robot.commands.TimedWinchCommand;
import org.usfirst.frc.team4509.robot.commands.TurnCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSwitchAuto extends CommandGroup {
	
	public RightSwitchAuto() {
		requires(Robot.drivingSubsystem);
		requires(Robot.grabberSubsystem);
		requires(Robot.winchSubsystem);
		addSequential(new TimedDriveCommand((46 / 12) * RobotMap.SECONDS_PER_FOOT, 1));
		addSequential(new TurnCommand(90));
		addSequential(new TimedDriveCommand((54 / 12) * RobotMap.SECONDS_PER_FOOT, 1));
		addSequential(new TurnCommand(-90));
		addSequential(new CommandGroup() { {
				addParallel(new TimedWinchCommand(1, 0.75));
				addSequential(new TimedDriveCommand((46 / 12) * RobotMap.SECONDS_PER_FOOT, 1));
		} });
		addSequential(new TimedGrabberCommand(2, -1));
	}
	
	protected void initialize() {
	}
	
}
