//Code modified and obtained from https://github.com/derixmpppubsub/derixmpppubsub/tree/master/src/org/deri/xmpppubsub

package chat;


import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smackx.pubsub.*;
import org.jivesoftware.smackx.*;
import org.jivesoftware.smackx.packet.DiscoverItems;

public class PubSub {
	
	protected XMPPConnection connection;
    protected PubSubManager pubSubMgr;

    public PubSub(){}
    
  /*//Constructor 
    public PubSub(String userName, String password, String domain, int port) throws XMPPException, InterruptedException {
        this.init(userName, password, domain, port);
    }*/


    public void connect(String domain, int port)throws XMPPException, InterruptedException{
    	SmackConfiguration.setPacketReplyTimeout(60000);
        ConnectionConfiguration config = new ConnectionConfiguration(domain, port);
        config.setSASLAuthenticationEnabled(true);
        connection = new XMPPConnection(config);
        connection.getSASLAuthentication();
		SASLAuthentication.supportSASLMechanism("PLAIN", 0);
		connection.connect();
		pubSubMgr = new PubSubManager(connection,"pubsub."+ connection.getServiceName());
    }
    
    public void login(String userName, String password) throws XMPPException, InterruptedException{
    	try {
            connection.login(userName, password); 
            System.out.println("Usuario: " + connection.getUser() + " inicia sesión ");
        } catch(IllegalStateException e) {
        	 System.out.println("Usuario " + connection.getUser() + " ya inició sesión ");
        }
    }
 
    
    
    public void CreateNewAccount(String userName, String password) throws XMPPException{
		connection.getAccountManager().createAccount(userName, password);
        System.out.println(" Usuario: " + userName + " se ha registrado ");      
    }
    
    public Collection<RosterEntry> getContacts(){
    	Collection<RosterEntry> list = null;
    	Roster roster = connection.getRoster();
    	if(roster!= null){
    		list = roster.getEntries();
    		System.out.println("list is" + list);
    	}
    	return list;
    }
    
    public Presence getPrecence(String user){
    	return connection.getRoster().getPresence(user);
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

   public void createEntry(String user, String name) throws XMPPException{
	   Roster roster = connection.getRoster();
	   roster.createEntry(user, name, null);
   }
     
    public String getUser() {
        return connection.getUser();
    }

    //GetNode
    public LeafNode getNode(String nodename) throws XMPPException {
        LeafNode node = (LeafNode) pubSubMgr.getNode(nodename);
        return node;
    }
    
    public XMPPConnection getConnection() {
		return connection;
	}
    
    public Boolean searchNode(String name){
    	Boolean finded = false;
    	try {
			this.getNode(name);
			System.out.println("Se obtuvo el nodo: " + name);
			finded = true;
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return finded;
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
