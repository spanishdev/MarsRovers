package com.jordic.marsrover.tests;


import static org.junit.Assert.*;

import org.junit.Test;

import com.jordic.marsrover.MarsRover;
import com.jordic.marsrover.Plateau;

/**
 * 
 * @author Jordi Català
 *
 */
public class MarsRoverTest {

	
	@Test
	public void creationTest() {
		MarsRover rover = new MarsRover(null,2,3,"E");
		
		assertEquals("2 3 E", rover.toString());
		
	}
	
	@Test
	public void rotationTest()
	{
		MarsRover rover = new MarsRover(null,2,3,"E");
		
		//ROTATE LEFT
		assertEquals(MarsRover.Orientation.EAST, rover.getOrientation());
		rover.rotateLeft();
		assertEquals(MarsRover.Orientation.NORTH, rover.getOrientation());
		rover.rotateLeft();
		assertEquals(MarsRover.Orientation.WEST, rover.getOrientation());
		rover.rotateLeft();
		assertEquals(MarsRover.Orientation.SOUTH, rover.getOrientation());
		
		//ROTATE RIGHT
		assertEquals(MarsRover.Orientation.SOUTH, rover.getOrientation());
		rover.rotateRight();
		assertEquals(MarsRover.Orientation.WEST, rover.getOrientation());
		rover.rotateRight();
		assertEquals(MarsRover.Orientation.NORTH, rover.getOrientation());
		rover.rotateRight();
		assertEquals(MarsRover.Orientation.EAST, rover.getOrientation());
	}
	
	@Test
	public void movementTest()
	{
		Plateau plateau = new Plateau(5, 5);
		MarsRover rover = new MarsRover(plateau,3,3,"E");
		plateau.addRover(rover);
		
		//Moving to the east
		rover.move();
		assertEquals("4 3 E", rover.toString());
		rover.move();
		assertEquals("5 3 E", rover.toString());
		rover.move();
		assertEquals("5 3 E", rover.toString());
		
		//Moving to the south
		rover.rotateRight();
		rover.move();
		assertEquals("5 2 S", rover.toString());
		rover.move();
		assertEquals("5 1 S", rover.toString());
		rover.move();
		assertEquals("5 0 S", rover.toString());
		rover.move();
		assertEquals("5 0 S", rover.toString());
		
		//Moving to the west
		rover.rotateRight();
		rover.move();
		assertEquals("4 0 W", rover.toString());
		rover.move();
		rover.move();
		rover.move();
		assertEquals("1 0 W", rover.toString());
		rover.move();
		assertEquals("0 0 W", rover.toString());
		rover.move();
		assertEquals("0 0 W", rover.toString());
		
		//Moving to the north
		rover.rotateRight();
		rover.move();
		assertEquals("0 1 N", rover.toString());
		rover.move();
		rover.move();
		rover.move();
		assertEquals("0 4 N", rover.toString());
		rover.move();
		assertEquals("0 5 N", rover.toString());
		rover.move();
		assertEquals("0 5 N", rover.toString());
		
	}
	
	@Test
	public void testRoverCommands()
	{
		Plateau plateau = new Plateau(5, 5);
		MarsRover rover = new MarsRover(plateau,3,3,"N");
		plateau.addRover(rover);
		
		rover.setMovementCommands("");
		rover.executeMovement();
		assertEquals("3 3 N", rover.toString());
		
		rover.setMovementCommands("LMMRMMRM");
		rover.executeMovement();
		assertEquals("2 5 E", rover.toString());
	}

}
