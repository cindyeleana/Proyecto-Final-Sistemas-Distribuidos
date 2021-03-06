//Code modified and obtained from https://github.com/derixmpppubsub/derixmpppubsub/tree/master/src/org/deri/xmpppubsub

package chat;

import java.io.*;
import java.util.*;

import org.jivesoftware.smack.*;
import org.jivesoftware.smackx.pubsub.*;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;
import org.jivesoftware.smackx.*;

public class Subscriber extends PubSub {
	
    public HashMap<String, LeafNode> nodeSubscriptions;
    public ItemEventCoordinator eventCordinator;

    public Subscriber(XMPPConnection connection, PubSubManager pubsubmgr)  {
    	super.connection = connection;
    	super.pubSubMgr = pubsubmgr;
        initNodesSubscribedTo();
    }

    public void addEventCoordinator(LeafNode nd){
    	eventCordinator = new ItemEventCoordinator();
  	    nd.addItemEventListener(eventCordinator);
    }
    
    public void initNodesSubscribedTo(){
        nodeSubscriptions = new HashMap<String, LeafNode>();
         try {
            List<Affiliation> affs = this.pubSubMgr.getAffiliations();
            for(Affiliation aff : affs ) {
                String nodeName = aff.getNodeId();
               // System.out.println(this.getUser() + " esta afiliado a nodo "+ nodeName);
                LeafNode node = this.getNode(nodeName);
                nodeSubscriptions.put(nodeName, node);
            }  
            
        } catch (XMPPException e) {
        	System.out.println("no tiene afiliaciones");
        }
    }
    
    
    

    public boolean isSubscribedTo(String nodeName) {
        boolean subscribed;
        subscribed = nodeSubscriptions.containsKey(nodeName);
        return subscribed;        
    }
    
    
    public void SubscribeUser(String user, LeafNode nd) throws XMPPException{
 	   System.out.println("Entra a susc");
 	   System.out.println("user es "+ user);
 	   String[] parts = this.getUser().split("@");
 	   String[] domainParts = parts[1].split("/");
 	   System.out.println(user+"@"+parts[1]);
 	   //nd.subscribe(user+"@"+parts[1]);
 	   nd.subscribe(this.getUser());
    }

//*******************************+ Suscripcion**********************************   
    public void getOrCreateSubscription(String user, String nodeName) throws XMPPException {
        //Subscription s = new Subscription(this.getUser(), nodeName);
    	//if (!isSubscribedTo(nodeName)) {
    		LeafNode nd= this.getNode(nodeName);
    		
    		SubscribeUser(user,nd);
    		System.out.println(user + " suscrito al nodo " + nodeName +"\n");
    		 //nd.subscribe(this.getUser());
        	nd.addItemEventListener(new ItemEventCoordinator());
        	
       // System.out.println(this.getUser() + " tiene suscripcion al nodo " + nodeName +"\n");
        	
        //} else {
        	//System.out.println(this.getUser() + " ya esta suscrito a " + nodeName);
        //}
    }

    
  
 // Recibe nombre de nodo y obtiene los items publicados en ese nodo
    public void GetItems(String nodename) throws XMPPException{
    	System.out.println("Entra a GetItems ");
    	LeafNode node= this.getNode(nodename);
    	System.out.println("Item count: " + node.getItems().size());
    	Collection<? extends Item> itemss = node.getItems();
    	String itemM[];
    	for (Item item : itemss) {
    	  itemM = item.getId().split("-");
          System.out.println("item: " +itemM[1]);
        }
    }
    
    

    public void deleteSubscriptions(String nodeName) throws XMPPException {
    	LeafNode node=this.getNode(nodeName);
    	System.out.println(node);
        List<? extends Subscription> subs = node.getSubscriptions();
        for(Subscription sub : subs){
            node.unsubscribe(this.getUser(), sub.getId()); 
            System.out.println("deleted jid " + this.getUser() + " subscription to node " + node + " with id " +
                    sub.getId());
        }
    }

    
    
    
    
    
    public void getAffSubs(String nameNd) throws InterruptedException {
    	System.out.println("\n");
    	try{
    	//Lista de afiliaciones de usuario a nodos
        List<Affiliation> affs = this.getPubSubMgr().getAffiliations();
        for(Affiliation aff : affs ) {
            String nodeName = aff.getNodeId();
//            Affiliation.Type = aff.getType();
            System.out.println(this.getUser() + " esta afiliado al nodo " + nodeName);
        }
        System.out.println("\n");
        //Lista de suscripciones de usuario a nodos
        List<Subscription> subs = this.getPubSubMgr().getSubscriptions();
        for(Subscription sub: subs) {
            String jid = sub.getJid();
            String id = sub.getId();
            String nodeNam = sub.getNode();
            System.out.println(jid + " tiene suscripcion al nodo " + nodeNam);
            //s.deleteSubscriptions(nodeNam);
         }
        } catch (XMPPException e) {
        	System.out.println(e);
        }
    	
    }

   
 
}
