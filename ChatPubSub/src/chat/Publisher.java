
//Code modified and obtained from https://github.com/derixmpppubsub/derixmpppubsub/tree/master/src/org/deri/xmpppubsub


package chat;

import java.io.*;
import java.util.*;
import java.io.IOException;


import org.jivesoftware.smack.*;
import org.jivesoftware.smackx.pubsub.*;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;
import org.jivesoftware.smackx.*;


public class Publisher extends PubSubClient{

	
	public LeafNode node;

	//Constructor that logs in or creates a user account with provided details (username, password) on the given server (xmppserver) using the default
	 
	public Publisher(String userName, String password, String xmppserver) throws XMPPException, InterruptedException {
		super(userName, password, xmppserver);
	}

	public Publisher(String userName, String password, String xmppserver, int port, boolean createAccountIfNotExist) throws XMPPException, InterruptedException {
		super(userName, password, xmppserver, port, createAccountIfNotExist);
	}

	public Publisher(String fileName) throws IOException, XMPPException, InterruptedException {
		super(fileName);
	}

	public Publisher(String fileName, boolean createAccountIfNotExist) throws IOException, XMPPException, InterruptedException {
		super(fileName, createAccountIfNotExist);
	}

	public LeafNode createNode(String nodename) throws XMPPException {
		System.out.println("Creando nodo.......");
		ConfigureForm form = new ConfigureForm(FormType.submit);
		
		form.setAccessModel(AccessModel.open);
		form.setDeliverPayloads(true);
		form.setNotifyRetract(true);
		form.setPersistentItems(true);
		form.setPublishModel(PublishModel.open);
		
		//this.pubSubMgr.deleteNode(nodename);
		
		node = (LeafNode) this.pubSubMgr.createNode(nodename, form);
		
		System.out.println("node " + nodename + " created");
		return node;
	}

	@Override
	public LeafNode getNode(String nodename) throws XMPPException {
		System.out.println("entra a obtener nodo");
		
		node = super.getNode(nodename);
		
		return node;
	}

	public LeafNode getOrCreateNode(String nodename) throws XMPPException {
		try {
			node = this.getNode(nodename);
			
		} catch (Exception e) {
			System.out.println(e);
			node = this.createNode(nodename);	
		}
		//System.out.println("Publica nodo");
		//node.publish(new Item("hola mmmmm"));
		return node;
	}

	
	
	
	public static void main(String[] args) {
		try {	
			System.out.println("Entering application.");
			String nodeName = "TopicNode2";
			XMPPConnection.DEBUG_ENABLED = true;
			Publisher p= new Publisher("testuser2", "123", "localhost");
			p.getOrCreateNode(nodeName);
			//p.disconnect();
		} catch (XMPPException e) {
			System.out.println(e);
		} catch (InterruptedException e) {
			System.out.println(e);
	}
			

	}

}
