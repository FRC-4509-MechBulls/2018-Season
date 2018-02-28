package org.usfirst.frc.team4509.robot.subsystems;


import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team4509.robot.VisualCube;
import org.usfirst.frc.team4509.robot.Robot;
import org.usfirst.frc.team4509.robot.RobotMap;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * Manages the connection with the arduino for the Pixy
 * 
 * @author FRC Team 4509
 */
public class CameraSubsystem extends Subsystem {
	
	SerialPort port;
	private List<String> buffer;
	
	public CameraSubsystem() {
		this.buffer = new ArrayList<>();
	}
	
	public void setPort(SerialPort port) {
		this.port = port;
	}

	@Override
	protected void initDefaultCommand() {  }
	
	@Override
	public void periodic() {
		// add new data if there is any
		if(this.port.getBytesReceived() > 0) {
			try {
				String data = this.port.readString();
				for(char c : data.toCharArray()) {
					buffer.add("" + c);
				}
			} catch(RuntimeException e) {
				System.out.println("Camera failure: " + e.getMessage() +  ".");
				this.port.reset();
				System.out.println("Camera reset.");
			}
		}
		
		// check for full line
		if(buffer.contains("!")) {
			String line = "";
			int n = buffer.indexOf("!");
			for(int i = 0; i <= n; i++) {
				line += buffer.get(0);
				buffer.remove(0);
			}
			// if the data is good, add it
			if(CameraSubsystem.checkData(line))
				this.setCubes(CameraSubsystem.formData(line));
		}
	}
	
	public void setCubes(VisualCube[] cubes) {
		Robot.cubes = cubes;
	}

	/**
	 * Checks if the given data is valid.
	 * 
	 * @param line a line of data
	 * @return whether the given data is correctly formed or not
	 */
	public static boolean checkData(String line) {
		// Yes, this function is disgusting, I know. But it works, so who cares? At least it's commented. - K
		try {
			if(line.indexOf("!") == -1 || !line.endsWith("!")) return false; // is there a '!' and does the string end w/ it?
			String[] split1 = line.substring(0, line.indexOf("!")).split(":", -1);
			if(Integer.parseInt(split1[0]) == 0 && split1[1].equals("")) return true;
			String[] blocks = split1[1].split(";", -1);
			if(blocks.length != Integer.parseInt(split1[0])) throw new IllegalArgumentException(); // verify that the length number is equal to the number of blocks given
			for(String s : blocks) { // check each block
				String[] data = s.split(",");
				if(data.length != 4) throw new IllegalArgumentException(); // make sure there are the correct amount of attributes
				for(String s1 : data) // check each value of the block
					Integer.parseInt(s1); // This is just here to throw exceptions; it shouldn't do anything.
			return true;
			}
		} catch(IndexOutOfBoundsException e) {  } // catches bad arrays
			catch(NumberFormatException e)     {  } // catches stuff that got mixed up
			catch(IllegalArgumentException e)  {  } // catches user-thrown exceptions
		return false;
	}
	
	/**
	 * Turns the data into an array of cubes. This function assumes that the data is correctly formed.
	 * 
	 * @param line the line of data to interpret
	 * @return an array of cubes
	 */
	public static VisualCube[] formData(String line) {
		String[] meta = line.substring(0, line.indexOf("!")).split(":", -1);
		int n = Integer.parseInt(meta[0]);
		if(n == 0 && meta[1].equals("")) return new VisualCube[]{  };
		VisualCube[] cubes = new VisualCube[n];
		String[] blocks = meta[1].split(";");
		for(int i = 0; i < n; i++) {
			int[] data = stringArrayToIntArray(blocks[i].split(","));
			cubes[i] = new VisualCube(data[0], data[1], data[2], data[3]);
		}
		return cubes;
	}

	public void flushInput() {
		this.port.read(this.port.getBytesReceived());
	}
	
	public void reset() {
		this.flushInput();
		this.buffer.clear();
		this.setCubes(new VisualCube[]{});
	}
	
	public static int[] stringArrayToIntArray(String[] sA) {
		int[] iA = new int[sA.length];
		for(int i = 0; i < sA.length; i++)
			iA[i] = Integer.parseInt(sA[i]);
		return iA;
	}
	
	public static VisualCube findBestX() {
		int offset = 0;
		int centerX = RobotMap.PIXY_MAX_X / 2, centerY = RobotMap.PIXY_MAX_Y / 2;
		while(offset < (RobotMap.PIXY_MAX_X / 2)) {
			for(VisualCube c : Robot.cubes)
				if(c.containsY(centerY) && (c.containsX(centerX - offset) || c.containsX(centerX + offset)))
					return c;
			offset += 1;
		}
		return null;
	}

}