package com.terrorbytes.scavengerscram.io;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Utility for IO
 * 
 * @author Anthony
 *
 */
public class ScavengerScramIOUtils 
{
	public static String readStreamToString(InputStream is)
	{
		Scanner s = new Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}
}

