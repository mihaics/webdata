package eu.tcmdsystems.scraper.produse;

public class LaptopGeneric {
	private String surlProduct;
	private String surlImage;
	private String sproductTitle;
	private String sproductStatus;
	private String sdiscount;
	private double doldPrice;
	private double dnewPrice;
	private String scurrency;
	
	
	
	public LaptopGeneric(){
		
	}
	
	public LaptopGeneric(String surlProduct, String surlImage,
			String sproductTitle, String sproductStatus, String sdiscount,
			double doldPrice, double dnewPrice, String scurrency) {
		//super();
		this.surlProduct = surlProduct;
		this.surlImage = surlImage;
		this.sproductTitle = sproductTitle;
		this.sproductStatus = sproductStatus;
		this.sdiscount = sdiscount;
		this.doldPrice = doldPrice;
		this.dnewPrice = dnewPrice;
		this.scurrency = scurrency;
	}
	
	public String getSurlProduct() {
		return surlProduct;
	}
	public void setSurlProduct(String surlProduct) {
		this.surlProduct = surlProduct;
	}
	public String getSurlImage() {
		return surlImage;
	}
	public void setSurlImage(String surlImage) {
		this.surlImage = surlImage;
	}
	public String getSproductTitle() {
		return sproductTitle;
	}
	public void setSproductTitle(String sproductTitle) {
		this.sproductTitle = sproductTitle;
	}
	public String getSproductStatus() {
		return sproductStatus;
	}
	public void setSproductStatus(String sproductStatus) {
		this.sproductStatus = sproductStatus;
	}
	public String getSdiscount() {
		return sdiscount;
	}
	public void setSdiscount(String sdiscount) {
		this.sdiscount = sdiscount;
	}
	public double getDoldPrice() {
		return doldPrice;
	}
	public void setDoldPrice(double doldPrice) {
		this.doldPrice = doldPrice;
	}
	public double getDnewPrice() {
		return dnewPrice;
	}
	public void setDnewPrice(double dnewPrice) {
		this.dnewPrice = dnewPrice;
	}
	public String getScurrency() {
		return scurrency;
	}
	public void setScurrency(String scurrency) {
		this.scurrency = scurrency;
	}
	
	
}
