package com.jordic.marsrover;

/**
 * 
 * @author Jordi Català
 *
 */
public class Utils {

	/**
	 * Converts a String to a Integer. If the String cannot be converted
	 * to an Integer, it will return the default value
	 * @param string String to convert
	 * @param defaultValue Value which will be returned in case that an error occurs
	 * @return The integer converted, or the default value if an error occurred
	 */
	public static Integer stringToInt(String string, int defaultValue)
	{
		try
		{
			return Integer.valueOf(string);
		}
		catch(Exception e)
		{
			return defaultValue;
		}
	}
}
