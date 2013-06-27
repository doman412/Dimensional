package com.makowaredev.dimensional.Misc;

import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class MakoParser extends DefaultHandler{
	
	

	private HashMap<String, XMLEntity> loaders;

	public MakoParser(HashMap<String, XMLEntity> loaders){
		this.loaders = loaders;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws IllegalArgumentException{
		XMLEntity loader = loaders.get(qName);
//		Gdx.app.log("MakoParser", "uri:"+uri+" local:"+localName+" qName:"+qName);
		if(loader != null)
			loader.onLoad(localName, attributes);
		else
			throw new IllegalArgumentException("Unexpected tag: '"+localName+"'");
		
	}
	
}
