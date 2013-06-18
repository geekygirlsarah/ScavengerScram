package com.terrorbytes.scavengerscram.net;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.protocol.HTTP;

import android.graphics.Bitmap;
import android.util.Base64;

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
	
	public static String convertToBase64(Bitmap bitmap)
	{		
		// Encode bitmap to String
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
		return Base64.encodeToString(bao.toByteArray(), Base64.DEFAULT);
	}
	
}

