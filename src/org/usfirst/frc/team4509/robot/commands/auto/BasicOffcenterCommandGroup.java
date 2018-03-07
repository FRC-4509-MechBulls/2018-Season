package org.usfirst.frc.team4509.robot.commands.auto;

import org.usfirst.frc.team4509.robot.commands.DriveForFeetCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Command Group to pass the auto line.
 */
public class BasicOffcenterCommandGroup extends CommandGroup {

	public BasicOffcenterCommandGroup() {
		addSequential(new DriveForFeetCommand(19.061));
	}
}
