//Code modified and obtained from https://github.com/derixmpppubsub/derixmpppubsub/tree/master/src/org/deri/xmpppubsub

package chat;

import java.io.*;
import java.util.*;

import org.jivesoftware.smack.*;
import org.jivesoftware.smackx.pubsub.*;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;
import org.jivesoftware.smackx.*;

public class Subscriber extends PubSubClient {
	
    public HashMap<String, LeafNode> nodeSubscriptions;

    public Subscriber(String userName, String password, String xmppserver) throws XMPPException, InterruptedException {
        super(userName, password, xmppserver);
        initNodesSubscribedTo();
       // System.out.println("no initialization");
    }

    public Subscriber(String userName, String password, String xmppserver, int port, boolean createAccountIfNotExist) throws XMPPException, InterruptedException {
        super(userName, password, xmppserver, port, createAccountIfNotExist);
        initNodesSubscribedTo();
    }

    public Subscriber(String fileName) throws IOException, XMPPException, InterruptedException {
        super(fileName);
        initNodesSubscribedTo();
    }

    public Subscriber(String fileName, boolean createAccountIfNotExist) throws IOException, XMPPException, InterruptedException {
        super(fileName, createAccountIfNotExist);
        initNodesSubscribedTo();
    }
    
    public void initNodesSubscribedTo() throws XMPPException {
    	System.out.println("entra a initNodesSubscribedTo");
        nodeSubscriptions = new HashMap<String, LeafNode>();
        System.out.println("Entra a funcion (initNodesSubscribedTo)");
        try {
            List<Affiliation> affs = this.pubSubMgr.getAffiliations();
            for(Affiliation aff : affs ) {
                String nodeName = aff.getNodeId();
                System.out.println(this.getUser() + "is affiliated to node "+ nodeName);
                LeafNode node = this.getNode(nodeName);
                nodeSubscriptions.put(nodeName, node);
//                nodesSubscribedTo.add(nodeName);
            }
        } catch (XMPPException e) {
        	System.out.println("no affiliations");
        }
    }

    public boolean isSubscribedTo(String nodeName) {
    	System.out.println("entra a IssubscribeTo");
        boolean subscribed;
//        subscribed = nodesSubscribedTo.contains(node);
        subscribed = nodeSubscriptions.containsKey(nodeName);
        System.out.println("está suscrito? " +subscribed);
        return subscribed;        
        
    }

    public void subscribeTo(String nodeName) throws XMPPException {
    	System.out.println("entra a subscribeTo");
        LeafNode node = this.getNode(nodeName);
        node.subscribe(this.getUser());
//            nodes.add(node);
//            nodesSubscribedTo.add(node.getId());
        nodeSubscriptions.put(nodeName, node);
        System.out.println(this.getUser() + " subscribed to node " + node.getId());
    }

    public void subscribeIfNotSubscribedTo(String nodeName) throws XMPPException {
    	System.out.println("entra a subscribeIfNotSubscribedTo");
        if (!isSubscribedTo(nodeName)) {
        	this.getNode(nodeName).subscribe(this.getUser());
        	
        } else {
        	System.out.println(this.getUser() + " is already subscribed to " + nodeName);
        }
    }
    
    public void getOrCreateSubscription(String nodeName) throws XMPPException {
    	System.out.println("entra a getOrCreateSubscription");
        Subscription s = new Subscription(this.getUser(), nodeName);
        subscribeIfNotSubscribedTo(nodeName);

    }

    

    public void deleteSubscriptions(String nodeName) throws XMPPException {
    	System.out.println("entra a deleteSubscriptions");
    	LeafNode node=this.getNode(nodeName);
    	System.out.println(node);
        List<? extends Subscription> subs = node.getSubscriptions();
        for(Subscription sub : subs){
            node.unsubscribe(this.getUser(), sub.getId()); 
            System.out.println("deleted jid " + this.getUser() + " subscription to node " + node + " with id " +
                    sub.getId());
        }
    }


    public static void main(String[] args) throws InterruptedException {
        try {
            

            XMPPConnection.DEBUG_ENABLED = true;
            Subscriber s = new Subscriber("prueba", "prueba", "localhost");
            String NodeN="abc";
            s.getOrCreateSubscription(NodeN);
            
           //Lista de afiliaciones de usuario a nodos
            List<Affiliation> affs = s.getPubSubMgr().getAffiliations();
            System.out.println("entra a ListAffiliation");
            for(Affiliation aff : affs ) {
                String nodeName = aff.getNodeId();
//                Affiliation.Type = aff.getType();
                System.out.println(s.getUser() + " esta afiliado al nodo " + nodeName);
            }
            
            //Lista de suscripciones de usuario a nodos
            List<Subscription> subs = s.getPubSubMgr().getSubscriptions();
            System.out.println("entra a ListSubscription");
            for(Subscription sub: subs) {
                String jid = sub.getJid();
                String id = sub.getId();
                String nodeNam = sub.getNode();
                System.out.println(jid + " tiene suscripcion " + id + " al nodo " + nodeNam);
                //s.deleteSubscriptions(nodeNam);
             }
            
            
        } catch (XMPPException e) {
        	System.out.println(e);
        } catch (InterruptedException e) {
        	System.out.println(e);
        }
    }
}
