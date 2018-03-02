package org.usfirst.frc.team4509.robot;

import org.usfirst.frc.team4509.robot.commands.*;
import org.usfirst.frc.team4509.robot.controls.BaseController;
import org.usfirst.frc.team4509.robot.controls.XboxController;

import edu.wpi.first.wpilibj.buttons.Trigger;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * @author FRC Team 4509
 */
public class OI {
	
	public static final int LEFT_JOYSTICK_PORT  = 1;
	public static final int RIGHT_JOYSTICK_PORT = 0;
	public static final int XBOX_CONTROLLER_PORT = 0;
	
	public BaseController controller;
	DriveTrigger     driveTrigger;
	FixedTurnTrigger fixedTurnTrigger;
	AlignTrigger     alignTrigger;
	WinchTrigger     winchTrigger;
	GrabberTrigger   grabberTrigger;
	DisableTrigger   disableTrigger;
	
	public OI() {
		this.controller = new XboxController(OI.XBOX_CONTROLLER_PORT);
		//this.controller = new JoystickPair(OI.LEFT_JOYSTICK_PORT, OI.RIGHT_JOYSTICK_PORT);
		
		this.driveTrigger     = new DriveTrigger();
		this.fixedTurnTrigger = new FixedTurnTrigger();
		this.alignTrigger     = new AlignTrigger();
		this.winchTrigger     = new WinchTrigger();
		this.grabberTrigger   = new GrabberTrigger();
		this.disableTrigger   = new DisableTrigger();
	}
	
	public void setTriggers() {
		this.driveTrigger.whileActive(new DirectDriveCommand());
		this.fixedTurnTrigger.whenActive(new DirectAbsoluteTurnCommand());
		//this.alignTrigger.whenActive(new AlignCommand());
		this.winchTrigger.whileActive(new DirectWinchCommand());
		this.grabberTrigger.whileActive(new DirectGrabberCommand());
		this.disableTrigger.whileActive(new StopAllCommand());
	}
	
	class DriveTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getDrive() != 0 || Robot.oi.controller.getSlide() != 0 || Robot.oi.controller.getTurn() != 0; }
	}
	
	class FixedTurnTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getFixedTurn() != -1; }
	}
	
	class AlignTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getAlign(); }
	}
	
	class WinchTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getWinch() != 0; }
	}
	
	class GrabberTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getGrabber() != 0; }
	}
	
	class DisableTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getDisabled(); }
	}

}
