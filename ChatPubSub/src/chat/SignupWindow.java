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
import javax.swing.border.MatteBorder;

public class SignupWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField pwdJkjk;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignupWindow window = new SignupWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public SignupWindow() {
		initialize();
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
		
		textField = new JTextField();
		lblNombre.setLabelFor(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		lblUsuario.setLabelFor(textField_1);
		textField_1.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setForeground(Color.BLACK);
		btnRegistrar.setBackground(UIManager.getColor("Separator.shadow"));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setBackground(UIManager.getColor("Separator.shadow"));
		
		pwdJkjk = new JPasswordField();
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
								.addComponent(textField)
								.addComponent(textField_1)
								.addComponent(pwdJkjk)
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
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblUsuario)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblContrasena)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pwdJkjk, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRegistrar)
						.addComponent(btnCancelar))
					.addGap(21))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
