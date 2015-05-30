package eu.tcmdsystems.scraper.emag;

import eu.tcmdsystems.scraper.WebSiteScraper;

public class LaptopScraper extends WebSiteScraper {
	static String laptopurl = "http://www.emag.ro/laptopuri/p1";
	
	public LaptopScraper(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}
	
	public LaptopScraper(){
		super(laptopurl);
	}

}
