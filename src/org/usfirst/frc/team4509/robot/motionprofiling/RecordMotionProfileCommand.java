package org.usfirst.frc.team4509.robot.motionprofiling;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;


public class RecordMotionProfileCommand extends Command {

	private MotionProfile.Builder profileBuilder;
	
	public RecordMotionProfileCommand(MotionProfile.Builder profileBuilder) {
		requires(Robot.motionProfilingSubsystem);
		this.profileBuilder = profileBuilder;
	}

	@Override
	protected void initialize() {
		this.profileBuilder.add(new MotionProfileStep.Builder(0)
		                        .setText("START")
		                        .setLeftDriveSpeed(0)
		                        .setRightDriveSpeed(0)
		                        .setWinchSpeed(0)
		                        .setGrabberSpeed(0)
		                        .build());
	}

	@Override
	protected void execute() {
		this.profileBuilder.add(new MotionProfileStep.Builder(this.timeSinceInitialized())
        .setText("_")
        .setLeftDriveSpeed(RobotMap.leftFrontDriveTalon.get())
        .setRightDriveSpeed(RobotMap.rightFrontDriveTalon.get())
        .setWinchSpeed(RobotMap.winchTalon.get())
        .setGrabberSpeed(RobotMap.grabberLeftTalon.get())
        .build());
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		this.profileBuilder.add(new MotionProfileStep.Builder(this.timeSinceInitialized())
        .setText("END")
        .setLeftDriveSpeed(0.0)
        .setRightDriveSpeed(0.0)
        .setWinchSpeed(0.0)
        .setGrabberSpeed(0.0)
        .build());
		Robot.motionProfilingSubsystem.addProfile(this.profileBuilder.build());
	}

}
