package com.terrorbytes.scavengerscram.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;

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
	
	public static Game toGame(String xml)
	{
		//XPath xpath = XPathFactory.newInstance().newXPath();
		return null;	
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

}
