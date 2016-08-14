//Code modified and obtained from https://github.com/derixmpppubsub/derixmpppubsub/tree/master/src/org/deri/xmpppubsub

package chat;


import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import org.jivesoftware.smack.*;
import org.jivesoftware.smackx.pubsub.*;
import org.jivesoftware.smackx.*;
import org.jivesoftware.smackx.packet.DiscoverItems;

public class PubSub {
	
	protected XMPPConnection connection;
    protected PubSubManager pubSubMgr;

  //Constructor 
    public PubSub(String userName, String password, String domain, int port) throws XMPPException, InterruptedException {
        this.init(userName, password, domain, port);
    }

    
    
    //Initialize
    public void init(String userName, String password, String domain, int port) throws XMPPException, InterruptedException {
        SmackConfiguration.setPacketReplyTimeout(60000);
        ConnectionConfiguration config = new ConnectionConfiguration(domain, port);
        config.setSASLAuthenticationEnabled(true);
        connection = new XMPPConnection(config);
        connection.getSASLAuthentication();
		SASLAuthentication.supportSASLMechanism("PLAIN", 0);
		connection.connect();
        
        try {
        	CreateNewAccount(userName, password);
            System.out.println(" Usuario: " + userName + " se ha registrado ");
        } catch(XMPPException e) {
        	 System.out.println("Usuario: " + userName + " ya registrado ");
        }
        try {
            connection.login(userName, password); 
            System.out.println("Usuario: " + connection.getUser() + " inicia sesión ");
        } catch(IllegalStateException e) {
        	 System.out.println("Usuario " + connection.getUser() + " ya inició sesión ");
        }
       
        
        // Creación del pubsub manager
        pubSubMgr = new PubSubManager(connection,"pubsub."+ connection.getServiceName());
        System.out.println("PubSub manager created");
    }

 
    
    public void CreateNewAccount(String userName, String password) throws XMPPException{
    	
    	connection.getAccountManager().createAccount(userName, password);
    	System.out.println(" Usuario: " + userName + " se ha registrado ");
    }
    
    //Disconnect
    public void disconnect() {
        try {
            connection.disconnect();
            System.out.println("disconected");
        } catch(java.lang.IllegalStateException e) {
        	System.out.println(e);
        }
    }

   
     
    public String getUser() {
        return connection.getUser();
    }

    //GetNode
    public LeafNode getNode(String nodename) throws XMPPException {
        LeafNode node = (LeafNode) pubSubMgr.getNode(nodename);
        System.out.println("Se obtuvo el nodo: " + nodename);
        return node;
    }
    
    // getter for the PubSubManager
   public PubSubManager getPubSubMgr() {
		return pubSubMgr;
	}

    // setter for the PubSubManager
    public void setPubSubMgr(PubSubManager pubSubMgr) {
		this.pubSubMgr = pubSubMgr;
	}
	
	

}
