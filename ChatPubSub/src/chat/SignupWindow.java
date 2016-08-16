package chat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import org.jivesoftware.smack.XMPPException;

import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignupWindow {

	private JFrame frame;
	private JTextField nameTextField;
	private JTextField userTextField;
	private JPasswordField pwdTextField;
	private PubSub pubsub;
	private MainWindow mwindow;
	/**
	 * Create the application.
	 */
	public SignupWindow(PubSub pubsub) {
		this.pubsub = pubsub;
		initConnection();
		initialize();
	}
	
	private void initConnection(){
		try{
			pubsub.connect("localhost", 5222);
		}catch(InterruptedException e) {
       	 System.out.println("Error al conectar ");
       }catch(XMPPException e) {
         	 System.out.println("Error al conectar ");
       }
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 387, 363);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNuevaCuenta = new JLabel("Nueva Cuenta");
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		JLabel lblUsuario = new JLabel("Usuario:");
		
		JLabel lblContrasena = new JLabel("Contrasena:");
		
		nameTextField = new JTextField();
		lblNombre.setLabelFor(nameTextField);
		nameTextField.setColumns(10);
		
		userTextField = new JTextField();
		lblUsuario.setLabelFor(userTextField);
		userTextField.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newAccount();
			}
		});
		btnRegistrar.setForeground(Color.BLACK);
		btnRegistrar.setBackground(UIManager.getColor("Separator.shadow"));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setBackground(UIManager.getColor("Separator.shadow"));
		
		pwdTextField = new JPasswordField();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnCancelar)
							.addPreferredGap(ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
							.addComponent(btnRegistrar))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(126)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblUsuario)
								.addComponent(lblNombre)
								.addComponent(lblContrasena)
								.addComponent(nameTextField)
								.addComponent(userTextField)
								.addComponent(pwdTextField)
								.addComponent(lblNuevaCuenta))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNuevaCuenta)
					.addGap(45)
					.addComponent(lblNombre)
					.addGap(5)
					.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblUsuario)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(userTextField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblContrasena)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pwdTextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRegistrar)
						.addComponent(btnCancelar))
					.addGap(21))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	private void newAccount(){
		String name = this.nameTextField.getText();
		String username = this.userTextField.getText();
		String pwd = String.valueOf(this.pwdTextField.getPassword());
		
		try {
			pubsub.CreateNewAccount(username, pwd);
			pubsub.login(username, pwd);
			mwindow = new MainWindow(new User(name, username,pwd), pubsub);
			mwindow.getFrame().setLocationRelativeTo(null);
			frame.setVisible(false);
			mwindow.getFrame().setVisible(true);
		} catch (XMPPException e) {
			 System.out.println("Cuenta ya creada ");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al realizar login ");
			e.printStackTrace();
		}
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
