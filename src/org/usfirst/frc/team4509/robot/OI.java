package org.usfirst.frc.team4509.robot;

import org.usfirst.frc.team4509.robot.commands.*;
import org.usfirst.frc.team4509.robot.controls.*;

import edu.wpi.first.wpilibj.buttons.Trigger;

/*
 * This class handles human input and maps it to commands.
 * OI stands for Operator Input
 */
public class OI {
	
	public static final int XBOX_CONTROLLER_1_PORT = 0;
	public static final int XBOX_CONTROLLER_2_PORT = 1;
	
	public ControllerBase controller;
	DriveTrigger     driveTrigger;
	WinchTrigger     winchTrigger;
	GrabberTrigger   grabberTrigger;
	
	public OI() {
		// List of possible controllers
		this.controller = new XboxControllerPair(OI.XBOX_CONTROLLER_1_PORT, OI.XBOX_CONTROLLER_2_PORT);
		//this.controller = new XboxController(OI.XBOX_CONTROLLER_1_PORT);
		
		// Init triggers
		this.driveTrigger     = new DriveTrigger();
		this.winchTrigger     = new WinchTrigger();
		this.grabberTrigger   = new GrabberTrigger();
	}
	
	// Maps triggers to commands.
	public void setTriggers() {
		this.driveTrigger.whileActive(new DirectDriveCommand()); // while DriveTrigger.get() returns true, run DirectDriveCommand. When DriveTrigger.get() returns false, interrupt the command.
		this.winchTrigger.whileActive(new DirectWinchCommand());
		this.grabberTrigger.whileActive(new DirectGrabberCommand());
	}
	
	class DriveTrigger extends Trigger {
		// if the controller class is telling us to drive or turn, return true.
		public boolean get() { return Robot.oi.controller.getDrive() != 0 || Robot.oi.controller.getTurn() != 0; }
	}
	
	class WinchTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getWinch() != 0; }
	}
	
	class GrabberTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getGrabber() != 0; }
	}

}
