//Code modified and obtained from https://github.com/derixmpppubsub/derixmpppubsub/tree/master/src/org/deri/xmpppubsub

package chat;


import java.io.*;
import java.util.*;
import java.util.logging.Logger;

import org.jivesoftware.smack.*;
import org.jivesoftware.smackx.pubsub.*;
import org.jivesoftware.smackx.*;

public class PubSubClient {
	
	protected XMPPConnection connection;
    protected PubSubManager pubSubMgr;

    //Constructor 1
    public PubSubClient(String userName, String password, String xmppserver) throws XMPPException, InterruptedException {
        this(userName, password, xmppserver, 5222, true);
    }

  //Constructor 2
    public PubSubClient(String userName, String password, String xmppserver,boolean createAccountIfNotExist) throws XMPPException, InterruptedException {
        this(userName, password, xmppserver, 5222, createAccountIfNotExist);
    }

  //Constructor 3
    public PubSubClient(String fileName) throws IOException, XMPPException, InterruptedException {
        this(fileName, true);
    }

  //Constructor 4
    public PubSubClient(String fileName, boolean createAccountIfNotExist) throws IOException, XMPPException, InterruptedException {
            // the file path was not correct
            Properties prop = new Properties();
            File file = new File(fileName);
            String filePath = file.getCanonicalPath();
            InputStream is = new FileInputStream(filePath);
            prop.load(is);
            String userName = prop.getProperty("username");
            String password = prop.getProperty("password");
            String xmppserver = prop.getProperty("xmppserver");
            int port = Integer.parseInt(prop.getProperty("port"));
            is.close();
            this.init(userName, password, xmppserver, port, createAccountIfNotExist);
    }

  //Constructor 5
    public PubSubClient(String userName, String password, String domain, int port, boolean createAccountIfNotExist) throws XMPPException, InterruptedException {
        this.init(userName, password, domain, port, createAccountIfNotExist);
    }

    
    
    //Initialize
    public void init(String userName, String password, String domain, int port, boolean createAccountIfNotExist) throws XMPPException, InterruptedException {
        SmackConfiguration.setPacketReplyTimeout(60000);
        ConnectionConfiguration config = new ConnectionConfiguration(domain, port);
        connection = new XMPPConnection(config);
        connection.connect();
        
        try {
            connection.getAccountManager().createAccount(userName, password);
            System.out.println(" User: " + userName + " created "+ domain);
        } catch(XMPPException e) {
        	 System.out.println("User " + userName + " already created ");
        }
        try {
            connection.login(userName, password); 
            System.out.println("User " + connection.getUser() + " login ");
        } catch(IllegalStateException e) {
        	 System.out.println("User " + connection.getUser() + " already login ");
        }

        pubSubMgr = new PubSubManager(connection,"pubsub.localhost");
        System.out.println("PubSub manager created");
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

   
     // get full ID of the user that is logged in
    public String getUser() {
        return connection.getUser();
    }

    //GetNode
    public LeafNode getNode(String nodename) throws XMPPException {
        LeafNode node = (LeafNode) pubSubMgr.getNode(nodename);
        System.out.println("got node " + nodename);
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
