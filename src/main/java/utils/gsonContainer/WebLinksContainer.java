package utils.gsonContainer;

import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;

public class WebLinksContainer extends IndexedContainer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5097381990779655300L;

	@SuppressWarnings("unchecked")
	public  WebLinksContainer(JsonObject gson) {

		try {

			Set<Entry<String, JsonElement>> gsonitems = gson.entrySet();
			Iterator<Entry<String, JsonElement>> it = gsonitems.iterator();
			
			while (it.hasNext()) {
				//init item
				
				Entry<String, JsonElement> gel = it.next();
				
				// get the key/value pair
				String key = gel.getKey();// key
				String value = gel.getValue().toString();// value
				//add to item
				Object itemId = addItem();
				Item item = this.getItem(itemId);
				
					
				this.addContainerProperty("Key", String.class, "");
				this.addContainerProperty("Value", String.class, "");
				item.getItemProperty("Key").setValue(key);
				item.getItemProperty("Value").setValue(value);
				
				

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
