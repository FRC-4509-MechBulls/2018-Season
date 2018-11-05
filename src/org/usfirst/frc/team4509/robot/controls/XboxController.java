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
		double n = this.controller.getX(GenericHID.Hand.kLeft);
		return Math.abs(n) < Preferences.getInstance().getDouble("DEADZONE", 0.1) ? 0 : n;
	}
	
	@Override
	public double getWinch() {
		double n = 0;
		if(this.controller.getBumperPressed(GenericHID.Hand.kRight)) n++;
		if(this.controller.getBumperPressed(GenericHID.Hand.kLeft))  n--;
		return n;
	}
	
	@Override
	public double getGrabber() {
		double n = this.controller.getY(GenericHID.Hand.kRight);
		return Math.abs(n) < Preferences.getInstance().getDouble("DEADZONE", 0.1) ? 0 : n;
	}
	
	public boolean getAutoTest() {
		return this.controller.getYButton();
	}
	
	public boolean getSelectNextProfile() {
		return this.controller.getPOV() > 85 && this.controller.getPOV() < 95;
	}
	
	public boolean getSelectLastProfile() {
		return this.controller.getPOV() > 265 && this.controller.getPOV() < 275;
	}
	
	public boolean getRunProfile() {
		return this.controller.getPOV() > 355 && this.controller.getPOV() < 5 && this.controller.getPOV() > -1;
	}
	
	public boolean getRecordProfile() {
		return this.controller.getPOV() > 175 && this.controller.getPOV() < 185;
	}
	
}
