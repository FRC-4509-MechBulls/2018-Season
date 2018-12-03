package org.usfirst.frc.team4509.robot.controls;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Preferences;


public class XboxController implements ControllerBase {

	edu.wpi.first.wpilibj.XboxController controller;
	
	public XboxController(int port) {
		this.controller = new edu.wpi.first.wpilibj.XboxController(port);
	}
	
	@Override
	public boolean getCancelAll() {
		return this.controller.getStartButtonPressed();
	}
	
	@Override
	public double getDrive() {
		return this.controller.getTriggerAxis(GenericHID.Hand.kRight) - this.controller.getTriggerAxis(GenericHID.Hand.kLeft);
	}
	
	@Override
	public double getTurn() {
		double n = this.controller.getX(GenericHID.Hand.kRight);
		return Math.abs(n) < Preferences.getInstance().getDouble("DEADZONE", 0.1) ? 0 : n;
	}
	
	@Override
	public double getWinch() {
		double n = -1 * this.controller.getY(GenericHID.Hand.kLeft);
		return Math.abs(n) < Preferences.getInstance().getDouble("DEADZONE", 0.1) ? 0 : n;
	}
	
	@Override
	public double getGrabber() {
		double n = 0;
		if(this.controller.getBumper(GenericHID.Hand.kRight)) n--;
		if(this.controller.getBumper(GenericHID.Hand.kLeft))  n++;
		return n;
	}
	
	public boolean getAutoTest() {
		return this.controller.getYButton();
	}
	
	public boolean getSelectNextProfile() {
		return this.controller.getPOV() == 90;
	}
	
	public boolean getSelectLastProfile() {
		return this.controller.getPOV() == 270;
	}
	
	public boolean getRunProfile() {
		return this.controller.getPOV() == 0;
	}
	
	public boolean getRecordProfile() {
		return this.controller.getPOV() == 180;
	}
	
}
