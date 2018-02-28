package org.usfirst.frc.team4509.robot.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.usfirst.frc.team4509.robot.VisualCube;
import org.usfirst.frc.team4509.robot.subsystems.CameraSubsystem;


class CubeFormDataTest {

	@Test
	void testPass() {
		assertTrue(equal(CameraSubsystem.formData("0:!"), new VisualCube[]{}));
		assertTrue(equal(CameraSubsystem.formData("1:24,2,73,83!"), new VisualCube[]{ new VisualCube(24, 2, 73, 83) }));
		assertTrue(equal(CameraSubsystem.formData("2:32,43,62,16;52,62,83,25!"), new VisualCube[]{ new VisualCube(32, 43, 62, 16), new VisualCube(52, 62, 83, 25) }));
		assertTrue(equal(CameraSubsystem.formData("01:24,2,003,83!"), new VisualCube[]{ new VisualCube(24, 2, 3, 83) })); // leading zeroes
	}

	@Test
	void testFail() {
		assertFalse(equal(CameraSubsystem.formData("1:24,2,72,83!"), new VisualCube[]{ new VisualCube(25, 2, 71, 83) })); // incorrect attributes
	}
	
	static boolean equal(VisualCube[] c1, VisualCube[] c2) {
		for(int i = 0; i < c1.length; i++)
			if(!c1[i].equals(c2[i])) return false;
		return true;
	}

}
