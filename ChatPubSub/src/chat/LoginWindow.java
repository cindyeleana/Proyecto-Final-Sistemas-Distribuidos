package chat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

public class LoginWindow {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField pwdErrerererererer;
	private MainWindow mwindow;
	private SignupWindow suWindow;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setForeground(Color.LIGHT_GRAY);
		frame.setMinimumSize(new Dimension(365, 422));
		frame.setMaximumSize(new Dimension(365, 422));
		frame.setBounds(100, 100, 379, 422);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		
		JLabel lblPassword = new JLabel("Password");
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBackground(UIManager.getColor("Separator.shadow"));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
				
			}
		});
		
		JLabel lblChatLogin = new JLabel("CHAT ");
		lblChatLogin.setFont(new Font("Dialog", Font.BOLD, 18));
		
		JButton btnRegistrar = new JButton("Sign Up");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				signup();
			}
		});
		btnRegistrar.setForeground(Color.BLACK);
		btnRegistrar.setBackground(UIManager.getColor("Separator.shadow"));
		
		pwdErrerererererer = new JPasswordField();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(126)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsername)
								.addComponent(textField)
								.addComponent(btnLogin, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(pwdErrerererererer)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(152)
							.addComponent(lblChatLogin))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(136)
							.addComponent(btnRegistrar)))
					.addContainerGap(139, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addComponent(lblChatLogin)
					.addGap(61)
					.addComponent(lblUsername)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblPassword)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pwdErrerererererer, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(btnLogin)
					.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
					.addComponent(btnRegistrar)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	private void login(){
		
		mwindow = new MainWindow();
		mwindow.getFrame().setLocationRelativeTo(null);
		frame.setVisible(false);
		mwindow.getFrame().setVisible(true);
	}
	
	private void signup(){
		suWindow = new SignupWindow();
		suWindow.getFrame().setLocationRelativeTo(null);
		suWindow.getFrame().setVisible(true);
		frame.setVisible(false);
	}
}
