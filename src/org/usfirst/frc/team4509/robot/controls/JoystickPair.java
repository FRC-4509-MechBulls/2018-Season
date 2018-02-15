package org.usfirst.frc.team4509.robot.controls;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Implements controls using two joysticks
 * 
 * @author FRC Team 4509
 */
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
	
	/**
	 * Unable to implement
	 * 
	 * @return -1
	 */
	@Override
	public int getFixedTurn() {
		return -1; // Can't be implemented
	}

}
