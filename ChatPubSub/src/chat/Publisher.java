
//Code modified and obtained from https://github.com/derixmpppubsub/derixmpppubsub/tree/master/src/org/deri/xmpppubsub


package chat;

import java.io.*;
import java.util.*;
import java.io.IOException;


import org.jivesoftware.smack.*;
import org.jivesoftware.smackx.pubsub.*;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;
import org.jivesoftware.smackx.*;


public class Publisher extends PubSub{

	
	public LeafNode node;
	public Boolean isNewNode;

	public Publisher(XMPPConnection connection, PubSubManager pubsubmgr)  {
		super.connection = connection;
		super.pubSubMgr = pubsubmgr;
	}

	

	public LeafNode createNode(String nodename) throws XMPPException {
		System.out.println("Creando nodo.......");
		ConfigureForm form = new ConfigureForm(FormType.submit);
		
		form.setAccessModel(AccessModel.open);
		form.setDeliverPayloads(false);
		form.setNotifyRetract(true);
		form.setPersistentItems(true);
		form.setSubscribe(true);
		form.setPublishModel(PublishModel.open);
		form.setPresenceBasedDelivery(true);
		form.setMaxItems(100);
		//this.pubSubMgr.deleteNode(nodename);
		
		node = (LeafNode) this.pubSubMgr.createNode(nodename, form);
		
		System.out.println("node " + nodename + " created");
		return node;
	}

	@Override
	public LeafNode getNode(String nodename) throws XMPPException {
		node = super.getNode(nodename);
		return node;
	}
	
	

	public LeafNode getOrCreateNode(String nodename) throws XMPPException {
		try {
			node = this.getNode(nodename);
			isNewNode = false;
		} catch (Exception e) {
			System.out.println("Nodo no existe");
			
			// Crea nuevo nodo
			node = this.createNode(nodename);
			isNewNode = true;
		}
		
		return node;
	}

	
	static Item createItem(String message) {  
		
		 String itemId = Long.toString(System.currentTimeMillis());  
		 Item msg= new Item(itemId + "-" + message);
		 return msg;  
	}  
	
	
	
	public void publishItem (LeafNode node, String message) throws XMPPException{
		Item item=createItem(message);
		node.publish(item);
	}
	
	
	

}
