package org.usfirst.frc.team4509.robot;

import org.usfirst.frc.team4509.robot.commands.*;
import org.usfirst.frc.team4509.robot.controls.*;

import edu.wpi.first.wpilibj.buttons.Trigger;


public class OI {
	
	public static final int XBOX_CONTROLLER_1_PORT = 0;
	public static final int XBOX_CONTROLLER_2_PORT = 1;
	
	public ControllerBase controller;
	DriveTrigger     driveTrigger;
	WinchTrigger     winchTrigger;
	GrabberTrigger   grabberTrigger;
	
	public OI() {
		this.controller = new XboxControllerPair(OI.XBOX_CONTROLLER_1_PORT, OI.XBOX_CONTROLLER_2_PORT);
		//this.controller = new XboxController(OI.XBOX_CONTROLLER_1_PORT);
		
		this.driveTrigger     = new DriveTrigger();
		this.winchTrigger     = new WinchTrigger();
		this.grabberTrigger   = new GrabberTrigger();
	}
	
	public void setTriggers() {
		this.driveTrigger.whileActive(new DirectDriveCommand());
		this.winchTrigger.whileActive(new DirectWinchCommand());
		this.grabberTrigger.whileActive(new DirectGrabberCommand());
	}
	
	class DriveTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getDrive() != 0 || Robot.oi.controller.getTurn() != 0; }
	}
	
	class WinchTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getWinch() != 0; }
	}
	
	class GrabberTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getGrabber() != 0; }
	}

}
