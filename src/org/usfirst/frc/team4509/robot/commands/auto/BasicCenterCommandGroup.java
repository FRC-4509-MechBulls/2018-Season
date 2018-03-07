package org.usfirst.frc.team4509.robot.commands.auto;

import org.usfirst.frc.team4509.robot.commands.DriveForFeetCommand;
import org.usfirst.frc.team4509.robot.commands.TurnCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Command Group to pass the auto line.
 */
public class BasicCenterCommandGroup extends CommandGroup {

	public BasicCenterCommandGroup() {
		addSequential(new TurnCommand(90 * side));
		addSequential(new DriveForFeetCommand(5.5));
		addSequential(new TurnCommand(-90 * side));
		addSequential(new DriveForFeetCommand(13.667));
	}
}
