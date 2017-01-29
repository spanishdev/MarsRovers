package com.jordic.marsrover;

/**
 * 
 * This class represents a Mars Rover vehicle
 * 
 * @author Jordi Català
 *
 */
public class MarsRover {

	public static enum Orientation {
		NORTH, EAST, WEST, SOUTH
	}

	// Coordinates
	private int x, y;

	// The orientation where the Mars Rover is looking
	private Orientation orientation;

	private Plateau plateau;
	
	//These will be the movements which will execute the rover
	private String movementCommands;
	
	/**
	 * Creates a new Mars Rover, deployed in a position, facing a compas cardinal point
	 * @param plateau The Plateau where the Mars Rover is
	 * @param deployX Coordinate X of the Plateau
	 * @param deployY Coordinate Y of the Plateau
	 * @param orientationString Orientation as string (N => NORTH, S => SOUTH, E => EAST, W=> WEST)
	 */
	public MarsRover(Plateau plateau, int deployX, int deployY, String orientationString) {
		this.plateau=plateau;
		x = deployX;
		y = deployY;
		orientation = stringToOrientation(orientationString);
		movementCommands="";
	}

	/**
	 * Converts a Compass String to a Orientation enum
	 * 
	 * @param compass
	 *            String which represents the Cardinal Point ("N" => NORTH, "E"
	 *            => EAST, "W" => WEST, "S" => SOUTH)
	 * @return The Orientation (NORH, SOUTH, EAST, WEST)
	 */
	public Orientation stringToOrientation(String compass) {
		switch (compass) {
		case "N":
			return Orientation.NORTH;
		case "E":
			return Orientation.EAST;
		case "W":
			return Orientation.WEST;
		case "S":
			return Orientation.SOUTH;
		default:
			return Orientation.NORTH;
		}
	}

	/**
	 * We change the Orientation to its String value
	 * @return The orientation as String
	 */
	public String orientationToString() {
		switch (orientation) {
		case NORTH:
			return "N";

		case EAST:
			return "E";

		case WEST:
			return "W";

		case SOUTH:
			return "S";

		default:
			return "N";
		}

	}

	/**
	 * Rotates the Mars Rover to the left
	 */
	public void rotateLeft() {
		switch (orientation) {
		case NORTH:
			orientation = Orientation.WEST;
			break;

		case EAST:
			orientation = Orientation.NORTH;
			break;

		case WEST:
			orientation = Orientation.SOUTH;
			break;

		case SOUTH:
			orientation = Orientation.EAST;
			break;
		}
	}

	/**
	 * Rotates the Mars Rover to the right
	 */
	public void rotateRight() {
		switch (orientation) {
		case NORTH:
			orientation = Orientation.EAST;
			break;

		case EAST:
			orientation = Orientation.SOUTH;
			break;

		case WEST:
			orientation = Orientation.NORTH;
			break;

		case SOUTH:
			orientation = Orientation.WEST;
			break;
		}
	}

	/**
	 * It calculates where the rover will move, taking into account the place
	 * where it is, and the orientation.
	 * 
	 * The int array has 2 dimensions: 0 is within the Horizontal Axis, and 1 is
	 * in the Vertical Axis
	 * 
	 * @return The hypothetical next place to move (it must be checked that the
	 *         rover can move there)
	 */
	public int[] getNextCoordinatesToMove() {
		// By default, the next place to move is staying where the rover is.
		int[] coordinateToMove = new int[] { x, y };

		switch (orientation) {
		case NORTH:
			coordinateToMove[1] += 1;
			break;

		case EAST:
			coordinateToMove[0] += 1;
			break;

		case WEST:
			coordinateToMove[0] -= 1;
			break;

		case SOUTH:
			coordinateToMove[1] -= 1;
			break;
		}

		return coordinateToMove;
	}

	/**
	 * Executes the movement command of the Rover. It moves based on its current orientation
	 */
	public void move()
	{
		int[] nextCell = getNextCoordinatesToMove();
		
		//If the rover can move, then we update the coordinates and the
		//plateau cell's status
		if(plateau.canMove(nextCell[0], nextCell[1]))
		{
			x=nextCell[0];
			y=nextCell[1];
		}
	}

	/**
	 * We override the method "toString" and we set that it will print the position
	 * and orientation of the robot. 
	 * 
	 * Example: It will print 3 1 N when the robot is in the coordinates (3,1) of the grid
	 * and N when the robot is facing the North
	 */
	@Override
	public String toString() {
		return x + " " + y + " " + orientationToString();
	}

	/**
	 * It executes all the rover movements
	 */
	public void executeMovement() {
		for(int i=0; i<movementCommands.length(); i++)
		{
			char nextMove = movementCommands.charAt(i);
			
			if(nextMove=='L') rotateLeft();
			else if(nextMove=='R') rotateRight();
			else if(nextMove=='M') move();
			
		}
	}

	/**
	 * It will set the movements which will do the robot
	 * @param movementCommands Movements described as a string
	 */
	public void setMovementCommands(String movementCommands) {
		this.movementCommands=movementCommands;
	}
	
	//GETTERS AND SETTERS 
	public Orientation getOrientation() {
		return orientation;
	}
		
	public Plateau getPlateau()
	{
		return plateau;
	}
}
