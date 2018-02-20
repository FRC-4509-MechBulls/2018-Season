package org.usfirst.frc.team4509.robot.commands;


import org.usfirst.frc.team4509.robot.Cube;
import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;
import org.usfirst.frc.team4509.robot.subsystems.CameraSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;


/**
 * Align the robot with a cube
 * 
 * @author FRC Team 4509
 */
public class AlignCommand extends Command {
	
	Cube cube;
	int tries = 0;
	
	public AlignCommand() {
		requires(Robot.drivingSubsystem);
		this.setInterruptible(false);
	}

	protected void initialize() {  }

	protected void execute() {
		try {
			Cube newCube = CameraSubsystem.findBestX();
			if(newCube != null) this.cube = newCube;
			if(this.cube == null) return;
			double diff = this.getDiff();
			if(this.cube.getX() + (this.cube.getWidth() / 2) < RobotMap.PIXY_MAX_X / 2) {
				Robot.drivingSubsystem.drive(0, 0, diff < 4 ? -0.5 : -1);
				Timer.delay(0.1);
			} else {
				Robot.drivingSubsystem.drive(0, 0, diff < 4 ? 0.5 : 1);
				Timer.delay(0.1);
			}
		} catch(NullPointerException e) {} // shh it's okay everything's going to be fine... - K
	}

	protected boolean isFinished() {
		if(this.getDiff() < 1) System.out.println("close enough");
		return this.getDiff() < 1; // if it's close enough
	}

	protected void end() {
		Robot.drivingSubsystem.stop();
	}
	
	private double getDiff() throws NullPointerException {
		return Math.abs((RobotMap.PIXY_MAX_X / 2) - (this.cube.getX() + (this.cube.getWidth() / 2)));
	}

}
