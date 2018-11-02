package org.usfirst.frc.team4509.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class CancelAllCommand extends InstantCommand {

	public void execute() {
		Scheduler.getInstance().removeAll();
	}
	
}
