package org.usfirst.frc.team4509.robot.commands.auto;

import org.usfirst.frc.team4509.robot.commands.DriveForFeetCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Command Group to pass the auto line.
 */
public class NoAutoCommandGroup extends CommandGroup {
	
	public NoAutoCommandGroup() {
		addSequential(new DriveForFeetCommand(0.5));
		addSequential(new DriveForFeetCommand(-0.5));
	}
	
}
