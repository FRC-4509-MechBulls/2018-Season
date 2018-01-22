package org.usfirst.frc.team4509.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static final int LEFT_JOYSTICK_PORT  = 1;
	public static final int RIGHT_JOYSTICK_PORT = 0;
	public static final int XBOX_CONTROLLER_PORT = 0;
	
	public XboxController controller;
	public POVTrigger povTrigger;
	public DriveTrigger driveTrigger;
	
	public OI() {
		this.controller = new XboxController(OI.XBOX_CONTROLLER_PORT);
		this.povTrigger = new POVTrigger();
		this.driveTrigger = new DriveTrigger();
	}
	
	class POVTrigger extends Button {
		public boolean get() {
			return Robot.oi.controller.getPOV() > -1;
		}
	}
	
	class DriveTrigger extends Button {
		public boolean get() {
			return Robot.oi.controller.getTriggerAxis(GenericHID.Hand.kLeft) > 0 || Robot.oi.controller.getTriggerAxis(GenericHID.Hand.kRight) > 0 || Robot.oi.controller.getX(GenericHID.Hand.kRight) > 0;
		}
	}

}
