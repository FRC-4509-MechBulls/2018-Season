package org.usfirst.frc.team4509.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team4509.Cube;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author FRC Team 4509
 */
public class CameraSubsystem extends Subsystem {
	
	SerialPort port;
	public Cube[] cubes;
	
	public CameraSubsystem() {  }
	
	public void setPort(SerialPort port) {
		this.port = port;
	}

    public void initDefaultCommand() {
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void getBlocks() {
    	if(this.port.getBytesReceived() > 0) {
    		String data = this.port.readString();
    		String[] major = data.split(":");
    		int n = Integer.parseInt(major[0]);
    		this.cubes = new Cube[n];
    		if(n > 0) {
    			String[] blocks = major[1].split(";");
    			for(int i = 0; i < n; i++) {
    				String[] block = blocks[i].split(",");
    				this.cubes[i] = new Cube(Integer.parseInt(block[1]), Integer.parseInt(block[2]), Integer.parseInt(block[3]), Integer.parseInt(block[4]));
    			}
    		}
    	}
    }
    
    public void displayInput() {
    	if(this.port.getBytesReceived() > 0) {
	    	byte[] ba = this.port.read(this.port.getBytesReceived());
	    	String s = "";
	    	for(byte b : ba)
	    		s += Byte.toString(b);
	    	System.out.println(s);
    	}
    }
    
    public void flushInput() {
    	this.port.read(this.port.getBytesReceived());
    }
    
}