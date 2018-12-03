package org.usfirst.frc.team4509.robot.motionprofiling;

public class MotionProfileStep implements Comparable<MotionProfileStep> {
	
	public double time;
	public String text;
	public double leftDriveSpeed, rightDriveSpeed;
	public double grabberSpeed;
	public double winchSpeed;
	
	private MotionProfileStep() {}
	
	public static class Builder {
		
		public double time;
		public String text;
		public double leftDriveSpeed, rightDriveSpeed;
		public double grabberSpeed;
		public double winchSpeed;
		
		public Builder(double time) {
			this.time = time;
		}
		
		public Builder setText(String text) {
			this.text = text;
			return this;
		}
		
		public Builder setLeftDriveSpeed(double speed) {
			this.leftDriveSpeed = speed;
			return this;
		}
		
		public Builder setRightDriveSpeed(double speed) {
			this.rightDriveSpeed = speed;
			return this;
		}
		
		public Builder setWinchSpeed(double speed) {
			this.winchSpeed = speed;
			return this;
		}
		
		public Builder setGrabberSpeed(double speed) {
			this.grabberSpeed = speed;
			return this;
		}
		
		public MotionProfileStep build() {
			MotionProfileStep step = new MotionProfileStep();
			step.time = this.time;
			step.text = this.text;
			step.leftDriveSpeed = this.leftDriveSpeed;
			step.rightDriveSpeed = this.rightDriveSpeed;
			step.winchSpeed = this.winchSpeed;
			step.grabberSpeed = this.grabberSpeed;
			return step;
		}
		
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
		return String.format("%f,%s,%f,%f,%f,%f", this.time, (this.text == null ? "_" : this.text),
				this.leftDriveSpeed,
				this.rightDriveSpeed,
				this.winchSpeed,
				this.grabberSpeed);
	}
	
	public static MotionProfileStep fromString(String str) {
		String[] strA = str.split(",");
		return (new MotionProfileStep.Builder(Double.parseDouble(strA[0])))
				.setText(strA[1])
				.setLeftDriveSpeed(Double.parseDouble(strA[2]))
				.setRightDriveSpeed(Double.parseDouble(strA[3]))
				.setWinchSpeed(Double.parseDouble(strA[4]))
				.setGrabberSpeed(Double.parseDouble(strA[5]))
				.build();
	}
	
}