package org.usfirst.frc.team4509.robot.commands.auto;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.commands.DriveForFeetCommand;
import org.usfirst.frc.team4509.robot.commands.TurnCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Command Group to pass the auto line.
 */
public class BasicCommandGroup extends CommandGroup {

	int goSide;
	
	public BasicCommandGroup(int goSide) {
		requires(Robot.drivingSubsystem);
		this.goSide = goSide;
	}
	
	protected void initialize() {
		if(Robot.getStartingPosition() == 'C') {
				if(this.goSide == -1) {
					addSequential(new TurnCommand(-90));
					addSequential(new DriveForFeetCommand(9.75));
				} else if(this.goSide == 1) {
					addSequential(new TurnCommand(90));
					addSequential(new DriveForFeetCommand(5.75));
				}
			addSequential(new DriveForFeetCommand(13.667));
		} else {
			addSequential(new DriveForFeetCommand(19.061));
		}
	}
	
}
