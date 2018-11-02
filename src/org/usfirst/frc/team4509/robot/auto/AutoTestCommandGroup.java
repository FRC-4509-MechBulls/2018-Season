package org.usfirst.frc.team4509.robot.auto;

import org.usfirst.frc.team4509.robot.commands.DriveForwardCommand;
import org.usfirst.frc.team4509.robot.commands.MoveGrabberCommand;
import org.usfirst.frc.team4509.robot.commands.MoveWinchCommand;
import org.usfirst.frc.team4509.robot.commands.TurnCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class AutoTestCommandGroup extends CommandGroup {

	public AutoTestCommandGroup() {
		addSequential(new DriveForwardCommand(1));
		
		addSequential(new TimedCommand(1));
		
		addSequential(new TurnCommand(-90));
		
		addSequential(new TimedCommand(1));
		
		addParallel(new MoveWinchCommand(0.5, 1));
		addParallel(new MoveGrabberCommand(0.5, 1));
	}
	
}
