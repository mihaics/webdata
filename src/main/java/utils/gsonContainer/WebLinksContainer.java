package utils.gsonContainer;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Link;

import eu.tcmdsystems.scraper.WebSiteScraper;

public class WebLinksContainer extends IndexedContainer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5097381990779655300L;
	final static Logger logger = Logger.getLogger(WebLinksContainer.class
			.getName());

	private String textId = "Text";
	private String valueId = "Link";
	@SuppressWarnings("unchecked")
	public  WebLinksContainer(JsonObject gson) {
		
		try {

			Set<Entry<String, JsonElement>> gsonitems = gson.entrySet();
			Iterator<Entry<String, JsonElement>> it = gsonitems.iterator();
			
			while (it.hasNext()) {
				//init item
				
				Entry<String, JsonElement> gel = it.next();
				
				// get the key/value pair
				String text = gel.getKey();// key
				String value = gel.getValue().toString();// value
				//add to item
				Object itemId = addItem();
				Item item = this.getItem(itemId);
				
					
				
				this.addContainerProperty(textId, String.class, "");
				
				this.addContainerProperty(valueId, Component.class, null);
				item.getItemProperty(textId).setValue(text);
				// remove quotas from string
				if(value.startsWith("\"")) value=value.substring(1, value.length()-1);		
				//logger.log(Level.INFO, "Value substring = "+ value);
				Link addr = new Link(value, new ExternalResource(value));
				addr.setTargetName("_blank");
				item.getItemProperty(valueId).setValue(addr);
				
			

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getTextId() {
		return textId;
	}
	public void setTextId(String textId) {
		this.textId = textId;
	}
	public String getValueId() {
		return valueId;
	}
	public void setValueId(String valueId) {
		this.valueId = valueId;
	}
}
