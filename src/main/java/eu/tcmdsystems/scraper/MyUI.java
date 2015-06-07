package eu.tcmdsystems.scraper;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;

import utils.gsonContainer.WebLinksContainer;

import com.google.gson.JsonObject;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Container;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import eu.tcmdsystems.scraper.emag.LaptopScraper;
import eu.tcmdsystems.scraper.produse.LaptopGeneric;

/**
 *
 */
@Theme("mytheme")
@Widgetset("eu.tcmdsystems.scraper.MyAppWidgetset")
public class MyUI extends UI {
	/**
	 * 
	 */
	private static final long serialVersionUID = -570009004232949692L;

	private final static Logger logger = Logger.getLogger(MyUI.class.getName());

	String url = "";
	TextField tf;
	Label title_label;
	Table linkTable;
	boolean valid_url = false;

	

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		String url_regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

		tf = new TextField("Enter web address:");

		tf.setWidth("300px");
		tf.addValidator(new RegexpValidator(url_regexp, "Invalid URL"));

		title_label = new Label("Title");

		tf.addShortcutListener(new ShortcutListener("Enter pressed",
				ShortcutAction.KeyCode.ENTER, null) {
			@Override
			public void handleAction(Object sender, Object target) {
				// your code here
				tf.setValidationVisible(false);

				try {
					tf.validate();
					valid_url = true;
				} catch (InvalidValueException e) {
					Notification.show(e.getMessage());
					tf.setValidationVisible(true);
					valid_url = false;
				}
				if (valid_url) {
					parseMyURL();

				}
				tf.setValue(url);
			}

		});

		linkTable = new Table("Links");
		linkTable.setVisible(false);

		layout.addComponent(tf);
		layout.addComponent(title_label);
		layout.addComponent(linkTable);
		 BeanItemContainer<LaptopGeneric> laptopBeans =
		            new BeanItemContainer<LaptopGeneric>(LaptopGeneric.class);
		 
		laptopBeans.addAll(getLaptopData());
		Table laptopTable = new Table("Laptopuri Emag", laptopBeans);
		laptopTable.setSizeFull();
		laptopTable.setImmediate(true);
		layout.addComponent(laptopTable);

	}

	private ArrayList<LaptopGeneric> getLaptopData() {
		// TODO Auto-generated method stub
		String baseUrl = "http://www.emag.ro";
		String pageUrl = "/laptopuri/c";
		ArrayList<LaptopGeneric> dateLaptopuri = new ArrayList<LaptopGeneric>();	
		
		
		while((pageUrl!=null || !pageUrl.isEmpty())&& pageUrl.contains("/laptopuri")){
			
			logger.log(Level.INFO,"Processing page: " + pageUrl);
			LaptopScraper siteLaptopuri = new LaptopScraper(baseUrl+pageUrl);
			logger.log(Level.INFO,"Adding laptops: " + siteLaptopuri.getLaptopData().size());
			dateLaptopuri.addAll(siteLaptopuri.getLaptopData());
			pageUrl = siteLaptopuri.getNextPageurl();
			  try {
			        Thread.sleep(5000);

			      } catch (InterruptedException ie) {
			        ie.printStackTrace();
			      }
			
		}
		 
		logger.log(Level.INFO,"Processed : "+ dateLaptopuri.size());
		return dateLaptopuri;
		
	}

	protected void parseMyURL() {
		// TODO Auto-generated method stub
		url = tf.getValue();
		logger.log(Level.INFO, "URL: " + url);
		tf.clear();
		WebSiteScraper scraper = new WebSiteScraper(url);
		title_label.setValue(scraper.getDocumetTitle());

		// returns an JsonObject with all the links from page
		JsonObject pagelinks = scraper.getWebPageLinks();

		Container container = new WebLinksContainer(pagelinks);
		linkTable.setContainerDataSource(container);

		linkTable.setSizeFull();
		linkTable.setVisible(true);

		
		//scraper.getLinkElements("product-info-lite");
		// scraper.getElementsSize();
		// scraper.getAllAttributes();

	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1004092819267917804L;
	}
}
