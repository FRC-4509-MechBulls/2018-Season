package org.usfirst.frc.team4509.robot.commands.auto;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.commands.DriveForFeetCommand;
import org.usfirst.frc.team4509.robot.commands.GrabCommand;
import org.usfirst.frc.team4509.robot.commands.GrabberForSecondsCommand;
import org.usfirst.frc.team4509.robot.commands.TurnCommand;
import org.usfirst.frc.team4509.robot.commands.WinchForSecondsCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command Group to put a cube on the switch.
 */
public class SwitchCommandGroup extends CommandGroup {

	public SwitchCommandGroup() {
		if(Robot.getStartingPosition() == Robot.gameData[1]) {
			addSequential(new DriveForFeetCommand(19.061));
			addSequential(new TurnCommand(90 * -Robot.startPosition));
			addSequential(new DriveForFeetCommand(0)); // TODO
			addSequential(new TurnCommand(90 * -Robot.startPosition));
			addSequential(new GrabCommand());
			addSequential(new WinchForSecondsCommand(1, 2)); // TODO
			addSequential(new DriveForFeetCommand(1)); // TODO
			addSequential(new GrabberForSecondsCommand(1, 2)); // TODO
			addSequential(new DriveForFeetCommand(-1)); // TODO
			addSequential(new WinchForSecondsCommand(-1, 2)); // TODO
		}
	}
	
}