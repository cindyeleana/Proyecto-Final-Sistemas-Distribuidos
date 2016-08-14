package chat;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.Collection;


public class ItemEventCoordinator implements ItemEventListener {
    
    String subName;

    public ItemEventCoordinator() {
        System.out.println("entra a clase new itemeventcoordinator");
       
    }

    public void handlePublishedItems(ItemPublishEvent items) {
       
    	
    	 System.out.println("Item count: " + items.getItems().size());
         Collection<? extends Item> itemss = items.getItems();
         String itemM[];
         for (Item item : itemss) {
        	 itemM = item.getId().split("-");
             System.out.println("Message: " +itemM[1]);
         }
      

    }
}