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
		//RobotMap.leftFrontDriveTalon.set(step.leftFrontDriveTalonSpeed);
		//RobotMap.rightFrontDriveTalon.set(step.rightFrontDriveTalonSpeed);
		RobotMap.drive.tankDrive(step.leftFrontDriveTalonSpeed, -1 * step.rightFrontDriveTalonSpeed, false);
		RobotMap.grabberLeftTalon.set(step.grabberLeftTalonSpeed);
		RobotMap.winchTalon.set(step.winchTalonSpeed);
	}
	
	@Override
	protected boolean isFinished() {
		MotionProfileStep currentStep = this.profile.getStep(this.timeSinceInitialized());
		return currentStep == null || (currentStep.text != null && currentStep.text.equals("END")) || this.timeSinceInitialized() > 15;
	}
	
	protected void end() {
		Robot.drivingSubsystem.stop();
		Robot.grabberSubsystem.stop();
		Robot.winchSubsystem.stop();
	}

}
