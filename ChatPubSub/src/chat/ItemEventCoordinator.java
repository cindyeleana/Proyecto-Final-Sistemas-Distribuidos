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

    public ItemEventCoordinator(String subName) {
        System.out.println("new itemeventcoordinator");
        this.subName = subName;
    }

    public void handlePublishedItems(ItemPublishEvent items) {
       
    	 System.out.println("Item count: " + items.getItems().size());
         Collection<? extends Item> itemss = items.getItems();
         for (Item item : itemss) {
                   PayloadItem pi = (PayloadItem) item;


                   System.out.println("item: " + pi.toXML());
         }
        

    }
}