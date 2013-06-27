package com.makowaredev.dimensional.Misc;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.badlogic.gdx.Gdx;

public class MakoLoader {
	
	private String base;
	private HashMap<String, XMLEntity> loaders;

	public MakoLoader(String base){
		this.base = base;
		this.loaders = new HashMap<String, XMLEntity>();
	}
	
	public void registerTag(String entName, XMLEntity loader){
		this.loaders.put(entName, loader);
	}
	
	public void load(String fileName){
		InputStream is = Gdx.files.internal(base+fileName).read();
		
		try{
			
			final SAXParserFactory spf = SAXParserFactory.newInstance();
			final SAXParser sp = spf.newSAXParser();
	
			final XMLReader xr = sp.getXMLReader();
			
			final MakoParser levelParser = new MakoParser(this.loaders);
			xr.setContentHandler(levelParser);

			xr.parse(new InputSource(new BufferedInputStream(is)));
			
//			XmlReader xml;
//			FileHandle hn;
			
		} catch(SAXException se){
			
		} catch(ParserConfigurationException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		} catch(IllegalArgumentException e){
			Gdx.app.error("MakoLoader", "Parse Fail: ", e);
		} finally{
			try {
				is.close();
			} catch (IOException e) {
				Gdx.app.error("MakoLoader", "Could not close InputStream", e);
			}
		}
		
		
		
		
//		this.onBeforeLoadLevel();
 
	}

}
