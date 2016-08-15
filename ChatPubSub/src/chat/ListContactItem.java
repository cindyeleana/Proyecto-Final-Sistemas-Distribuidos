package chat;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;

public class ListContactItem extends JPanel {

	private ChatWindow cWindow;
	private User user;
	private User contact;
	/**
	 * Create the panel.
	 */
	public ListContactItem(User user, User contact) {
		this.user = user;
		this.contact = contact;
		init();
	}
	
	private void init(){
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		setMaximumSize(new Dimension(317, 54));
		
		JLabel lblUsername = new JLabel(contact.getUsername());
		lblUsername.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lblUserStatus = new JLabel(contact.getStatus());
		lblUserStatus.setFont(new Font("Dialog", Font.BOLD, 11));
		
		JPanel typePanel = new JPanel();
		typePanel.setBorder(new CompoundBorder());
		if(contact.getState().equals("available"))
			typePanel.setBackground(Color.GREEN);
		else
			typePanel.setBackground(Color.gray);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cWindow = new ChatWindow();
				cWindow.getFrame().setLocationRelativeTo(null);
				cWindow.getFrame().setVisible(true);
			}
		});
		button.setIcon(new ImageIcon(getClass().getResource("/Chat-25.png")));
		button.setMinimumSize(new Dimension(0, 0));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(typePanel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUsername)
						.addComponent(lblUserStatus))
					.addPreferredGap(ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(typePanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
								.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblUserStatus))
						.addComponent(button, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		setLayout(groupLayout);
	}
}
