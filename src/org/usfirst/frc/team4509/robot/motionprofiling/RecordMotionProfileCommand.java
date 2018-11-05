package org.usfirst.frc.team4509.robot.motionprofiling;

import org.usfirst.frc.team4509.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class RecordMotionProfileCommand extends Command {

	private MotionProfile profile;
	
	public RecordMotionProfileCommand(MotionProfile profile) {
		requires(Robot.motionProfilingSubsystem);
		this.profile = profile;
	}

	@Override
	protected void initialize() {
		this.profile.startRecording();
		this.profile.addStep(new MotionProfileStep("START", this.profile.timer.get(), 0, 0, 0, 0));
	}

	@Override
	protected void execute() {
		this.profile.record();
	}
	
	@Override
	protected boolean isFinished() {
		return this.timeSinceInitialized() > 15;
	}

	@Override
	protected void end() {
		this.profile.addStep(new MotionProfileStep("END", this.profile.timer.get(), 0, 0, 0, 0));
		this.profile.stopRecording();
	}

}
