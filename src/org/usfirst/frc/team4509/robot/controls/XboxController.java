package org.usfirst.frc.team4509.robot.controls;

import edu.wpi.first.wpilibj.GenericHID;

/**
 * Implements controls using a Xbox controller
 * 
 * @author FRC Team 4509
 */
public class XboxController implements BaseController {

	edu.wpi.first.wpilibj.XboxController controller;
	
	public XboxController(int port) {
		this.controller = new edu.wpi.first.wpilibj.XboxController(port);
	}
	
	@Override
	public double getDrive() {
		return this.controller.getTriggerAxis(GenericHID.Hand.kRight) - this.controller.getTriggerAxis(GenericHID.Hand.kLeft);
	}
	
	@Override
	public double getSlide() {
		double n = 0;
		if(this.controller.getBumper(GenericHID.Hand.kLeft)) n--;
		if(this.controller.getBumper(GenericHID.Hand.kRight)) n++;
		return (double)n;
	}
	
	@Override
	public double getTurn() {
		double n = this.controller.getX(GenericHID.Hand.kRight);
		return Math.abs(n) < 0.1 ? 0 : n; // dead-zone
	}
	
	@Override
	public int getFixedTurn() {
		return this.controller.getPOV();
	}
	
}
