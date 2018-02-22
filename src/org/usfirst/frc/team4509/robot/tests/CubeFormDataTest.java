package org.usfirst.frc.team4509.robot.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.usfirst.frc.team4509.robot.Cube;
import org.usfirst.frc.team4509.robot.subsystems.CameraSubsystem;


class CubeFormDataTest {

	@Test
	void testPass() {
		assertTrue(equal(CameraSubsystem.formData("0:!"), new Cube[]{}));
		assertTrue(equal(CameraSubsystem.formData("1:24,2,73,83!"), new Cube[]{ new Cube(24, 2, 73, 83) }));
		assertTrue(equal(CameraSubsystem.formData("2:32,43,62,16;52,62,83,25!"), new Cube[]{ new Cube(32, 43, 62, 16), new Cube(52, 62, 83, 25) }));
		assertTrue(equal(CameraSubsystem.formData("01:24,2,003,83!"), new Cube[]{ new Cube(24, 2, 3, 83) })); // leading zeroes
	}

	@Test
	void testFail() {
		assertFalse(equal(CameraSubsystem.formData("1:24,2,72,83!"), new Cube[]{ new Cube(25, 2, 71, 83) })); // incorrect attributes
	}
	
	static boolean equal(Cube[] c1, Cube[] c2) {
		for(int i = 0; i < c1.length; i++)
			if(!c1[i].equals(c2[i])) return false;
		return true;
	}

}
