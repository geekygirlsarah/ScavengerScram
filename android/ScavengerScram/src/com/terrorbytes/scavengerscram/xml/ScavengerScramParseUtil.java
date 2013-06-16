package com.terrorbytes.scavengerscram.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import com.terrorbytes.scavengerscram.model.Game;
import com.terrorbytes.scavengerscram.model.UserLogin;

public class ScavengerScramParseUtil
{	
	private static final String BASE = "//ScavengerScram";
	private static final String USERLOGIN_AUTHRESULT_EXPR = BASE + "/LoginAuth";
	private static final String USERLOGIN_ID_EXPR = BASE + "/player_id";
	private static final String USERLOGIN_NAME_EXPR = BASE + "/name";
	
	private static final String GAME_GAMEID_EXPR = BASE + "/game/name";
	private static final String GAME_NAME_EXPR = BASE + "/game/description";
	private static final String GAME_DESCRIPTION_EXPR = BASE + "/game/gamemaster";
	private static final String GAME_GAMECODE_EXPR = BASE + "/game/code";
	private static final String GAME_LOCKED_EXPR = BASE + "/game/locked";
	private static final String GAME_ENDTIME_EXPR = BASE + "/game/start_time";
	private static final String GAME_START_EXPR = BASE + "/game/end_time";
	
	public static Date timestampToDate(String s)
	{
		if(s != null && !s.trim().isEmpty())
		{
			Long parseResult = Long.parseLong(s);
		    if(parseResult != null)
		    {
		    	return new Date( parseResult );
		    }
		}
		
		return null;
	}
	
	public static Game toGame(String xml) throws XPathExpressionException
	{
		XPath xpath = XPathFactory.newInstance().newXPath();
		Integer playerId = (Integer) xpath.evaluate(GAME_GAMEID_EXPR, new StringReader(xml), XPathConstants.NUMBER);
		String name = (String) xpath.evaluate(GAME_NAME_EXPR, new StringReader(xml), XPathConstants.STRING);
		String description = (String) xpath.evaluate(GAME_DESCRIPTION_EXPR, new StringReader(xml), XPathConstants.STRING);
		String gameCode = (String) xpath.evaluate(GAME_GAMECODE_EXPR, new StringReader(xml), XPathConstants.STRING);
		Boolean locked = (Boolean) xpath.evaluate(GAME_LOCKED_EXPR, new StringReader(xml), XPathConstants.BOOLEAN);
		String startDateStr = (String) xpath.evaluate(GAME_START_EXPR, new StringReader(xml), XPathConstants.STRING);
		String endDateStr = (String) xpath.evaluate(GAME_ENDTIME_EXPR, new StringReader(xml), XPathConstants.STRING);		
		return new Game(playerId == null ? -1 : playerId, name, description, gameCode, locked, timestampToDate(startDateStr), timestampToDate(endDateStr));		
	}
	
	public static UserLogin toUserLogin(String xml) throws XPathExpressionException
	{
		XPath xpath = XPathFactory.newInstance().newXPath();
		String loginAuth = (String) xpath.evaluate(USERLOGIN_AUTHRESULT_EXPR, new StringReader(xml), XPathConstants.STRING);
		String name = (String) xpath.evaluate(USERLOGIN_NAME_EXPR, new StringReader(xml), XPathConstants.STRING);
		Integer playerId = (Integer) xpath.evaluate(USERLOGIN_ID_EXPR, new StringReader(xml), XPathConstants.NUMBER);
		
		UserLogin login = new UserLogin();
		login.setAuthResult(loginAuth);
		login.setName(name);
		login.setId(playerId != null ? playerId.intValue() : -1);
		return login;	
	}
	
	public static UserLogin toPlayer(String xml) throws XPathExpressionException
	{return null;	}

}
