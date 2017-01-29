package com.jordic.marsrover;

import java.util.ArrayList;

/**
 * 
 * @author Jordi Català
 *
 */
public class Plateau {

	private int rows, columns;

	private ArrayList<MarsRover> marsRovers;

	/**
	 * Creates a new Plateau with the size set by parameter
	 * @param rows Number of rows which will have the grid
	 * @param columns Number of columns which will have the grid
	 */
	public Plateau(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		marsRovers = new ArrayList<>();
	}

	/**
	 * Adds a new MarsRover to the Plateau
	 * 
	 * @param rover
	 *            Mars Rover to add
	 */
	public void addRover(MarsRover rover) {
		marsRovers.add(rover);
	}
	
	/**
	 * Returns the number of Mars Rovers which are on the Plateau
	 * @return How many Mars Rovers are in the Plateau
	 */
	public int getRoverCount()
	{
		return marsRovers.size();
	}

	/**
	 * A Rover can move only if the position is within the grid
	 * 
	 * @param x
	 *            Coordinate X
	 * @param y
	 *            Coordinate Y
	 * @return True if can move, False otherwise
	 */
	public boolean canMove(int x, int y) {
		return (x >= 0 && x <= columns && y >= 0 && y <= rows);
	}


	/**
	 * Makes the Rover with index roverIndex execute its movements.
	 * @param roverIndex Index of the rover
	 */
	public void moveRover(int roverIndex) {
		marsRovers.get(roverIndex).executeMovement();
	}
	
	/**
	 * Makes all the Mars Rovers execute its movements respectively.
	 */
	public void playMarsRovers()
	{
		for(int i=0; i<getRoverCount(); i++)
		{
			moveRover(i);
		}
	}
	
	/**
	 * Generates the Output of the programm, according to the positions of each Mars Rover
	 * and Orientation
	 * 
	 * @return The output results as string
	 */
	public String getRoverResults()
	{
		StringBuilder resultBuilder = new StringBuilder();
		
		for(int i=0; i<marsRovers.size(); i++)
		{
			//We add 1 to "i", because the Rover number begin with 1 instead of 0
			resultBuilder.append("Rover "+(i+1)+" position: ");
			resultBuilder.append(marsRovers.get(i).toString());
			resultBuilder.append('\n');
		}
		
		return resultBuilder.toString();
	}

}
