package org.usfirst.frc.team4509.robot.motionprofiling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.usfirst.frc.team4509.robot.RobotMap;
import org.usfirst.frc.team4509.robot.logging.LogEntry;

public class MotionProfileStorage {
	
	public static MotionProfile getStopProfile() {
		MotionProfile.Builder profileBuilder = new MotionProfile.Builder("Stop");
		profileBuilder.add(new MotionProfileStep.Builder(0)
				.setText("START").setLeftDriveSpeed(0.0).setRightDriveSpeed(0.0).setWinchSpeed(0.0).setGrabberSpeed(0.0).build());
		profileBuilder.add(new MotionProfileStep.Builder(0.02)
				.setText("END").setLeftDriveSpeed(0.0).setRightDriveSpeed(0.0).setWinchSpeed(0.0).setGrabberSpeed(0.0).build());
		return profileBuilder.build();
	}
	
	public static MotionProfile getProfile(String filename) {
		try {
			if(filename != null && !filename.equals("") && Files.exists(Paths.get(RobotMap.ROOT_DIRECTORY + "motion_profiles/" + filename))) {
				return MotionProfileStorage.constructProfile(Files.readAllLines(Paths.get(RobotMap.ROOT_DIRECTORY + "motion_profiles/" + filename)).get(0));
			} else {
				(new LogEntry(1, "MotionProfileStorage", "getProfile", "Tried to get nonexistent profile " + filename + " from filesystem!"))
					.addTag("Motion Profiling Subsystem").put();
			}
		} catch(IOException e) {}
		return null;
	}
	
	public static MotionProfile constructProfile(String file) {
		String[] metaSplit = file.split(":");
		MotionProfile.Builder profileBuilder = new MotionProfile.Builder(metaSplit[0]);
		String[] stepListSplit = metaSplit[1].split(";");
		for(String stepRaw : stepListSplit) {
			profileBuilder.add(MotionProfileStep.fromString(stepRaw));
		}
		return profileBuilder.build();
	}
	
}