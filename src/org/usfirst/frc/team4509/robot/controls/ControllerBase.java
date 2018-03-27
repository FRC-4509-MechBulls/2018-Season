package org.usfirst.frc.team4509.robot.controls;


/**
 * An interface that allows for multiple types of controllers
 * 
 * @author FRC Team 4509
 */
public interface ControllerBase {

	double  getDrive();     // [-1, 1], inactive @ 0
	double  getTurn();      // [-1, 1], inactive @ 0
	int     getFixedTurn(); // [0, 360], inactive @ -1
	boolean getAlign();     // inactive @ false
	double  getWinch();     // [-1, 1], inactive @ 0
	double  getGrabber();   // [-1, 1], inactive @ 0
	boolean getDisabled();  // inactive @ false

}
