package chat;

import java.io.*;
import org.jivesoftware.smack.*;
import org.jivesoftware.smackx.pubsub.*;
import org.jivesoftware.smackx.*;



public class PubSub {
	
	
	XMPPConnection connection;
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    public void login(String userName, String password) throws XMPPException
    {
    ConnectionConfiguration config = new ConnectionConfiguration("localhost",5222);
    connection = new XMPPConnection(config);
 
    connection.connect();
    connection.login(userName, password);
   }
    
    
   
    
    public static void main(String args[]) throws XMPPException, IOException
    {
    // declare variables
    PubSub c = new PubSub();
 
    // turn on the enhanced debugger
    XMPPConnection.DEBUG_ENABLED = true;
 
 
    // Enter your login information here
    c.login("testuser1", "testuser1");
    
 
   
    System.exit(0);
    }
 
    
    
	


     

}
