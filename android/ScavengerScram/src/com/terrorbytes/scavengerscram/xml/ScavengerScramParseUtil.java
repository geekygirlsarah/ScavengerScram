package com.terrorbytes.scavengerscram.xml;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import com.terrorbytes.scavengerscram.model.Game;

public class ScavengerScramParseUtil
{
	XPath xpath = XPathFactory.newInstance().newXPath();
	
	private static final String USERLOGIN_AUTHRESULT_EXPR = "//";
	private static final String USERLOGIN_ID_EXPR = "";
	private static final String USERLOGIN_NAME_EXPR = "";
	
	public static Game toGame(String xml)
	{
		//XPath xpath = XPathFactory.newInstance().newXPath();
		return null;	
	}
	
	public static Game toUserLogin(String xml)
	{
		XPath xpath = XPathFactory.newInstance().newXPath();
		return null;	
	}

}
