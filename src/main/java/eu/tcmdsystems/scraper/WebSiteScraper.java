package eu.tcmdsystems.scraper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.JsonObject;

public class WebSiteScraper {
	private final static Logger logger = Logger.getLogger(WebSiteScraper.class
			.getName());

	Document webdoc; // The HTML document representing a web-page

	public WebSiteScraper(String url) {
		// TODO Auto-generated constructor stub
		try {

			webdoc = Jsoup.connect(url).get();
			logger.log(Level.INFO, "Document Nodename: " + webdoc.nodeName());// #document

		}

		catch (IOException e) {
			logger.log(Level.WARNING, "Error getting data: " + e.getMessage());
			String html = "<html><head><title>Error getting data!</title></head>"
					+ "<body><p>" + e.getMessage() + "</p></body></html>";
			webdoc = Jsoup.parse(html);
		}
	}

	public int getElementsSize() {
		int elementsSize = webdoc.getAllElements().size();

		// logger.log(Level.INFO,
		// "elementsSize: " + Integer.toString(elementsSize));
		return elementsSize;
	}

	

	
	public JsonObject getWebPageLinks() {
		/**
		 * @return an JsonObject containing all the web links from given page
		 */
		JsonObject rez = new JsonObject();
		// Get links from document object.
		Elements links = webdoc.select("a[href]");
		// logger.log(Level.INFO,"Links : "+links.toString());
		// Iterate links and get link attributes.
		for (Element link : links) {
			rez.addProperty(link.text(), link.attr("href"));
		}
		// let's keep this in JSon format and return the Json object
		return rez;
	}

	
	public Elements getLinkElements() {

		

		Elements links = webdoc.select("a");
		// logger.log(Level.INFO, "links.size: "+links.size());
		
		/*links = webdoc.select("a");
		for (Element link : links) {
		String attribute=link.attr("class");
		if(attribute.equalsIgnoreCase(classname)){
			logger.log(Level.INFO, "Product is: "+link.toString());
		   }
		}
		
		for (Element link : links) {
			if(link.childNodeSize()==6){
				link.child(1).text();
				for(int i=0;i<6;i++){
					logger.log(Level.INFO, "Child node "+i+": "+link.childNode(i).toString());
				}
			}
			// listing-pagination-next - for pagination
			// product-container js-product-container for products
		}
*/
		return links;

	}

	

	public Document getWebdoc() {
		return webdoc;
	}

	public void setWebdoc(Document webdoc) {
		this.webdoc = webdoc;
	}

	public String getDocumetTitle() {
		return webdoc.title();
	}

	public Charset getDocumetCharset() {
		return webdoc.charset();
	}

}
