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

import android.annotation.SuppressLint;
import com.terrorbytes.scavengerscram.model.Clue;
import com.terrorbytes.scavengerscram.model.Game;
import com.terrorbytes.scavengerscram.model.UserLogin;

@SuppressLint("DefaultLocale") 
public class ScavengerScramParseUtil
{	
	private static final String BASE = "//ScavengerScram";
	
	// User Login
	private static final String USERLOGIN_AUTHRESULT_EXPR = BASE + "/LoginResult/LoginAuth";
	private static final String USERLOGIN_ID_EXPR         = BASE + "/LoginResult/player_id";
	private static final String USERLOGIN_NAME_EXPR       = BASE + "/LoginResult/name";
	
	// Game
	private static final String GAME_LIST_EXPR        = BASE + "/Games/Game";
	private static final String GAME_GAMEID_EXPR      = "./game_id";
	private static final String GAME_NAME_EXPR        = "./name";
	private static final String GAME_DESCRIPTION_EXPR = "./description";
	private static final String GAME_GAMECODE_EXPR    = "./code";
	private static final String GAME_LOCKED_EXPR      = "./locked";
	private static final String GAME_ENDTIME_EXPR     = "./start_time";
	private static final String GAME_START_EXPR       = "./end_time";
	private static final String GAME_GAMEMASTER_EXPR  = "./gamemaster";
	
	// Clue
	private static final String CLUE_LIST_EXPR        = BASE + "/Clues/Clue";
	private static final String CLUE_CLUEID_EXPR      = "./clue_id";
	private static final String CLUE_NUMBER_EXPR      = "./number";
	private static final String CLUE_TITLE_EXPR       = "./title";
	private static final String CLUE_DESCRIPTION_EXPR = "./description";
	private static final String CLUE_GAMEID_EXPR      = "./game_id";
	
	// Answer
	private static final String ANSWER_RESULT_EXPR    = BASE + "/AnswerResult";
	
	// ShortCode
	private static final String SHORTCODE_BASE_EXPR   = BASE + "/ShortcodeResult";
	private static final String SHORTCODE_RESULT_EXPR = SHORTCODE_BASE_EXPR + "/Result";
	
	// Create Player
	private static final String CREATEPLAYER_RESULT_EXPR = BASE + "/CreatePlayer/player_id";
	
	//------------------------ EXAMPLES ------------------------//
	public static final String USERLOGIN_EXAMPLE = 
			"<ScavengerScram>" +
				"<LoginAuth>pass</LoginAuth>" +
				"<player_id>1234567</player_id>" +
				"<name>The Player</name>" +
			"</ScavengerScram>";
	
	public static final String GAMELIST_EXAMPLE = 
			"<ScavengerScram>" +
				"<Games>" + 
					"<Game>" +
						"<name>Test1</name>" +
						"<description>Test1 description</description>" +
						"<gamemaster>The Gamemaster1</gamemaster>" +
						"<code>1234gaMeCode</code>" +
						"<locked>true</locked>" +
						"<start_time>12345678931110</start_time>" +
						"<end_time>12345679931110</end_time>" +
					"</Game>" +
					"<Game>" +
						"<name>Test2</name>" +
						"<description>Test2 description</description>" +
						"<gamemaster>The Gamemaster2</gamemaster>" +
						"<code>5678gaMeCode</code>" +
						"<locked>faLse</locked>" +
						"<start_time>12345678901112</start_time>" +
						"<end_time>12345679901112</end_time>" +
					"</Game>" +
				"</Games>" + 
		  "</ScavengerScram>";
	
	public static final String CREATEPLAYER_EXAMPLE = 
			"<ScavengerScram>" +
				"<CreatePlayer>" + 
					"<player_id>123456</player_id>" +
				"</CreatePlayer>" +
			"</ScavengerScram>";
	
	public static final String CLUELIST_EXAMPLE = 
			"<ScavengerScram>" +
					"<Clues>" + 
						"<Clue>" +
							"<number>1</number>" +
							"<description>Clue1</description>" +
							"<title>This is a clue</title>" +
						"</Clue>" +
						"<Clue>" +
							"<number>2</number>" +
							"<description>Clue 2</description>" +
							"<title>This is a clue too</title>" +
						"</Clue>" +
					"</Clues>" + 
			  "</ScavengerScram>";
	
	public static final String ANSWER_EXAMPLE =
			"<ScavengerScram>" +
				"<AnswerResult> accEpt</AnswerResult>" +
			"</ScavengerScram>";
	
	public static final String SHORTCODE_EXAMPLE =
			"<ScavengerScram>" +
				"<ShortcodeResult>" + 
					"<Game>" +
						"<name>A Game</name>" +
						"<description>This is a game</description>" +
						"<gamemaster>The gamemaster</gamemaster>" +
						"<game_id>12345</game_id>" +
						"<locked>false </locked>" +
						"<start_time>12345678901112</start_time>" +
						"<end_time>1234567801112</end_time>" +
					"</Game>" +
				"</ShortcodeResult>" + 
		  "</ScavengerScram>";
	
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
			try{ return new Date( Long.parseLong(s) );}
			catch(NumberFormatException e){}
		}
		
		return null;
	}
	
	public static List<Clue> parseToClues(String xml)
	{
		List<Clue> clueList = new ArrayList<Clue>();
		
		if(xml != null && !xml.isEmpty())
		{	
			try 
			{
				xml = clean(xml);
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
			try 
			{
				XPath xpath = XPathFactory.newInstance().newXPath();
				NodeList nodes = (NodeList) xpath.evaluate(GAME_LIST_EXPR, new InputSource(new StringReader(clean(xml))), XPathConstants.NODESET);
				
				for(int i = 0; i < nodes.getLength(); i++) gameList.add(toGame(nodes.item(i)));
			} 
			catch (XPathExpressionException e) {}
		}
		
		return gameList;
	}
	
	private static Clue toClue(Node node) throws XPathExpressionException
	{	
		// Clue ID
		String clueIdStr = parseQuietly(node, CLUE_CLUEID_EXPR);
		
		Integer clueId = -1;	
		try{ clueId = Integer.parseInt(clueIdStr);}
		catch(NumberFormatException e){/*IGNORE*/}
		
		// Clue Number
		String clueNumberStr = parseQuietly(node, CLUE_NUMBER_EXPR);
		
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
				parseQuietly(node,GAME_GAMEMASTER_EXPR),
				parseQuietly(node,GAME_NAME_EXPR),
				parseQuietly(node,GAME_DESCRIPTION_EXPR),
				parseQuietly(node,GAME_GAMECODE_EXPR), 
				Boolean.parseBoolean(parseQuietly(node,GAME_LOCKED_EXPR).trim().toLowerCase()), 
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
	
	public static boolean parseForAnswerResult(String xml)
	{
		return parseForResult(clean(xml), ANSWER_RESULT_EXPR, "ACCEPT");
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
	
	public static boolean parseForShortCodeResult(String xml)
	{
		return parseForResult(clean(xml), SHORTCODE_RESULT_EXPR, "ACCEPT");
	}
	
	public static boolean parseForResult(String xml, String expr, String expected)
	{
		return parseQuietly(xml, expr).trim().equalsIgnoreCase(expected);
	}
	
	public static Game parseShortCodeForGame(String xml)
	{
		try 
		{
			XPath xpath = XPathFactory.newInstance().newXPath();
			Node node = (Node) xpath.evaluate(SHORTCODE_BASE_EXPR, new InputSource(new StringReader(clean(xml))), XPathConstants.NODE);
			return toGame(node);
		} 
		catch (XPathExpressionException e) {}
		return null;	
	}
	
	public static int parseForCreatePlayerResult(String xml)
	{	
		return parserForInt(xml, CREATEPLAYER_RESULT_EXPR, -1);
	}
	
	public static int parserForInt(String xml, String expr, int defaultValue)
	{
		try { return Integer.parseInt( parseQuietly(xml, expr) ); }
		catch(NumberFormatException e){ return defaultValue;}	
	}

}
