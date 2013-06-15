package com.terrorbytes.scavengerscram.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.protocol.HTTP;

/**
 * Utility for HTTP requests
 * 
 * @author Anthony
 *
 */
public class ScavengerScramHttpUtils 
{
	/**
	 * Converts a List of name value pairs into a HTTP parameter string.
	 * 
	 * @param nameValuePairs
	 * @return
	 */
	public static String convertToParamString(List<NameValuePair> nameValuePairs)
	{
		if(nameValuePairs == null) return "";
		
		StringBuilder sb = new StringBuilder();
		
		try 
		{
			String name;
			for(NameValuePair entry : nameValuePairs)
			{
				if((name = entry.getName()) != null)
				{
						sb.append("&").
							append(name).
							append("=").
							append((entry.getValue() != null) ? URLEncoder.encode(entry.getValue(), HTTP.UTF_8) : "");
				}
			}
		}
		catch (UnsupportedEncodingException e) 
		{
			// Ignore
		}
		
		return sb.deleteCharAt(0).toString();
	}

}

