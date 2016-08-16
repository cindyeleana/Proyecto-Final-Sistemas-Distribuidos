package chat;

import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JToolBar;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.LeafNode;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class ChatWindow {

	private JFrame frame;
	private Publisher publisher;
	private Subscriber subscriber;
	private User user;
	private User contact;
	private LeafNode chatNode;
	private String chatName;
	
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Create the application.
	 */
	public ChatWindow(Publisher publisher, Subscriber subscriber, User user, User contact) {
		this.publisher = publisher;
		this.subscriber = subscriber;
		this.user = user;
		this.contact = contact;
		chatName = user.getUsername()+":"+contact.getUsername();
		try {
			chatNode = publisher.getOrCreateNode(chatName);
			if(publisher.isNewNode){
				subscriber.getOrCreateSubscription(contact.getUsername(), chatName);
			}
		} catch (XMPPException e) {
			System.out.println("Error al crear nodo");
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 351, 324);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		JTextArea historyTxt = new JTextArea();
		historyTxt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		historyTxt.setEditable(false);
		
		subscriber.eventCordinator.setChatmsgs(historyTxt);
		subscriber.eventCordinator.setContactName(contact.getUsername());
		
		JTextArea messageTxt = new JTextArea();
		messageTxt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendMessage(messageTxt, historyTxt);
			}
		});
		button.setIcon(new ImageIcon(getClass().getResource("/Sent-25.png")));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(historyTxt, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(messageTxt, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(historyTxt, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(messageTxt, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel contactlbl = new JLabel(contact.getUsername());
		contactlbl.setBorder(new EmptyBorder(0, 5, 0, 0));
		toolBar.add(contactlbl);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	private void sendMessage(JTextArea msg, JTextArea history){
		String chatMsg;
		try {
			publisher.publishItem(chatNode, msg.getText());
			
			if(!history.getText().equals(""))
				chatMsg =  history.getText()+"\n"+"me: "+msg.getText();
			else 
				chatMsg = "me: "+msg.getText();
			
			history.setText(chatMsg);
			msg.setText("");
		} catch (XMPPException e) {
			System.out.println("Error al publicar item");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
