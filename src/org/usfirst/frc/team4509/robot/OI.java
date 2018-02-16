package org.usfirst.frc.team4509.robot;


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
	public DriveTrigger driveTrigger;
	public FixedTurnTrigger fixedTurnTrigger;
	
	public OI() {
		this.controller = new XboxController(OI.XBOX_CONTROLLER_PORT);
		//this.controller = new JoystickPair(OI.LEFT_JOYSTICK_PORT, OI.RIGHT_JOYSTICK_PORT);
		this.driveTrigger     = new DriveTrigger();
		this.fixedTurnTrigger = new FixedTurnTrigger();
	}
	
	class DriveTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getDrive() > 0 || Robot.oi.controller.getSlide() != 0 ||  Robot.oi.controller.getTurn() != 0; }
	}
	
	class FixedTurnTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getFixedTurn() != -1; }
	}

}
