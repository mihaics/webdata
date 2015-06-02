package eu.tcmdsystems.scraper.emag;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import eu.tcmdsystems.scraper.WebSiteScraper;

public class LaptopScraper extends WebSiteScraper {
	
	private final static Logger logger = Logger.getLogger(LaptopScraper.class.getName());

	
	static String siteurl = "http://m.emag.ro/";
	static String pageurl = "/laptopuri/c";
	
	
	public LaptopScraper(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}
	
	public LaptopScraper(){
		super(siteurl+pageurl);
	}

	
	public String getNextPageurl(){
		return getWebdoc().getElementsByClass("pagination").select("a[href]").attr("href");		
	}
	
	public String getLaptopData(){
		Elements dataelements = getWebdoc().getElementsByClass("product-container").select("a[href]");
		Iterator<Element> it = dataelements.iterator();
		while(it.hasNext()){
			Element prod = it.next();
			//logger.log(Level.INFO,"Product data: " + prod.toString());
			String producturl = prod.attr("href");
			logger.log(Level.INFO,"Product url: "+ producturl);
			String productimage = prod.getElementsByClass("img-container").select("img").attr("data-src");
			logger.log(Level.INFO,"Product image: "+ productimage);
			String producttitle= prod.getElementsByClass("product-title").text();
			logger.log(Level.INFO,"Product title: "+ producttitle);
			String productstare= prod.getElementsByClass("stare-disp-listing").text();
			logger.log(Level.INFO,"Product status: "+ productstare);
			
			String priceold= prod.getElementsByClass("old").text();
			logger.log(Level.INFO,"Price old: "+ priceold);
			
			//daca are pret vechi/pret nou size = 2, dacă are numai pret nou, size = 1
			 int priceold_int = prod.getElementsByClass("money-int").size();
			logger.log(Level.INFO,"Price old size: "+ priceold_int);
			
			
			String pricenew= prod.getElementsByClass("price-over").text();
			logger.log(Level.INFO,"Price new: "+ pricenew);
			
			//String pricenew_int= prod.getElementsByClass("money-int").get(1).text();
			//logger.log(Level.INFO,"Price new int: "+ pricenew_int);
			
		}
		
		return null;
	}
	
	
	public static String getPageurl() {
		return pageurl;
	}

	public static void setPageurl(String pageurl) {
		LaptopScraper.pageurl = pageurl;
	}

	

}
