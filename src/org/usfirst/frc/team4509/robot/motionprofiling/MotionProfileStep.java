package org.usfirst.frc.team4509.robot.motionprofiling;


public class MotionProfileStep implements Comparable<MotionProfileStep> {
	
	public static final MotionProfileStep STOP = new MotionProfileStep(0, 0, 0, 0, 0);
	
	public double time;
	public String text; // just in case you want to add a description
	public double leftFrontDriveTalonSpeed, rightFrontDriveTalonSpeed;
	public double grabberLeftTalonSpeed;
	public double winchTalonSpeed;
	
	public MotionProfileStep() {}
	
	public MotionProfileStep(double t, double lF, double rF, double gL, double w) {
		this.time = t;
		this.leftFrontDriveTalonSpeed = lF;
		this.rightFrontDriveTalonSpeed = rF;
		this.grabberLeftTalonSpeed = gL;
		this.winchTalonSpeed = w;
	}
	
	public MotionProfileStep(String txt, double t, double lF, double rF, double gL, double w) {
		this(t, lF, rF, gL, w);
		this.text = txt;
	}
	
	// compares based on time.
	public int compareTo(MotionProfileStep other) {
		if(this.time < other.time)
			return -1;
		else if(this.time == other.time)
			return 0;
		else
			return 1;
	}
	
	public String toString() {
		return String.format("(%f,\"%s\",%f,%f,%f,%f)", this.time, (this.text == null ? "" : this.text),
				this.leftFrontDriveTalonSpeed,
				this.rightFrontDriveTalonSpeed,
				this.grabberLeftTalonSpeed,
				this.winchTalonSpeed);
	}
	
}
