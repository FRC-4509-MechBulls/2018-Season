package org.usfirst.frc.team4509.robot;

import org.usfirst.frc.team4509.robot.auto.AutoTestCommandGroup;
import org.usfirst.frc.team4509.robot.commands.*;
import org.usfirst.frc.team4509.robot.controls.*;
import org.usfirst.frc.team4509.robot.motionprofiling.RecordProfileFromOICommand;
import org.usfirst.frc.team4509.robot.motionprofiling.RunProfileFromOICommand;
import org.usfirst.frc.team4509.robot.motionprofiling.SelectLastProfileCommand;
import org.usfirst.frc.team4509.robot.motionprofiling.SelectNextProfileCommand;

import edu.wpi.first.wpilibj.buttons.Trigger;

/*
 * This class handles human input and maps it to commands.
 * OI stands for Operator Input
 * 
 */
public class OI {
	
	public static final int XBOX_CONTROLLER_1_PORT = 0;
	public static final int XBOX_CONTROLLER_2_PORT = 1;
	
	public ControllerBase controller;
	CancelAllTrigger cancelAllTrigger;
	DriveTrigger    driveTrigger;
	WinchTrigger    winchTrigger;
	GrabberTrigger  grabberTrigger;
	AutoTestTrigger autoTestTrigger;
	SelectNextProfileTrigger selectNextProfileTrigger;
	SelectLastProfileTrigger selectLastProfileTrigger;
	RunProfileTrigger runProfileTrigger;
	RecordProfileTrigger recordProfileTrigger;
	
	public OI() {
		// List of possible controllers
		this.controller = new XboxControllerPair(OI.XBOX_CONTROLLER_1_PORT, OI.XBOX_CONTROLLER_2_PORT);
		//this.controller = new XboxController(OI.XBOX_CONTROLLER_1_PORT);
		
		// Init triggers
		this.cancelAllTrigger = new CancelAllTrigger();
		this.driveTrigger     = new DriveTrigger();
		this.winchTrigger     = new WinchTrigger();
		this.grabberTrigger   = new GrabberTrigger();
		this.autoTestTrigger  = new AutoTestTrigger();
		this.selectNextProfileTrigger = new SelectNextProfileTrigger();
		this.selectLastProfileTrigger = new SelectLastProfileTrigger();
		this.runProfileTrigger = new RunProfileTrigger();
		this.recordProfileTrigger = new RecordProfileTrigger();
	}
	
	// Maps triggers to commands.
	public void setTriggers() {
		this.cancelAllTrigger.whileActive(new CancelAllCommand());
		this.driveTrigger.whileActive(new DirectDriveCommand()); // while DriveTrigger.get() returns true, run DirectDriveCommand. When DriveTrigger.get() returns false, interrupt the command.
		this.winchTrigger.whileActive(new DirectWinchCommand());
		this.grabberTrigger.whileActive(new DirectGrabberCommand());
		this.autoTestTrigger.whenActive(new AutoTestCommandGroup());
		
		this.selectNextProfileTrigger.whenActive(new SelectNextProfileCommand());
		this.selectLastProfileTrigger.whenActive(new SelectLastProfileCommand());
		this.runProfileTrigger.whenActive(new RunProfileFromOICommand());
		this.recordProfileTrigger.whenActive(new RecordProfileFromOICommand());
	}
	
	class CancelAllTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getCancelAll(); }
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
	
	class AutoTestTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getAutoTest(); }
	}
	
	class SelectNextProfileTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getSelectNextProfile(); }
	}
	
	class SelectLastProfileTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getSelectLastProfile(); }
	}
	
	class RunProfileTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getRunProfile(); }
	}
	
	class RecordProfileTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getRecordProfile(); }
	}

}