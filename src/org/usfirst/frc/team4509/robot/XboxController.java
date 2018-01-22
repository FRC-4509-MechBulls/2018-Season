package org.usfirst.frc.team4509.robot;

import edu.wpi.first.wpilibj.GenericHID;

public class XboxController implements BaseController {

	edu.wpi.first.wpilibj.XboxController controller;
	
	public XboxController(int port) {
		this.controller = new edu.wpi.first.wpilibj.XboxController(port);
	}
	
	public double getDrive() {
		return (-1 * this.controller.getTriggerAxis(GenericHID.Hand.kLeft)) + this.controller.getTriggerAxis(GenericHID.Hand.kRight);
	}
	
	public double getSlide() {
		double n = 0;
		if(this.controller.getBumper(GenericHID.Hand.kLeft)) n--;
		if(this.controller.getBumper(GenericHID.Hand.kRight)) n++;
		return (double)n;
	}
	
	public double getTurn() {
		return this.controller.getX(GenericHID.Hand.kRight);
	}
	
}
