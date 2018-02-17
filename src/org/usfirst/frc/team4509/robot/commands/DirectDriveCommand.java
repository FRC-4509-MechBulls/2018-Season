package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.controls.BaseController;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to drive using input directly from the controller
 * 
 * @author FRC Team 4509
 */
public class DirectDriveCommand extends Command {
	
	private BaseController controller;
	
	public DirectDriveCommand(BaseController controller) {
		this.controller = controller;
		requires(Robot.drivingSubsystem);
	}

	protected void initialize() {  }

	public void execute() {
		Robot.drivingSubsystem.drive(this.controller.getDrive(), this.controller.getTurn(), this.controller.getSlide());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.drivingSubsystem.stop();
	}

}
