package chat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import java.awt.Button;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

public class MainWindow {

	private JFrame frame;
	private BoxLayout bContactslayout;
	private NewContact nContact;
	private NewGroup nGroup;
	private User userClass;
	private PubSub pubsub;
	/**
	 * Create the application.
	 */
	public MainWindow(User user, PubSub pubsub) {
		this.userClass = user;
		this.pubsub = pubsub;
		/*String[] parts = pubsub.getUser().split("@");
		System.out.println(parts[0]);
		System.out.println(parts[1]);*/
		String[] parts = pubsub.getUser().split("@");
	 	String[] domainParts = parts[1].split("/");
	 	System.out.println(parts[0]);
		System.out.println(domainParts[0]);
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 389, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		//tabbedPane.setLayout(glayout);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Contactos", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newContact();
			}
		});
		btnNewButton.setMinimumSize(new Dimension(0, 0));
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("/Add User Male-25.png")));
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap(290, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap(23, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel contactsPanel = new JPanel();
		contactsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		bContactslayout = new BoxLayout(contactsPanel, BoxLayout.Y_AXIS);
		contactsPanel.setLayout(bContactslayout);
		
		//agregar los contactos del usuario al panel donde se los mostrara
		addContactsToPanel(contactsPanel);
		JScrollPane panel_3 = new JScrollPane(contactsPanel);
		panel.add(panel_3, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Grupos", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.NORTH);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGroup();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(getClass().getResource("/Add User Group Man Man-25.png")));
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap(293, Short.MAX_VALUE)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addContainerGap())
		);
		panel_4.setLayout(gl_panel_4);
		
		JPanel groupPanel = new JPanel();
		groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.Y_AXIS));
		
		//agregar los grupos del usuario al panel donde se los mostrara
		addGroupsToPanel(groupPanel);
		JScrollPane panel_6 = new JScrollPane(groupPanel);
		panel_1.add(panel_6, BorderLayout.CENTER);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JLabel lblUser = new JLabel(this.userClass.getUsername());
		lblUser.setBorder(new EmptyBorder(5, 15, 5, 0));
		toolBar.add(lblUser);
		
	}
	
	private void newContact(){
		nContact = new NewContact(pubsub);
		nContact.setLocationRelativeTo(null);
		nContact.setVisible(true);
	}
	
	private void newGroup(){
		nGroup = new NewGroup();
		nGroup.setLocationRelativeTo(null);
		nGroup.setVisible(true);
	}
	
	private void addContactsToPanel(JPanel panel){
		Collection<RosterEntry> contacts = pubsub.getContacts();
		Presence presence;
		String status = "", type = "unavailable";
		User contactUser;
		if(contacts != null){
			for(RosterEntry contact : contacts){
				presence = pubsub.getPrecence(contact.getUser());
				if(presence != null){
					status = presence.getStatus();
					type = presence.getType().name();
				}
				contactUser = new User("",contact.getUser(),"",status, type);
				System.out.println(contact.getUser());
				ListContactItem item = new ListContactItem(userClass,contactUser,pubsub);
				panel.add(item);
			}
		}
	}
	
	private void addGroupsToPanel(JPanel panel){
		GroupListItem group1 = new GroupListItem();
		GroupListItem group2 = new GroupListItem();
		GroupListItem group3 = new GroupListItem();
		GroupListItem group4 = new GroupListItem();
		GroupListItem group5 = new GroupListItem();
		panel.add(group1);
		panel.add(group2);
		panel.add(group3);
		panel.add(group4);
		panel.add(group5);
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
