package org.usfirst.frc.team4509.robot.commands.auto;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.commands.DriveForFeetCommand;
import org.usfirst.frc.team4509.robot.commands.SlideForSecondsCommand;
import org.usfirst.frc.team4509.robot.commands.TurnCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Command Group to put a cube on the scale.
 */
public class ScaleCommandGroup extends CommandGroup {

	public ScaleCommandGroup() {
		// Find out which side of the scale to score on
		// Check for starting right, left, or center
		
		addSequential(new DriveForFeetCommand(19.061));
		addSequential(new TurnCommand(180 * Robot.startPosition));
		addSequential(new SlideForSecondsCommand(-1, 2));
		// Acquire a cube
		
		addSequential(new SlideForSecondsCommand(1, 2));
		addSequential(new TurnCommand(180 * Robot.startPosition));
		// Elevate the cube
		
		addSequential(new DriveForFeetCommand(5.910));
		// Drop cube on the scale
		
		addSequential(new DriveForFeetCommand(-5.910));
		
		// Put the elevator down.
	}
}
