package org.usfirst.frc.team4509.robot.commands;

import org.usfirst.frc.team4509.robot.VisualCube;
import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;
import org.usfirst.frc.team4509.robot.subsystems.CameraSubsystem;

import edu.wpi.first.wpilibj.command.Command;


/**
 * Align the robot with a cube
 * 
 * @author FRC Team 4509
 */
public class TurnToAlignCommand extends Command {
	
	VisualCube cube;
	int tries = 0;
	
	public TurnToAlignCommand() {
		requires(Robot.drivingSubsystem);
	}

	protected void initialize() {  }

	protected void execute() {
		try {
			VisualCube newCube = CameraSubsystem.findBestX();
			if(newCube != null) this.cube = newCube;
			if(this.cube == null) return;
			double diff = this.getDiff();
			if(this.cube.getX() + (this.cube.getWidth() / 2) < RobotMap.PIXY_MAX_X / 2) {
				Robot.drivingSubsystem.turn(diff < 4 ? -0.5 : -1);
			} else {
				Robot.drivingSubsystem.turn(diff < 4 ? 0.5 : 1);
			}
		} catch(NullPointerException e) {} // shh it's okay everything's going to be fine... - K
	}

	protected boolean isFinished() {
		try {
			return this.getDiff() < 1; // if it's close enough
		} catch(NullPointerException e) { return false; }
	}

	protected void end() {
		Robot.drivingSubsystem.stop();
	}
	
	private double getDiff() throws NullPointerException {
		return Math.abs((RobotMap.PIXY_MAX_X / 2) - (this.cube.getX() + (this.cube.getWidth() / 2)));
	}

}
