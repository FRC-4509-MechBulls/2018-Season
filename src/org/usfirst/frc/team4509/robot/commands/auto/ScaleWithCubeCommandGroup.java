package org.usfirst.frc.team4509.robot.commands.auto;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.commands.DriveForFeetCommand;
import org.usfirst.frc.team4509.robot.commands.GrabberForSecondsCommand;
import org.usfirst.frc.team4509.robot.commands.TurnCommand;
import org.usfirst.frc.team4509.robot.commands.WinchForSecondsCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Command Group to put a cube on the scale.
 */
public class ScaleWithCubeCommandGroup extends CommandGroup {

	public ScaleWithCubeCommandGroup() {
		requires(Robot.drivingSubsystem);
		requires(Robot.winchSubsystem);
		requires(Robot.grabberSubsystem);
	}
	
	protected void initialize() {
		addSequential(new DriveForFeetCommand(0.5));
		addSequential(new DriveForFeetCommand(-0.5));
		if(Robot.getStartingPosition() != 'C') {
			addSequential(new DriveForFeetCommand(19.061));
			addSequential(new TurnCommand(90 * -Robot.startPosition));
			addSequential(new DriveForFeetCommand(3.96875));
			if(Robot.getStartingPosition() == Robot.gameData[1]) {
				addSequential(new TurnCommand(90 * Robot.startPosition));
			} else {
				addSequential(new DriveForFeetCommand(11.9375));
				addSequential(new TurnCommand(90 * Robot.startPosition));
			}
			addSequential(new WinchForSecondsCommand(1, 3)); // TODO
			addSequential(new DriveForFeetCommand(5.910));
			addSequential(new GrabberForSecondsCommand(1, 1)); // TODO
			addSequential(new DriveForFeetCommand(-5.910));
			addSequential(new WinchForSecondsCommand(-1, 3)); // TODO
		}
	}
	
}
