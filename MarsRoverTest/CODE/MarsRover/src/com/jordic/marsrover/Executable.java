package com.jordic.marsrover;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Jordi Català
 *
 */
public class Executable {
	
	 //Plateau is a global variable, due that we will need it in several parts of the program
	static Plateau plateau = null;
	
	public static void main(String[] args) {
		System.out.println("Please, write the path of the input file:");

		Scanner scan = new Scanner(System.in);

		String path = scan.nextLine();

		String input = "";

		//We try to read the file whose path is written in the console. 
		//If the path written equals "exit", it means that the programm must
		//stop running
		do {
			if (!path.equalsIgnoreCase("exit")) {
				try {
					input = readFile(path);
				} catch (IOException e) {
					System.out.println("Invalid path, please write it again:");
					path = scan.nextLine();
				}
			}
		} while (input.isEmpty() && !path.equalsIgnoreCase("exit"));

		//If we found a file, we proceed to execute the program
		if (!path.equalsIgnoreCase("exit")) {
			
			//First we load the whole data
			loadMarsRoverProgram(input);
			
			if(plateau!=null)
			{
				//Second, we execute the program
				plateau.playMarsRovers();
				
				//And lastly, we show the results
				System.out.println(plateau.getRoverResults());
				
				System.out.println("Press Enter to finish...");
				
				scan.nextLine();
			}
			
		} 
		else
		{
			System.out.println("Bye!");
		}
		
		scan.close();
			
	}

	/**
	 * We read the file with the path passed by parameter. Then we load the wo
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String readFile(String path) throws IOException {

		System.out.println("Reading input...");

		StringBuilder sringBuilder = new StringBuilder();

		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		try {
			String line = "";

			while ((line = bufferedReader.readLine()) != null) {
				sringBuilder.append(line);
				sringBuilder.append("\n");
			}
		} finally {
			bufferedReader.close();
		}
		return sringBuilder.toString();
	}

	/**
	 * Based on the input extracted from the file, we build the whole program.
	 * 
	 * @param input
	 *            Content of the input file as text
	 */
	private static void loadMarsRoverProgram(String input) {

		System.out.println("Loading...");

		String[] lines = input.split("\n");

		int index = 0;
		
		while (index < lines.length) {
			String line = lines[index].trim();

			// If Plateau has not been initialized, we know that the first
			// line which is not empty will be to initialize it
			if (plateau == null) {
				int rows = Utils.stringToInt(line.split("x")[0], 1);
				int columns = Utils.stringToInt(line.split("x")[1], 1);
				plateau = new Plateau(rows, columns);
				index++;
				continue;
			}
			// The rest of the lines are Rover parameters.
			else {

				int deployX = Utils.stringToInt(line.split("\\s+")[0], 0);
				int deployY = Utils.stringToInt(line.split("\\s+")[1], 0);
				String orientationString = line.split("\\s+")[2];

				MarsRover rover = new MarsRover(plateau, deployX, deployY, orientationString);

				String line2 = lines[index + 1].trim();

				rover.setMovementCommands(line2);

				plateau.addRover(rover);

				index += 2;
			}

		}

		System.out.println("Loading Complete...");
	}

}
