package org.usfirst.frc.team4509.robot.controls;

/**
 * An interface that allows for multiple types of controllers
 * 
 * @author FRC Team 4509
 */
public interface BaseController {

	double  getDrive();
	double  getSlide();
	double  getTurn();
	int     getFixedTurn();
	boolean getAlign();
	double  getWinch();
	double  getGrabber();
	boolean getDisabled();

}
