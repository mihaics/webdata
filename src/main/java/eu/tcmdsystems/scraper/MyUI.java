package eu.tcmdsystems.scraper;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;



import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

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


	private final static Logger logger =
	          Logger.getLogger(MyUI.class.getName());
	  
	
	String url = "";
	 TextField tf;
	 Label title_label; 
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
      
       
       tf.addShortcutListener(new ShortcutListener("Enter pressed", ShortcutAction.KeyCode.ENTER, null) {
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
    	        if(valid_url){
    	    		getMyURL();
    	       		
    	       	}
    	   }

		
    	   });
        
       
       
        layout.addComponent(tf);
        layout.addComponent(title_label);
       
     
    	
    }

	
   


	protected void getMyURL() {
		// TODO Auto-generated method stub
    	  url = tf.getValue();
          logger.log(Level.INFO,"URL: "+url);
          tf.clear();
          WebSiteScraper scraper = new WebSiteScraper(url);
     	  title_label.setValue(scraper.getDocumetTitle());
     	  
     	  logger.log(Level.INFO,"JSON Links: "+scraper.getElementLinks());
     	  scraper.getClassLinks("product-container js-product-container");
     	  
     		
	}


	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
