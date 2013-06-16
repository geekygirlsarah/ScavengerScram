package com.terrorbytes.scavengerscram.xml;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.terrorbytes.scavengerscram.model.Clue;
import com.terrorbytes.scavengerscram.model.Game;
import com.terrorbytes.scavengerscram.model.UserLogin;

public class ScavengerScramParseUtil
{	
	private static final String BASE = "//ScavengerScram";
	
	// User Login
	private static final String USERLOGIN_AUTHRESULT_EXPR = BASE + "/LoginResult/LoginAuth";
	private static final String USERLOGIN_ID_EXPR         = BASE + "/LoginResult/player_id";
	private static final String USERLOGIN_NAME_EXPR       = BASE + "/LoginResult/name";
	
	// Game
	private static final String GAME_LIST_EXPR        = "ScavengerScram/Games/Game";
	private static final String GAME_GAMEID_EXPR      = "//game_id";
	private static final String GAME_NAME_EXPR        = "//name";
	private static final String GAME_DESCRIPTION_EXPR = "//description";
	private static final String GAME_GAMECODE_EXPR    = "//code";
	private static final String GAME_LOCKED_EXPR      = "//locked";
	private static final String GAME_ENDTIME_EXPR     = "//start_time";
	private static final String GAME_START_EXPR       = "//end_time";
	
	// Player
	private static final String PLAYER_PLAYERID_EXPR  = BASE + "/CreatePlayer/player_id";
	
	// Clue
	private static final String CLUE_LIST_EXPR        = "ScavengerScram/Clues/Clue";
	private static final String CLUE_CLUEID_EXPR      = "//clue_id";
	private static final String CLUE_NUMBER_EXPR      = "//number";
	private static final String CLUE_TITLE_EXPR       = "//title";
	private static final String CLUE_DESCRIPTION_EXPR = "//description";
	private static final String CLUE_GAMEID_EXPR      = "//game_id";
	
	private static String clean(String xml)
	{
		int start = xml.indexOf('<');
		int end = xml.lastIndexOf('>');
		return xml.substring(start == -1 ? 0 : start, end == -1 ? xml.length() : end + 1);
	}
	
	public static Date timestampToDate(String s)
	{
		if(s != null && !s.trim().isEmpty())
		{
			try{ new Date( Long.parseLong(s) );}
			catch(NumberFormatException e){return null;}
		}
		
		return null;
	}
	
	public static List<Clue> parseToClues(String xml)
	{
		List<Clue> clueList = new ArrayList<Clue>();
		
		if(xml != null && !xml.isEmpty())
		{	
			xml = clean(xml);
			try 
			{
				XPath xpath = XPathFactory.newInstance().newXPath();
				NodeList nodes = (NodeList) xpath.evaluate(CLUE_LIST_EXPR, new InputSource(new StringReader(xml)), XPathConstants.NODESET);
				
				for(int i = 0; i < nodes.getLength(); i++) clueList.add(toClue(nodes.item(i)));
			} 
			catch (XPathExpressionException e) {}
		}
		
		return clueList;
	}
	
	public static List<Game> parseToGames(String xml)
	{
		List<Game> gameList = new ArrayList<Game>();
		
		if(xml != null && !xml.isEmpty())
		{	
			xml = clean(xml);
			try 
			{
				XPath xpath = XPathFactory.newInstance().newXPath();
				NodeList nodes = (NodeList) xpath.evaluate(GAME_LIST_EXPR, new InputSource(new StringReader(xml)), XPathConstants.NODESET);
				
				for(int i = 0; i < nodes.getLength(); i++) gameList.add(toGame(nodes.item(i)));
			} 
			catch (XPathExpressionException e) {}
		}
		
		return gameList;
	}
	
	private static Clue toClue(Node node) throws XPathExpressionException
	{
		// Clue ID
		String clueIdStr = parseQuietly(node,CLUE_CLUEID_EXPR);
		
		Integer clueId = -1;	
		try{ clueId = Integer.parseInt(clueIdStr);}
		catch(NumberFormatException e){/*IGNORE*/}
		
		// Clue Number
		String clueNumberStr = parseQuietly(node,CLUE_NUMBER_EXPR);
		
		Integer clueNumber = -1;	
		try{ clueNumber = Integer.parseInt(clueNumberStr);}
		catch(NumberFormatException e){/*IGNORE*/}
		
		// Game ID
		String gameIdStr = parseQuietly(node, CLUE_GAMEID_EXPR);
		
		Integer gameId = -1;	
		try{ gameId = Integer.parseInt(gameIdStr);}
		catch(NumberFormatException e){/*IGNORE*/}
		
		return new Clue( 
				clueId, 
				clueNumber, 
				parseQuietly(node,CLUE_TITLE_EXPR), 
				parseQuietly(node,CLUE_DESCRIPTION_EXPR), 
				gameId);		
	}
	
	private static Game toGame(Node node) throws XPathExpressionException
	{
		String gameIdStr = parseQuietly(node,GAME_GAMEID_EXPR);		
		Integer gameId = -1;
		
		try{ gameId = Integer.parseInt(gameIdStr);}
		catch(NumberFormatException e){/*IGNORE*/}
		
		return new Game(gameId, 
				parseQuietly(node,GAME_NAME_EXPR),
				parseQuietly(node,GAME_DESCRIPTION_EXPR), 
				parseQuietly(node,GAME_GAMECODE_EXPR), 
				Boolean.parseBoolean(parseQuietly(node,GAME_LOCKED_EXPR)), 
				timestampToDate(parseQuietly(node, GAME_START_EXPR)), 
				timestampToDate(parseQuietly(node, GAME_ENDTIME_EXPR)));		
	}
	
	public static String parseQuietly(Node node, String expr)
	{
		try {return (String) XPathFactory.newInstance().newXPath().evaluate(expr, node, XPathConstants.STRING);} 
		catch (XPathExpressionException e) {return "";}
	}
	
	public static String parseQuietly(String xml, String expr)
	{
		try {return (String) XPathFactory.newInstance().newXPath().evaluate(expr, new InputSource(new StringReader(xml)), XPathConstants.STRING);} 
		catch (XPathExpressionException e) {return "";}
	}
	
	/**
	 * Parse XML 
	 * 
	 * @param xml
	 * @return
	 * @throws XPathExpressionException
	 */
	public static UserLogin toUserLogin(String xml) throws XPathExpressionException
	{
		if(xml == null || xml.isEmpty()) return new UserLogin();
		
		xml = clean(xml);
		
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
	
	/**
	 * Method to parse player ID from XML
	 * 
	 * 		<CreatePlayer>
	 * 			<player_id>1234</player_id>
	 * 		</CreatePlayer>
	 * 
	 * @param xml
	 * @return
	 * @throws XPathExpressionException
	 */
	public static String parsePlayerID(String xml) throws XPathExpressionException
	{
		return parseQuietly(xml, PLAYER_PLAYERID_EXPR);
	}

}
