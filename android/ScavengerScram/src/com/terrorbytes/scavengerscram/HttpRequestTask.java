package com.terrorbytes.scavengerscram;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.NameValuePair;

import android.os.AsyncTask;
import android.util.Log;

import com.terrorbytes.scavengerscram.io.ScavengerScramIOUtils;
import com.terrorbytes.scavengerscram.net.ScavengerScramHttpUtils;

/**
 * Asynchronous task to send an HTTP Request in a background thread
 * 
 * @author Anthony
 * 
 */
public abstract class HttpRequestTask<P,O,A> extends AsyncTask<P,O,A>
{
	private static final String DEBUG_TAG = "com.scavengerhack.debug";
	private static final int DEFAULT_CONNECTION_TIMEOUT = 120000;
	
	public static final String HTTP_GET = "GET";
	public static final String HTTP_PUT = "PUT";
	public static final String HTTP_POST = "POST";
	
	public final String httpPost(String url, List<NameValuePair> params) throws IOException
	{
		InputStream in = null;
		OutputStream out = null;
		HttpURLConnection conn = null;
		
		try 
		{
			String data = ScavengerScramHttpUtils.convertToParamString(params);
			
			// Setup connection
			conn = (HttpURLConnection) new URL(url).openConnection();
			//conn.setReadTimeout(DEFAULT_CONNECTION_TIMEOUT /* milliseconds */);
			conn.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT /* milliseconds */);
			conn.setRequestMethod(HTTP_POST);
			conn.setDoOutput(true); // Needed or we will get a ProtocolException when trying to write
			conn.setFixedLengthStreamingMode(data.getBytes().length); // Set length of data that we are sending to the server
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			// Connect
			//conn.connect();
			
			// Write POST data
            out = new BufferedOutputStream(conn.getOutputStream());
            PrintWriter writer = new PrintWriter(out);
            writer.print(data); 
            writer.close();
			
			// log response for debugging
			Log.d(DEBUG_TAG, "The response message: " + conn.getResponseMessage());
			
			// Convert the InputStream into a string
			in = new BufferedInputStream(conn.getInputStream());
			return ScavengerScramIOUtils.readStreamToString(in);
		} 
		finally 
		{
			// Close input stream
			try { if (in != null) in.close();}
			catch(IOException ioe){/* Do Nothing */}
			
			// Close output stream
			try{ if (out != null) out.close(); }
			catch(IOException ioe){/* Do Nothing */}
			
			// Disconnect
			conn.disconnect();
		}
	}
	
}

