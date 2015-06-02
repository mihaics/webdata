package eu.tcmdsystems.scraper.emag;

import eu.tcmdsystems.scraper.WebSiteScraper;

public class LaptopScraper extends WebSiteScraper {
	static String laptopurl = "http://m.emag.ro/laptopuri/c";
	
	public LaptopScraper(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}
	
	public LaptopScraper(){
		super(laptopurl);
	}

}
