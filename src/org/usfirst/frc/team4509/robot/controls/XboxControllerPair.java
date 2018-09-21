package org.usfirst.frc.team4509.robot.controls;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Preferences;

/*
 * Controller 1 - Drive
 * Forward    - Right Trigger
 * Backward   - Left Trigger
 * Turn Left  - Right Stick, X axis, left
 * Turn Right - Right Stick, X axis, right
 * 
 * Controller 2 - Grabber, Winch
 * Winch Up    - Right Trigger
 * Winch Down  - Left Trigger
 * Grabber In  - Left Stick, Y axis, down
 * Grabber Out - Left Stick, Y axis, up
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
		// Take the right trigger and subtract the left trigger from it. This prevents movement if both triggers are held.
		return this.controller1.getTriggerAxis(GenericHID.Hand.kRight) - this.controller1.getTriggerAxis(GenericHID.Hand.kLeft);
	}
	
	@Override
	public double getTurn() {
		double n = this.controller1.getX(GenericHID.Hand.kRight); // Get the x axis of the right stick on controller 1
		return Math.abs(n) < Preferences.getInstance().getDouble("DEADZONE", 0.1) ? 0 : n; // if the x axis is below the deadzone threshold, return 0. Prevents accidental movement.
	}
	
	@Override
	public double getWinch() {
		return this.controller2.getTriggerAxis(GenericHID.Hand.kRight) - this.controller2.getTriggerAxis(GenericHID.Hand.kLeft); // same as getDrive(), but on controller 2
	}
	
	@Override
	public double getGrabber() {
		double n = this.controller2.getY(GenericHID.Hand.kLeft); // Get the y axis of the left stick on controller 2
		return Math.abs(n) < Preferences.getInstance().getDouble("DEADZONE", 0.1) ? 0 : n;
	}

}
