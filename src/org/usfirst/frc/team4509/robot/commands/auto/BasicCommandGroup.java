package org.usfirst.frc.team4509.robot.commands.auto;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.commands.DriveForFeetCommand;
import org.usfirst.frc.team4509.robot.commands.SlideForSecondsCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Command Group to pass the auto line.
 */
public class BasicCommandGroup extends CommandGroup {

	public BasicCommandGroup(int side) {
		if(Robot.startPosition == 0) {
			addSequential(new SlideForSecondsCommand(1 * side, 2));
			addSequential(new DriveForFeetCommand(13.667));
		} else {
			addSequential(new DriveForFeetCommand(19.061));
		}
	}
}
