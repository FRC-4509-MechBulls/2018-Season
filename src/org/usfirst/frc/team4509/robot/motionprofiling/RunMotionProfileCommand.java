package org.usfirst.frc.team4509.robot.motionprofiling;

import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;


public class RunMotionProfileCommand extends Command {

	private MotionProfile profile;
	
	public RunMotionProfileCommand(MotionProfile profile) {
		requires(Robot.motionProfilingSubsystem);
		requires(Robot.drivingSubsystem);
		requires(Robot.grabberSubsystem);
		requires(Robot.winchSubsystem);
		this.profile = profile;
	}
	
	protected void initialize() {
		Robot.drivingSubsystem.stop();
		Robot.grabberSubsystem.stop();
		Robot.winchSubsystem.stop();
	}
	
	protected void execute() {
		MotionProfileStep step = this.profile.getStep(this.timeSinceInitialized());
		RobotMap.drive.tankDrive(step.leftDriveSpeed, -1 * step.rightDriveSpeed, false);
		RobotMap.grabberLeftTalon.set(step.grabberSpeed);
		RobotMap.winchTalon.set(step.winchSpeed);
	}
	
	@Override
	protected boolean isFinished() {
		MotionProfileStep currentStep = this.profile.getStep(this.timeSinceInitialized());
		return currentStep == null || currentStep.text.equals("END");
	}
	
	protected void end() {
		Robot.drivingSubsystem.stop();
		Robot.grabberSubsystem.stop();
		Robot.winchSubsystem.stop();
	}

}
