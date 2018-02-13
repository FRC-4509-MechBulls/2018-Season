package org.usfirst.frc.team4509;

public class Cube {
	
	private int x, y, width, height;
	public static double y(int w, int h) { return (w / h); }
	
	public Cube(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public int getX()      { return this.x;      }
	public int getY()      { return this.y;      }
	public int getWidth()  { return this.width;  }
	public int getHeight() { return this.height; }
	
	public double getDistance() {
		return 0.0;
	}
	
	public String toString() {
		return "Cube : ( " + this.getX() + ", " + this.getY() + ", " + this.getWidth() + ", " + this.getHeight() + " )";
	}

}
