import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginScreen extends MainScreen implements ActionListener
{

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField txtUserId;
   static LoginScreen window;
   ConnectDB connect = new ConnectDB();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 window = new LoginScreen();
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
	public LoginScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(208, 178, 108, 20);
		passwordField.setToolTipText("\"Enter Password\"");
		panel.add(passwordField);
		
		txtUserId = new JTextField();
		txtUserId.setBounds(208, 144, 108, 20);
		panel.add(txtUserId);
		txtUserId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("User ID:");
		lblNewLabel.setBounds(113, 147, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(113, 181, 63, 14);
		panel.add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 94, 414, 2);
		panel.add(separator);
		
		JLabel lblDreamCollege = new JLabel("DREAM COLLEGE LIBRARY");
		lblDreamCollege.setHorizontalAlignment(SwingConstants.CENTER);
		lblDreamCollege.setBounds(51, 24, 342, 49);
		lblDreamCollege.setFont(new Font("Traditional Arabic", Font.PLAIN, 25));
		lblDreamCollege.setBackground(Color.GRAY);
		panel.add(lblDreamCollege);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(105, 206, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.setBounds(227, 209, 89, 23);
      btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
            Vector<String> loginVector = new Vector();
            String userId = txtUserId.getText();
            String userPassword = passwordField.getText();
            String loginString = userId + ":" + userPassword;
            loginVector = connect.getUserIdAndPassword();
            
            if(loginVector.contains(loginString))
            {
            	System.out.print("loging in");
	            window.frame.setVisible(false);
	            MainScreen ms = new MainScreen();
            				          
            	ms.initializeMs(userId);
            	
            	
            }
            else 
            	System.out.println("login info not found");
			}
		});
		panel.add(btnNewButton_1);
	}
}
