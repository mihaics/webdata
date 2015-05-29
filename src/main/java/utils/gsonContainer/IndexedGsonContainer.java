package utils.gsonContainer;


import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;


public class IndexedGsonContainer extends IndexedContainer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5097381990779655300L;

	
    public void IndexedJsonContainer(JsonObject gson) {
    	
    	
    	
        try {
            JsonArray jsonArray = gson.getAsJsonArray();
            
            Iterator<JsonElement> i = jsonArray.iterator();
            while(i.hasNext()){
            	Item item = this.getItem(this.addItem());
            	
            }
            /*
            for (int i = 0; jsonArray.length() > i; i++) {
                Item item = this.getItem(this.addItem());
                Iterator iterator = jsonArray.getJsonObject(i).keys();
                while (iterator.hasNext()) {
                    Object key = iterator.next();
                    if (!this.getAllItemIds().contains(key))
                        this.addContainerProperty(key, String.class, "");
                    item.getItemProperty(key).setValue(jsonArray.getJsonObject(i).get((String) key));
                }
            }
*/
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
