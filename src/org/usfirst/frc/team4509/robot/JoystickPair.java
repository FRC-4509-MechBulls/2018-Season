package org.usfirst.frc.team4509.robot;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickPair implements BaseController {

	Joystick jLeft, jRight;
	
	public JoystickPair(int leftPort, int rightPort) {
		this.jLeft  = new Joystick(leftPort);
		this.jRight = new Joystick(rightPort);
	}
	
	@Override
	public double getDrive() {
		return this.jRight.getY();
	}

	@Override
	public double getSlide() {
		return this.jRight.getX();
	}

	@Override
	public double getTurn() {
		return this.jLeft.getX();
	}

}
