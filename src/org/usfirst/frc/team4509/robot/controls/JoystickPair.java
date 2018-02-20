package org.usfirst.frc.team4509.robot.controls;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;


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
		return Math.abs(this.jRight.getY()) < Preferences.getInstance().getDouble("DEADZONE", 0.1) ? 0 : this.jRight.getY();
	}

	@Override
	public double getSlide() {
		return Math.abs(this.jRight.getX()) < Preferences.getInstance().getDouble("DEADZONE", 0.1) ? 0 : this.jRight.getX();
	}

	/**
	 * Not implemented
	 * 
	 * @return 0
	 */
	@Override
	public double getTurn() {
		return 0;
	}

	@Override
	public int getFixedTurn() {
		return (int)this.jLeft.getDirectionDegrees();
	}
	
	@Override
	public boolean getAlign() {
		return false; // TODO: implement
	}
	
	@Override
	public double getWinch() {
		return 0.0; // TODO: implement
	}
	
	@Override
	public double getGrabber() {
		return 0.0; // TODO: implement
	}

}
