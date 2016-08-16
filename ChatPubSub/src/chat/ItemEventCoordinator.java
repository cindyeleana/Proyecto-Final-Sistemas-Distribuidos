package chat;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.Collection;

import javax.swing.JTextArea;


public class ItemEventCoordinator implements ItemEventListener {
    
    String subName;
    JTextArea chatmsgs;
    String contactName;

    public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public JTextArea getChatmsgs() {
		return chatmsgs;
	}

	public void setChatmsgs(JTextArea chatmsgs) {
		this.chatmsgs = chatmsgs;
	}

	public ItemEventCoordinator() {
        System.out.println("entra a clase new itemeventcoordinator");
       
    }

    public void handlePublishedItems(ItemPublishEvent items) {
       
    	
    	 System.out.println("Item count: " + items.getItems().size());
         Collection<? extends Item> itemss = items.getItems();
         String itemM[], chatMsg;
         for (Item item : itemss) {
        	 itemM = item.getId().split("-");
             System.out.println("Message: " +itemM[1]);
             if(!chatmsgs.getText().equals(""))
 				chatMsg =  chatmsgs.getText()+"\n"+contactName+": "+itemM[1];
 			else 
 				chatMsg = contactName+": "+itemM[1];
             
            chatmsgs.setText(chatMsg);
         }
         
         
      

    }
}