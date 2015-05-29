package utils.gsonContainer;

import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;

public class IndexedGsonContainer extends IndexedContainer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5097381990779655300L;

	@SuppressWarnings("unchecked")
	public void IndexedJsonContainer(JsonObject gson) {

		try {

			Set<Entry<String, JsonElement>> gsonitems = gson.entrySet();
			Iterator<Entry<String, JsonElement>> it = gsonitems.iterator();
			while (it.hasNext()) {
				//init item
				Entry<String, JsonElement> gel = it.next();
				Item item = this.getItem(this.addItem());
				// get the key/value pair
				String key = gel.getKey();// key
				String value = gel.getValue().toString();// value
				//add to item
				if (!this.getAllItemIds().contains(key))
					this.addContainerProperty(key, String.class, "");
				item.getItemProperty(key).setValue(value);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
