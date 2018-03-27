package org.usfirst.frc.team4509.robot.commands.auto;

import org.usfirst.frc.team4509.robot.commands.TimedDriveCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Command Group to pass the auto line.
 */
public class NoAutoCommandGroup extends CommandGroup {
	
	public NoAutoCommandGroup() {
		addSequential(new TimedDriveCommand(0.5, 1));
		addSequential(new TimedDriveCommand(0.5, -1));
	}
	
}
