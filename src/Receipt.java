import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

public class Receipt extends JFrame{
	static JLabel totalLbl, cashLbl, changeLbl, lblDate, lblTime;
	static JList list;
	static JPanel panel_2;
	public Receipt() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 400);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		 panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel_2.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblDreamCollegeLibrary = new JLabel("Dream College Library");
		panel.add(lblDreamCollegeLibrary);
		lblDreamCollegeLibrary.setHorizontalAlignment(SwingConstants.CENTER);
		lblDreamCollegeLibrary.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.white);
		panel.add(panel_4);
		panel_4.setLayout(new GridLayout(4, 3, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("1234 Dream Ave");
		panel_4.add(lblNewLabel_3);
		
		 lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(lblDate);
		
		JLabel lblNewLabel_7 = new JLabel("Coquitlam BC, V4B 2C3 ");
		panel_4.add(lblNewLabel_7);
		
		 lblTime = new JLabel("Time");
		lblTime.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(lblTime);
		
		JLabel label_2 = new JLabel("(604) 123-1234");
		panel_4.add(label_2);
		
		JLabel lblNewLabel = new JLabel("");
		panel_4.add(lblNewLabel);
		
		JLabel label = new JLabel("_____________________");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(label);
		
		JLabel label_1 = new JLabel("_____________________");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_4.add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.white);
		panel_2.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTotal = new JLabel("Total");
		panel_3.add(lblTotal);
		
		 totalLbl = new JLabel("$0.00");
		panel_3.add(totalLbl);
		totalLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblCash = new JLabel("Cash");
		panel_3.add(lblCash);
		
		 cashLbl = new JLabel("$0.00");
		panel_3.add(cashLbl);
		cashLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblChange = new JLabel("Change");
		panel_3.add(lblChange);
		
		 changeLbl = new JLabel("$0.00");
		panel_3.add(changeLbl);
		changeLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		
		 list = new JList();
		 list.setBackground(UIManager.getColor("Button.background"));
		panel_2.add(list, BorderLayout.CENTER);
	}
	public static void createReceipt(double total, double cash, double change, Vector<String> v)
	{
		totalLbl.setText("$"+String.valueOf(total));
		cashLbl.setText("$"+String.valueOf(cash));
		changeLbl.setText("$"+String.valueOf(change));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		String date = sdf.format(new Date());
		lblDate.setText(date);
		SimpleDateFormat tdf = new SimpleDateFormat("HH:mm");
		String time = tdf.format(new Date());
		lblTime.setText(time);		
		list.setListData(v);
		
		
	}
	public static void printReceipt(){
		Print p = new Print();
		p.printComponent(panel_2);
	}
}
