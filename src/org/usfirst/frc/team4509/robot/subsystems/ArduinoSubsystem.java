package org.usfirst.frc.team4509.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ArduinoSubsystem extends Subsystem {
	
	SerialPort arduino;

	public ArduinoSubsystem(SerialPort arduino) {
		this.arduino = arduino;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	public byte readByte() {
		if(this.arduino.getBytesReceived() > 0)
			return this.arduino.read(1)[0];
		else
			return -0b01;
	}
	
	public void writeByte(byte b) {
		this.arduino.write(new byte[]{ b }, 1);
	}

}
