package org.usfirst.frc.team4509.robot.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.usfirst.frc.team4509.robot.subsystems.CameraSubsystem;


class CubeCheckDataTest {
	
	@Test
	void testPass() {
		assertTrue(CameraSubsystem.checkData("0:!"));
		assertTrue(CameraSubsystem.checkData("1:5,28,15,32!"));
		assertTrue(CameraSubsystem.checkData("2:48,2,94,28;92,23,53,73!"));
		assertTrue(CameraSubsystem.checkData("001:04,34,63,72!")); // leading zeroes
		assertTrue(CameraSubsystem.checkData("1:23453,94,236,7435!")); // large numbers
	}
	
	@Test
	void testFail() {
		assertFalse(CameraSubsystem.checkData("0!"));
		assertFalse(CameraSubsystem.checkData("!:1,3,54,21!"));                  // premature end character
		assertFalse(CameraSubsystem.checkData(":39,25,63,62!"));                 // no list length
		assertFalse(CameraSubsystem.checkData("92:94,23,64,21;235,52,63,73!"));  // incorrect list length
		assertFalse(CameraSubsystem.checkData("2:94,32,53,25,63;34,73,83,34!")); // too many attributes
		assertFalse(CameraSubsystem.checkData("2:94,32,63;34,73,83,34!"));       // not enough attributes
		assertFalse(CameraSubsystem.checkData("1:23,52,a,23!"));                 // non-integer
		assertFalse(CameraSubsystem.checkData(""));                              // empty string
		assertFalse(CameraSubsystem.checkData("1:92,43,53,63;!"));               // seperator not used correctly
	}

}
