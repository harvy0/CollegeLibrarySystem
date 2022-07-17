import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class MembershipCard extends JFrame {

	private JPanel contentPane, panel_1;
	//private JTextField ;
	static JLabel idLbl, nameLbl, typeLbl, expiryLbl, txtNoImage;

	
	public MembershipCard() {
		setTitle("Print");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 316, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		final JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JButton printBtn = new JButton("Print");
		printBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Print p = new Print();
				p.printComponent(panel_1);
			}
		});
		panel_2.add(printBtn);
		
		JLabel lblPreview = new JLabel("Preview:");
		panel.add(lblPreview);
		
		 panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		txtNoImage = new JLabel();
		txtNoImage.setIcon(new ImageIcon(MembershipCard.class.getResource("/images/auto_pic.jpg")));
		txtNoImage.setForeground(SystemColor.menu);
		txtNoImage.setBackground(SystemColor.controlDkShadow);
		txtNoImage.setBounds(212, 37, 86, 86);
		panel_1.add(txtNoImage);
	
		
		JLabel lblNewLabel = new JLabel("Dream College Library");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(UIManager.getColor("Button.shadow"));
		lblNewLabel.setBounds(1, 1, 298, 35);
		panel_1.add(lblNewLabel);
		
		 idLbl = new JLabel("#########");
		idLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		idLbl.setBounds(20, 85, 117, 14);
		panel_1.add(idLbl);
		
		 nameLbl = new JLabel("FirstName LastName");
		nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameLbl.setBounds(10, 60, 156, 14);
		panel_1.add(nameLbl);
		
		 typeLbl = new JLabel("MemberType");
		typeLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		typeLbl.setBounds(220, 133, 78, 14);
		panel_1.add(typeLbl);
		
		JLabel lblNewLabel_3 = new JLabel("Expires:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(31, 133, 76, 14);
		panel_1.add(lblNewLabel_3);
		
		 expiryLbl = new JLabel("YYYY-MM-DD");
		expiryLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		expiryLbl.setBounds(29, 149, 108, 14);
		panel_1.add(expiryLbl);						
	}
	public static void createCard(String name, String id, String date, String type)
	{
		idLbl.setText(id);
		nameLbl.setText(name);
		expiryLbl.setText(date);
		typeLbl.setText(type);
	}
}
