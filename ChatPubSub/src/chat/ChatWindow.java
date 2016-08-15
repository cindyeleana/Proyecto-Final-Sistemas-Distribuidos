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

public class ChatWindow {

	private JFrame frame;
	private Publisher publisher;
	private User user;
	private User contact;
	private LeafNode chatNode;
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Create the application.
	 */
	public ChatWindow(Publisher publisher, User user, User contact) {
		this.publisher = publisher;
		this.user = user;
		this.contact = contact;
		String chatName = user.getUsername()+":"+contact.getUsername();
		try {
			chatNode = publisher.getOrCreateNode(chatName);
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
		historyTxt.setEditable(false);
		
		JTextArea messageTxt = new JTextArea();
		
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
		try {
			publisher.publishItem(chatNode, msg.getText());
			history.setText(history.getText()+"\n"+"me:"+msg.getText());
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
