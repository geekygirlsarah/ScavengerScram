package com.terrorbytes.scavengerscram.xml;

import java.io.StringReader;
import java.util.Date;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

import com.terrorbytes.scavengerscram.model.Game;
import com.terrorbytes.scavengerscram.model.UserLogin;

public class ScavengerScramParseUtil
{	
	private static final String BASE = "//ScavengerScram";
	private static final String USERLOGIN_AUTHRESULT_EXPR = BASE + "/LoginResult/LoginAuth";
	private static final String USERLOGIN_ID_EXPR = BASE + "/LoginResult/player_id";
	private static final String USERLOGIN_NAME_EXPR = BASE + "/LoginResult/name";
	
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
			try{ new Date( Long.parseLong(s) );}
			catch(NumberFormatException e){return null;}
		}
		
		return null;
	}
	
	public static Game toGame(String xml) throws XPathExpressionException
	{
		String gameIdStr = parseQuietly(xml,GAME_GAMEID_EXPR);		
		Integer gameId = -1;
		
		try{ gameId = Integer.parseInt(gameIdStr);}
		catch(NumberFormatException e){/*IGNORE*/}
		
		return new Game(gameId, 
				parseQuietly(xml,GAME_NAME_EXPR),
				parseQuietly(xml,GAME_DESCRIPTION_EXPR), 
				parseQuietly(xml,GAME_GAMECODE_EXPR), 
				Boolean.parseBoolean(parseQuietly(xml,GAME_LOCKED_EXPR)), 
				timestampToDate(parseQuietly(xml, GAME_START_EXPR)), 
				timestampToDate(parseQuietly(xml, GAME_ENDTIME_EXPR)));		
	}
	
	public static String parseQuietly(String xml, String expr)
	{
		try {return (String) XPathFactory.newInstance().newXPath().evaluate(expr, new InputSource(new StringReader(xml)), XPathConstants.STRING);} 
		catch (XPathExpressionException e) {return "";}
	}
	
	public static UserLogin toUserLogin(String xml) throws XPathExpressionException
	{
		String playerIdStr = parseQuietly(xml,USERLOGIN_ID_EXPR);		
		Integer playerId = -1;
		
		try{ playerId = Integer.parseInt(playerIdStr);}
		catch(NumberFormatException e){/*IGNORE*/}
		
		UserLogin login = new UserLogin();
		login.setAuthResult(parseQuietly(xml,USERLOGIN_AUTHRESULT_EXPR));
		login.setName(parseQuietly(xml,USERLOGIN_NAME_EXPR));
		login.setId(playerId.intValue());
		
		return login;	
	}
	
	public static UserLogin toPlayer(String xml) throws XPathExpressionException
	{return null;	}

}
