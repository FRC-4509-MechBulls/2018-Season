package org.usfirst.frc.team4509.robot.controls;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Preferences;


/**
 * Implements {@link ControllerBase} with two Xbox controllers
 * 
 * @author FRC Team 4509
 */
public class XboxControllerPair implements ControllerBase {

	edu.wpi.first.wpilibj.XboxController controller1;
	edu.wpi.first.wpilibj.XboxController controller2;
	
	public XboxControllerPair(int port1, int port2) {
		this.controller1 = new edu.wpi.first.wpilibj.XboxController(port1);
		this.controller2 = new edu.wpi.first.wpilibj.XboxController(port2);
	}
	
	@Override
	public double getDrive() {
		return this.controller1.getTriggerAxis(GenericHID.Hand.kRight) - this.controller1.getTriggerAxis(GenericHID.Hand.kLeft);
	}
	
	@Override
	public double getTurn() {
		double n = this.controller1.getX(GenericHID.Hand.kRight);
		return Math.abs(n) < Preferences.getInstance().getDouble("DEADZONE", 0.1) ? 0 : n;
	}
	
	@Override
	public int getFixedTurn() {
		return this.controller1.getPOV();
	}
	
	@Override
	public boolean getAlign() {
		return this.controller1.getYButtonPressed();
	}
	
	@Override
	public double getWinch() {
		return this.controller2.getTriggerAxis(GenericHID.Hand.kRight) - this.controller2.getTriggerAxis(GenericHID.Hand.kLeft);
	}
	
	@Override
	public double getGrabber() {
		double n = this.controller2.getY(GenericHID.Hand.kLeft);
		return Math.abs(n) < Preferences.getInstance().getDouble("DEADZONE", 0.1) ? 0 : n;
	}
	
	@Override
	public boolean getDisabled() {
		return this.controller1.getStartButton() || this.controller2.getStartButton();
	}

}
