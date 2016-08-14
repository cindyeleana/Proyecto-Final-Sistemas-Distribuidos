
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

	public Publisher(String userName, String password, String xmppserver, int port) throws XMPPException, InterruptedException {
		super(userName, password, xmppserver, port);
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
			
		} catch (Exception e) {
			System.out.println("Nodo no existe");
			
			// Crea nuevo nodo
			node = this.createNode(nodename);	
		}
		
		String mssge= "Mensaje1";
		
		publishItem(node, mssge);
		
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
	
	
	
	
	public static void main(String[] args) {
		try {	
			System.out.println("Entering application.");
			String nodeName = "NodoPrueba";
			
			
			String user= "prueba";
            String password= "prueba";
            String domain= "localhost";
            int port= 5222;
            
            
			XMPPConnection.DEBUG_ENABLED = true;
			Publisher p= new Publisher(user, password, domain, port);
			p.getOrCreateNode(nodeName);
			
			p.disconnect();
		} catch (XMPPException e) {
			System.out.println(e);
		} catch (InterruptedException e) {
			System.out.println(e);
	}
			

	}

}
