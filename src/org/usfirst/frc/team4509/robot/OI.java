package org.usfirst.frc.team4509.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static final int LEFT_JOYSTICK_PORT  = 1;
	public static final int RIGHT_JOYSTICK_PORT = 0;
	public static final int XBOX_CONTROLLER_PORT = 0;
	
	public XboxController controller;
	
	public OI() {
		this.controller = new XboxController(OI.XBOX_CONTROLLER_PORT);
	}

}
