package org.usfirst.frc.team4509.robot.controls;

import edu.wpi.first.wpilibj.GenericHID;


/**
 * Implements {@link ControllerBase} with a Xbox controller
 * 
 * @author FRC Team 4509
 */
public class GuitarController implements ControllerBase {

	edu.wpi.first.wpilibj.XboxController controller;
	
	public GuitarController(int port) {
		this.controller = new edu.wpi.first.wpilibj.XboxController(port);
	}
	
	@Override
	public double getDrive() {
		double n = 0;
		if(this.controller.getAButtonPressed()) n++;
		if(this.controller.getXButtonPressed()) n--;
		return n;
	}
	
	@Override
	public double getTurn() {
		double n = 0;
		if(this.controller.getBButtonPressed()) n++;
		if(this.controller.getYButtonPressed())  n--;
		return n;
	}
	
	@Override
	public int getFixedTurn() {
		return this.controller.getPOV();
	}
	
	@Override
	public boolean getAlign() {
		return false;
	}
	
	@Override
	public double getWinch() {
		return Math.sin(Math.toRadians(this.controller.getPOV()));
	}
	
	@Override
	public double getGrabber() {
		return this.controller.getY(GenericHID.Hand.kLeft);
	}
	
	@Override
	public boolean getDisabled() {
		return this.controller.getBackButton();
	}
	
}
