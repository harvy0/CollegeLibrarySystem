import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Lists extends JFrame{
	static JLabel lblDate, lblTime, lblReportTitle, idLbl, lblTotalOwed;
	static JList list;
	static JPanel panel_2;
	public Lists() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 442, 435);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		 panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setBackground(Color.white);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel_2.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblDreamCollegeLibrary = new JLabel("Dream College Library");
		panel.add(lblDreamCollegeLibrary);
		lblDreamCollegeLibrary.setHorizontalAlignment(SwingConstants.CENTER);
		lblDreamCollegeLibrary.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel.add(panel_3);
		 panel_3.setLayout(new GridLayout(3, 1, 0, 0));
		
		 lblReportTitle = new JLabel("Report Title");
		 lblReportTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblReportTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_3.add(lblReportTitle);
		
		 idLbl = new JLabel("New label");
		idLbl.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(idLbl);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.white);
		panel.add(panel_4);
		panel_4.setLayout(new GridLayout(3, 3, 0, 0));
		
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
		
		JLabel lblNewLabel = new JLabel("_____________________________________________________________________");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		 list = new JList();
		 list.setBackground(UIManager.getColor("Button.background"));
		panel_2.add(list, BorderLayout.CENTER);
		
		 lblTotalOwed = new JLabel("Total Owed: $");
		lblTotalOwed.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalOwed.setBackground(Color.WHITE);
		panel_2.add(lblTotalOwed, BorderLayout.SOUTH);
	}
	public static void createReceipt(String title, Vector<String> v, int id)
	{
		
		lblReportTitle.setText(title);
		idLbl.setText("Member: "+id);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		String date = sdf.format(new Date());
		lblDate.setText(date);
		SimpleDateFormat tdf = new SimpleDateFormat("HH:mm");
		String time = tdf.format(new Date());
		lblTime.setText(time);
		lblTotalOwed.setVisible(false);
		list.setListData(v);
		
		
	}
	public static void createReceiptFees(String title, Vector<String> v, int id, String total)
	{
		
		lblReportTitle.setText(title);
		idLbl.setText("Member: "+id);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		String date = sdf.format(new Date());
		lblDate.setText(date);
		SimpleDateFormat tdf = new SimpleDateFormat("HH:mm");
		String time = tdf.format(new Date());
		lblTime.setText(time);
		lblTotalOwed.setText("Total Owed: $" + total);
		
		list.setListData(v);
		
		
	}
	public static void printReceipt(){
		Print p = new Print();
		p.printComponent(panel_2);
	}
}


