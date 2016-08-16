package chat;

import java.awt.EventQueue;

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

public class GChatWindow {

	private JFrame frame;
	private Publisher publisher;
	private Subscriber subscriber;
	private User user;
	private LeafNode chatnode;

	public JFrame getFrame() {
		return frame;
	}


	/**
	 * Create the application.
	 */
	public GChatWindow(Publisher publisher, Subscriber subscriber, User user, LeafNode chatnode) {
		this.publisher = publisher;
		this.subscriber = subscriber;
		this.user = user;
		this.chatnode =chatnode;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 351, 322);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		
		subscriber.eventCordinator.setChatmsgs(textArea);
		subscriber.eventCordinator.setContactName("usern");
		
		JTextArea textArea_1 = new JTextArea();
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage(textArea_1, textArea);
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
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel label = new JLabel("Nombre Grupo");
		label.setBorder(new EmptyBorder(0, 5, 0, 40));
		toolBar.add(label);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(getClass().getResource("/Info-25.png")));
		button_1.setBorder(new EmptyBorder(0, 0, 0, 20));
		toolBar.add(button_1);
		
		JButton button_2 = new JButton("salir");
		button_2.setIcon(new ImageIcon(getClass().getResource("/Exit-25.png")));
		button_2.setBorder(new EmptyBorder(0, 0, 0, 0));
		toolBar.add(button_2);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	private void sendMessage(JTextArea msg, JTextArea history){
		String chatMsg;
		try {
			publisher.publishItem(chatnode, msg.getText());
			
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
