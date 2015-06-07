package eu.tcmdsystems.scraper.emag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import eu.tcmdsystems.scraper.WebSiteScraper;
import eu.tcmdsystems.scraper.produse.LaptopGeneric;

public class LaptopScraper extends WebSiteScraper {

	private final static Logger logger = Logger.getLogger(LaptopScraper.class
			.getName());

	static String siteurl = "http://m.emag.ro/";
	static String pageurl = "/laptopuri/c";

	public LaptopScraper(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	public LaptopScraper() {
		super(siteurl + pageurl);
	}

	public String getNextPageurl() {
		String rez = "";
		String pag = getWebdoc().getElementsByClass("pagination").html();
		logger.log(Level.INFO,
				"Pagination: "
						+ getWebdoc().getElementsByClass("pagination").html());
		Document pdoc = Jsoup.parse(pag);
		Elements pagination = pdoc.select("a[href]");
		Iterator<Element> p = pagination.iterator();
		while (p.hasNext()) {
			Elements next = p.next().getElementsByClass(
					"listing-pagination-next");
			if (next.hasAttr("href")) {
				rez = next.attr("href");

			}
			// logger.log(Level.INFO, "Pagination: "+ rez);
		}

		return rez;
	}

	public ArrayList<LaptopGeneric> getLaptopData() {
		Elements dataelements = getWebdoc().getElementsByClass(
				"product-container").select("a[href]");
		ArrayList<LaptopGeneric> laptops = new ArrayList<LaptopGeneric>();

		Iterator<Element> it = dataelements.iterator();
		while (it.hasNext()) {
			LaptopGeneric alaptop = new LaptopGeneric();
			Element prod = it.next();

			// logger.log(Level.INFO,"Product data: " + prod.toString());
			// String producturl = prod.attr("href");
			alaptop.setSurlProduct(prod.attr("href"));
			// logger.log(Level.INFO, "Product url: " +
			// alaptop.getSurlProduct());

			alaptop.setSurlImage(prod.getElementsByClass("img-container")
					.select("img").attr("data-src"));
			// String productimage =
			// prod.getElementsByClass("img-container").select("img").attr("data-src");
			// logger.log(Level.INFO, "Product image: " +
			// alaptop.getSurlImage());
			alaptop.setSproductTitle(prod.getElementsByClass("product-title")
					.text());
			// String producttitle=
			// prod.getElementsByClass("product-title").text();
			// logger.log(Level.INFO,
			// "Product title: " + alaptop.getSproductTitle());
			alaptop.setSproductStatus(prod.getElementsByClass(
					"stare-disp-listing").text());
			// String productstare=
			// prod.getElementsByClass("stare-disp-listing").text();
			// logger.log(Level.INFO,
			// "Product status: " + alaptop.getSproductStatus());

			if (prod.getElementsByClass("money-int").size() == 2) {

				String priceold_int = prod.getElementsByClass("money-int")
						.get(0).text();
				// logger.log(Level.INFO,"Price old int: "+ priceold_int);
				String priceold_dec = prod.getElementsByClass("money-decimal")
						.get(0).text();
				// logger.log(Level.INFO,"Price old decimal: "+ priceold_dec);

				alaptop.setDoldPrice(Double.valueOf(priceold_int.replace(".",
						"") + "." + priceold_dec));

				alaptop.setSdiscount(prod.getElementsByClass(
						"emg-discount-price").text());
				// String discount=
				// prod.getElementsByClass("emg-discount-price").text();
				// logger.log(Level.INFO, "Discount: " +
				// alaptop.getSdiscount());

				String pricenew_int = prod.getElementsByClass("money-int")
						.get(1).text();
				// logger.log(Level.INFO,"Price new int: "+ pricenew_int);
				String pricenew_dec = prod.getElementsByClass("money-decimal")
						.get(1).text();
				// logger.log(Level.INFO,"Price new decimal: "+ pricenew_dec);

				alaptop.setDnewPrice(Double.valueOf(pricenew_int.replace(".",
						"") + "." + pricenew_dec));
				alaptop.setScurrency(prod.getElementsByClass("money-currency")
						.get(0).text());
				// String scurrency=
				// prod.getElementsByClass("money-currency").get(0).text();
				// logger.log(Level.INFO,"Currency: "+ scurrency);
				// logger.log(
				// Level.INFO,
				// "Pret laptop: " + alaptop.getDnewPrice() + " "
				// + alaptop.getScurrency() + " / "
				// + alaptop.getDoldPrice());
			} else {

				String pricenew_int = prod.getElementsByClass("money-int")
						.get(0).text();
				// logger.log(Level.INFO,"Price new int: "+ pricenew_int);
				String pricenew_dec = prod.getElementsByClass("money-decimal")
						.get(0).text();
				alaptop.setDnewPrice(Double.valueOf(pricenew_int.replace(".",
						"") + "." + pricenew_dec));
				alaptop.setDoldPrice(alaptop.getDnewPrice());
				alaptop.setSdiscount("");
				// logger.log(Level.INFO,"Price new decimal: "+ pricenew_dec);
				alaptop.setScurrency(prod.getElementsByClass("money-currency")
						.text());
				// String scurrency=
				// prod.getElementsByClass("money-currency").text();
				// logger.log(Level.INFO, "Pret laptop: " +
				// alaptop.getDnewPrice()
				// + " " + alaptop.getScurrency());

			}

			laptops.add(alaptop);

		}

		return laptops;
	}

	public static String getPageurl() {
		return pageurl;
	}

	public static void setPageurl(String pageurl) {
		LaptopScraper.pageurl = pageurl;
	}

}
