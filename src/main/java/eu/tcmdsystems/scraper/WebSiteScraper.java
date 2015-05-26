package eu.tcmdsystems.scraper;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;








public class WebSiteScraper {
	private final static Logger logger =
	          Logger.getLogger(WebSiteScraper.class.getName());
	  
	
	Document webdoc; 
	public WebSiteScraper(String url) {
		// TODO Auto-generated constructor stub
		 try { 
    		  
    		 webdoc = Jsoup.connect(url).get();   
		 } 
    		  
    	 catch (IOException e) 
    	 	{ 
    		 logger.log(Level.WARNING,"Error getting data: "+e.getMessage());
    	 	}	
	}

	public String getDocumetTitle() {
		// TODO Auto-generated method stub
		return webdoc.title();
	}
	
	public String getElementLinks(){
		JsonObject rez = new JsonObject();
		
		
		
		//Get links from document object. 
		Elements links = webdoc.select("a[href]");   
		logger.log(Level.INFO,"Links : "+links.toString());
		
		//Iterate links and print link attributes. 
		for (Element link : links) {
			
			logger.log(Level.INFO, "Link attributes: "+link.attributes().toString());
			rez.addProperty(link.text(),link.attr("href"));
					
		
		} 
		
		
		return rez.toString();
	}

}
