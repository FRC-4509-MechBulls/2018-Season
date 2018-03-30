package org.usfirst.frc.team4509.robot.commands.auto;

import org.usfirst.frc.team4509.robot.RobotMap;
import org.usfirst.frc.team4509.robot.commands.TimedDriveCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Command Group to pass the auto line.
 */
public class IdiotAutoCommandGroup extends CommandGroup {
	
	public IdiotAutoCommandGroup() {
		addSequential(new TimedDriveCommand(15 * RobotMap.SECONDS_PER_FOOT, 1));
	}
	
}
