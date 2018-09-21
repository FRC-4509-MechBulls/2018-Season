package org.usfirst.frc.team4509.robot.controls;

/*
 * Interface to allow hot-swapping of controller classes
 */
public interface ControllerBase {

	double  getDrive();     // [-1, 1], inactive @ 0
	double  getTurn();      // [-1, 1], inactive @ 0
	double  getWinch();     // [-1, 1], inactive @ 0
	double  getGrabber();   // [-1, 1], inactive @ 0

}
