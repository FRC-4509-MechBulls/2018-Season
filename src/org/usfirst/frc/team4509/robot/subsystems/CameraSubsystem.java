package org.usfirst.frc.team4509.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author FRC Team 4509
 */
public class CameraSubsystem extends Subsystem {
	
	SerialPort arduino;
	
	public CameraSubsystem(SerialPort arduino) {
		this.arduino = arduino;
	}

    public void initDefaultCommand() {
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void getBlocks() {
    	this.flushInput();
    	this.arduino.writeString("g");
    	if(this.arduino.getBytesReceived() > 0) {
    		byte[] data = this.arduino.read(this.arduino.getBytesReceived());
    		
    	}
    }
    
    public void flushInput() {
    	this.arduino.read(this.arduino.getBytesReceived());
    }
    
}