package org.usfirst.frc.team4509.robot.commands.auto;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.commands.DriveForFeetCommand;
import org.usfirst.frc.team4509.robot.commands.TurnCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Command Group to pass the auto line.
 */
public class BasicCommandGroup extends CommandGroup {

	public BasicCommandGroup(int side) {
		if(Robot.startPosition == 0) {
			addSequential(new TurnCommand(90 * side));
			addSequential(new DriveForFeetCommand(5.5));
			addSequential(new TurnCommand(-90 * side));
			addSequential(new DriveForFeetCommand(13.667));
		} else {
			addSequential(new DriveForFeetCommand(19.061));
		}
	}
}
