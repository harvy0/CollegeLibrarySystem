import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.Format;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.UIManager.*;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;



public class MainScreen implements ActionListener, ListSelectionListener,  ItemListener
{
  static ConnectDB connect = new ConnectDB();
	JFrame DreamCollegeLibrary;
	Vector<String> rowOne = new Vector<String>();    	
	Vector<Vector> itemVector= new Vector<Vector>();
	Vector<Vector> searchItems= new Vector<Vector>();
	Vector<String> checkNames = new Vector<String>(); 
	Vector<Vector> checkItems =  new Vector<Vector>();
	
	private JTextField isbntxt, titleTxt ,authorTxt, locationTxt, qtyText,editionTxt,searchMtxt,memberIDtxt,fnameTxt,lnameTxt,phoneTxt,emailTxt,isbnTxt,memberIdTxt,totaltxt,searchResourceTxt,searchTxt,cashTxt;
	JTextArea descriptionTxt;
    private Vector vtMemberType, vtYear, vtMonth, vtItemType, names, columnNames ;
    JComboBox memberTypeCombo, yearCombo, monthCombo, typeCombo, searchCombo, fromMonth, toMonth, reportType, searchResourceCombo;
    JButton addMemberBtn,generateIdBtn, updateMemberBtn, removeMemberBtn,searchMBtn, addResourceBtn, updateResourceBtn, removeResourceBtn, searchBtn, addCheckoutBtn,
    removeCheckoutBtn, clearCheckoutBtn, checkoutBtn, confirmMemberBtn, payBtn, btnCheckIn, showBtn, createReportBtn, itemSearchBtn;

    int checkoutMemberID;
    
    JTabbedPane tabbedPane;
    JPanel checkoutPanel,mid,mid2;
    static JList memberList, borrowedList, feesList;
    private JPasswordField passwordTxt;
    private JTable table, table_1, checkoutTable, reportTable;
    DefaultTableModel model, model_1, checkoutModel, reportModel; 

   private JTextField fromYear,toYear;
   private JCheckBox chckbxBooks;
   private JCheckBox chckbxCdsdvds;
   private JCheckBox chckbxMagazinesnewspapers;
   private JCheckBox chckbxArticles;
   TableRowSorter<DefaultTableModel> sorter;
private JButton btnDeselect;
private JButton btnDeselect_1;
   
	 /**
	  * @wbp.parser.entryPoint
	  */
	 void initializeMs(String loginType) { //ALL GUI  

		 try {
		     for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		         if ("Nimbus".equals(info.getName())) {
		             UIManager.setLookAndFeel(info.getClassName());
		             break;
		         }
		     }
		 } catch (Exception e) {
		     // If Nimbus is not available, you can set the GUI to another look and feel.
		 }
      vtMemberType = new Vector();
      vtMemberType.add("Faculty");
      vtMemberType.add("Library Staff");
      vtMemberType.add("Student");      
      vtYear = new Vector();      
      for(int i = 2016; i >= 1900; i--)
      {
        vtYear.add(i);
      }      
      vtMonth = new Vector();      
      for(int i = 1; i <=12; i++ )
       {
         vtMonth.add(i);
       }
      
      vtItemType = new Vector();     
      vtItemType.add("Book");
      vtItemType.add("DVD or CD");
      vtItemType.add("Magazine");
      vtItemType.add("Newspaper");
      vtItemType.add("Article");
      
		DreamCollegeLibrary = new JFrame();
       DreamCollegeLibrary.setVisible(true);
		DreamCollegeLibrary.setTitle("Dream College Library");
		DreamCollegeLibrary.setBounds(100, 100, 672, 481);
		DreamCollegeLibrary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DreamCollegeLibrary.getContentPane().setLayout(new BoxLayout(DreamCollegeLibrary.getContentPane(), BoxLayout.X_AXIS));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		DreamCollegeLibrary.getContentPane().add(tabbedPane);
		
		checkoutPanel = new JPanel();
		tabbedPane.addTab("Check Out", null, checkoutPanel, null);
		checkoutPanel.setLayout(new BorderLayout(0, 0));
		
		mid = new JPanel();
		mid.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		checkoutPanel.add(mid, BorderLayout.CENTER);
		mid.setLayout(new BorderLayout(0, 0));
		
		mid2 = new JPanel();
		mid.add(mid2, BorderLayout.CENTER);
		mid2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		mid2.add(scrollPane_2, BorderLayout.CENTER);
		
		checkoutTable = new JTable();
		
		checkNames.addElement("ISBN/ID");
		checkNames.addElement("Title");
		checkNames.addElement("Author");
		checkNames.addElement("Edition");
		checkNames.addElement("Type");
		
		checkoutModel = new DefaultTableModel(checkItems, checkNames);

		checkoutTable = new JTable(checkoutModel);
		checkoutTable.setColumnSelectionAllowed(false);
		checkoutTable.getSelectionModel().addListSelectionListener(this);
		
		checkoutTable.setShowVerticalLines(false);		
		scrollPane_2.setViewportView(checkoutTable);			
		
		JPanel top2 = new JPanel();
		mid.add(top2, BorderLayout.NORTH);
		top2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_24 = new JPanel();
		top2.add(panel_24, BorderLayout.SOUTH);
		
		 addCheckoutBtn = new JButton("Add");
		 panel_24.add(addCheckoutBtn);
		 
		 removeCheckoutBtn = new JButton("Remove");
		 panel_24.add(removeCheckoutBtn);
		 
		 clearCheckoutBtn = new JButton("Clear");
		 panel_24.add(clearCheckoutBtn);
		 clearCheckoutBtn.addActionListener(this);
		 removeCheckoutBtn.addActionListener(this);
		 addCheckoutBtn.addActionListener(this);
		
		JPanel panel_15 = new JPanel();
		top2.add(panel_15);
		panel_15.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_9 = new JLabel("ISBN / Item ID:");
		panel_15.add(lblNewLabel_9);
		
		isbnTxt = new JTextField();
		panel_15.add(isbnTxt);
		isbnTxt.setColumns(10);
		
		JPanel panel_16 = new JPanel();
		top2.add(panel_16, BorderLayout.NORTH);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DreamCollegeLibrary.dispose();
				LoginScreen ls = new LoginScreen();
				String args[] = {};
				ls.main(args);
			}
		});
		panel_16.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel label_2 = new JLabel("");
		panel_16.add(label_2);
		
		panel_16.add(btnLogOut);
		
		JLabel label_3 = new JLabel("");
		panel_16.add(label_3);
		
		JLabel lblNewLabel_10 = new JLabel("Member ID: ");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_16.add(lblNewLabel_10);
		
		memberIdTxt = new JTextField();
		panel_16.add(memberIdTxt);
		memberIdTxt.setColumns(10);
		
		 confirmMemberBtn = new JButton("Confirm Member");
		confirmMemberBtn.addActionListener(this);
		panel_16.add(confirmMemberBtn);
		
		JPanel bot2 = new JPanel();
		mid.add(bot2, BorderLayout.SOUTH);
		
		 btnCheckIn = new JButton("Check In");
		 btnCheckIn.addActionListener(this);
		bot2.add(btnCheckIn);
		
		 checkoutBtn = new JButton("Check Out");
		 checkoutBtn.addActionListener(this);
		bot2.add(checkoutBtn);
		
		JPanel top = new JPanel();
		checkoutPanel.add(top, BorderLayout.NORTH);
		
		JPanel right = new JPanel();
		right.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		checkoutPanel.add(right, BorderLayout.EAST);
		right.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		right.add(layeredPane);
		
		JLabel lblNewLabel_13 = new JLabel("Items Borrowed:");
		lblNewLabel_13.setBounds(6, 10, 292, 66);
		layeredPane.add(lblNewLabel_13);
		
		//Icon printIcon = new ImageIcon(MainScreen.class.getResource("/images/printIcon.png"));
		
		JButton borrowedBtn = new JButton();
		borrowedBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("print");
				Lists l = new Lists();
				l.createReceipt("Items Borrowed", connect.getBorrowed(checkoutMemberID), checkoutMemberID);
				l.setVisible(true);
				l.toBack();
				l.printReceipt();
				l.dispose();
			}
		});
		layeredPane.setLayer(borrowedBtn, 1);
		borrowedBtn.setIcon(new ImageIcon(MainScreen.class.getResource("/images/printIcon.png")));
		borrowedBtn.setBounds(223, 15, 50, 50);
		layeredPane.add(borrowedBtn);
		
		
		JScrollPane sPane = new JScrollPane();
		
		 borrowedList = new JList();
		sPane.setViewportView(borrowedList);

		right.add(sPane);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		right.add(layeredPane_1);
		
		JLabel lblNewLabel_12 = new JLabel("Fees Owed:");
		lblNewLabel_12.setBounds(0, 10, 292, 66);
		layeredPane_1.add(lblNewLabel_12);
		
		JButton feesBtn = new JButton();
		feesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Lists l = new Lists();
				l.createReceiptFees("Fees Owed", connect.getFees(checkoutMemberID), checkoutMemberID, totaltxt.getText());
				
				l.setVisible(true);
				l.toBack();
				l.printReceipt();
				l.dispose();
			}
		});
		layeredPane_1.setLayer(feesBtn, 1);
		feesBtn.setBounds(223, 15, 50, 50);
		feesBtn.setIcon(new ImageIcon(MainScreen.class.getResource("/images/printIcon.png")));
		layeredPane_1.add(feesBtn);
		
		JScrollPane scrollpane = new JScrollPane();
		
		 feesList = new JList();
		scrollpane.setViewportView(feesList);

		right.add(scrollpane);
		
		JPanel panel_17 = new JPanel();
		right.add(panel_17);
		panel_17.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel lblNewLabel_14 = new JLabel("Total Due:");
		panel_17.add(lblNewLabel_14);
		
		totaltxt = new JTextField();
		totaltxt.setEditable(false);
		panel_17.add(totaltxt);
		totaltxt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Cash:");
		panel_17.add(lblNewLabel);
		
		cashTxt = new JTextField();
		panel_17.add(cashTxt);
		cashTxt.setColumns(10);
		
		 payBtn = new JButton("Pay Fees");
		 payBtn.addActionListener(this);
		
		 showBtn = new JButton("Show Borrowed/Fees");
		showBtn.addActionListener(this);
		panel_17.add(showBtn);
		panel_17.add(payBtn);
		
		JPanel searchPanel = new JPanel();
		tabbedPane.addTab("Search", null, searchPanel, null);
		searchPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		searchPanel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_21 = new JPanel();
		panel.add(panel_21);
		panel_21.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_22 = new JPanel();
		panel_21.add(panel_22);
		panel_22.setLayout(new BorderLayout(0, 0));
		
		searchTxt = new JTextField();
		searchTxt.setColumns(10);
		panel_22.add(searchTxt);
		
		Vector<String> searchVt = new Vector<String>();
		searchVt.add("Search by ISBN/ID");
		searchVt.add("Search by Title");
		searchVt.add("Search by Author");
		
		searchCombo = new JComboBox(searchVt);
		
		panel_22.add(searchCombo, BorderLayout.WEST);
			
	    searchBtn = new JButton("Search");
	    searchBtn.addActionListener(this);
	    panel_22.add(searchBtn, BorderLayout.EAST);
	    
	    JPanel panel_23 = new JPanel();
	    panel_21.add(panel_23);
	    
	     chckbxBooks = new JCheckBox("Books");
	     chckbxBooks.setSelected(true);
	     chckbxBooks.addItemListener(this);
	    panel_23.add(chckbxBooks);
	    
	     chckbxCdsdvds = new JCheckBox("CDs/DVDs");	 
	     chckbxCdsdvds.setSelected(true);
	     chckbxCdsdvds.addItemListener(this);
	    panel_23.add(chckbxCdsdvds);
	    
	     chckbxMagazinesnewspapers = new JCheckBox("Magazines/Newspapers");
	     chckbxMagazinesnewspapers.setSelected(true);
	     chckbxMagazinesnewspapers.addItemListener(this);
	    panel_23.add(chckbxMagazinesnewspapers);
	    
	     chckbxArticles = new JCheckBox("Articles");
	     chckbxArticles.setSelected(true);
	     chckbxArticles.addItemListener(this);
	    panel_23.add(chckbxArticles);
		
				
		JScrollPane scrollPane_1 = new JScrollPane();
		searchPanel.add(scrollPane_1, BorderLayout.CENTER);
		
		columnNames= new Vector<String>();
		columnNames.addElement("ISBN/ID");
		columnNames.addElement("Title");
		columnNames.addElement("Author");
		columnNames.addElement("Edition");
		columnNames.addElement("Type");
		columnNames.addElement("Year");
		columnNames.addElement("Month");
		columnNames.addElement("Qty Total");
		columnNames.addElement("Qty Available");		
		columnNames.addElement("Location");
		
		searchItems = connect.getFullResourceList();
		model_1 = new DefaultTableModel(searchItems, columnNames);
		sorter = new TableRowSorter<DefaultTableModel>(model_1);
		table_1 = new JTable(model_1);
		table_1.setColumnSelectionAllowed(false);
		table_1.getSelectionModel().addListSelectionListener(this);
		//table_1.setRowSorter(sorter);		
		
		table_1.setShowVerticalLines(false);
		scrollPane_1.setViewportView(table_1);
		
		JPanel managePanel = new JPanel();
		tabbedPane.addTab("Manage Resources", null, managePanel, null);
		managePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		managePanel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new GridLayout(0, 3, 0, 0));
		
		removeResourceBtn = new JButton("Remove Item");
		removeResourceBtn.addActionListener(this);
		panel_5.add(removeResourceBtn);
		
		 updateResourceBtn = new JButton("Update Item");
		 updateResourceBtn.addActionListener(this);
		panel_5.add(updateResourceBtn);
		
		 addResourceBtn = new JButton("Add Item");
		addResourceBtn.addActionListener(this);
		panel_5.add(addResourceBtn);
		
		JPanel panel_20 = new JPanel();
		panel_4.add(panel_20, BorderLayout.NORTH);
		panel_20.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_18 = new JPanel();
		panel_20.add(panel_18);
		panel_18.setLayout(new BorderLayout(0, 0));
		
		searchResourceTxt = new JTextField();
		panel_18.add(searchResourceTxt, BorderLayout.CENTER);
		searchResourceTxt.setColumns(10);
		
		 searchResourceCombo = new JComboBox(searchVt);
		 
		panel_18.add(searchResourceCombo, BorderLayout.WEST);
		
	    itemSearchBtn = new JButton("Search");
		itemSearchBtn.addActionListener(this);
		panel_18.add(itemSearchBtn, BorderLayout.EAST);
		
		 btnDeselect = new JButton("deselect");
		btnDeselect.addActionListener(this);
		panel_18.add(btnDeselect, BorderLayout.SOUTH);
				
		rowOne = new Vector<String>();
	    rowOne.addElement("");
	    rowOne.addElement("");
	    rowOne.addElement("");
	    rowOne.addElement("");
	    rowOne.addElement("");
	    rowOne.addElement("");
	    	    
	    itemVector.addElement(rowOne);
	    
		 names = new Vector<String>();
		names.addElement("ISBN/ID");
		names.addElement("Title");
		names.addElement("Author");
		names.addElement("Edition");
		names.addElement("Type");
		names.addElement("Total Qty");
		
		JScrollPane scrollPane = new JScrollPane();
		panel_4.add(scrollPane, BorderLayout.CENTER);
		
		itemVector = connect.getResourceList();
		
		model = new DefaultTableModel(itemVector, names);
		
		
		
		table = new JTable(model);
		table.setColumnSelectionAllowed(false);
		table.getSelectionModel().addListSelectionListener(this);
		
		table.setShowVerticalLines(false);

		//table = new JTable(itemVector, names);
		
		scrollPane.setViewportView(table);				
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		managePanel.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_3.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Item ISBN/SKU:");
		panel_8.add(lblNewLabel_1);
		
		isbntxt = new JTextField();
		panel_8.add(isbntxt);
		isbntxt.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_3.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel titleLabel = new JLabel("Title:");
		panel_9.add(titleLabel);
		
		titleTxt = new JTextField();
		panel_9.add(titleTxt);
		titleTxt.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		panel_3.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Author: ");
		panel_10.add(lblNewLabel_3);
		
		authorTxt = new JTextField();
		panel_10.add(authorTxt);
		authorTxt.setColumns(10);
		
		JPanel panel_12 = new JPanel();
		panel_3.add(panel_12);
		panel_12.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblYear = new JLabel("Year:");
		panel_12.add(lblYear);
		
		 yearCombo = new JComboBox(vtYear);
		panel_12.add(yearCombo);
		
		JLabel lblNewLabel_2 = new JLabel("Month: ");
		panel_12.add(lblNewLabel_2);
		
		 monthCombo = new JComboBox(vtMonth);
		panel_12.add(monthCombo);
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		panel_6.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblEdition = new JLabel("Edition:");
		panel_6.add(lblEdition);
		
		editionTxt = new JTextField();
		panel_6.add(editionTxt);
		editionTxt.setColumns(10);
		
		JLabel lblQuantityTotal = new JLabel("Quantity Total:");
		panel_6.add(lblQuantityTotal);
		
		qtyText = new JTextField();
		panel_6.add(qtyText);
		qtyText.setColumns(10);
		
		JPanel panel_19 = new JPanel();
		panel_3.add(panel_19);
		panel_19.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_15 = new JLabel("Location:");
		panel_19.add(lblNewLabel_15);
		
		locationTxt = new JTextField();
		panel_19.add(locationTxt);
		locationTxt.setColumns(10);
		
		JPanel panel_11 = new JPanel();
		panel_3.add(panel_11);
		panel_11.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblType = new JLabel("Type:");
		panel_11.add(lblType);
		
		 typeCombo = new JComboBox(vtItemType);
		panel_11.add(typeCombo);
		
		JLabel lblNewLabel_4 = new JLabel("Description:");
		panel_11.add(lblNewLabel_4);
		
		 descriptionTxt = new JTextArea();
		panel_3.add(descriptionTxt);
		
		JPanel memberPanel = new JPanel();
		tabbedPane.addTab("Memberships", null, memberPanel, null);
		memberPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		memberPanel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7, BorderLayout.SOUTH);
		
		removeMemberBtn = new JButton("Remove");
      removeMemberBtn.addActionListener(this);
		panel_7.add(removeMemberBtn);
		
		updateMemberBtn = new JButton("Update");
		updateMemberBtn.addActionListener(this);
		panel_7.add(updateMemberBtn);
		
		addMemberBtn = new JButton("Add");
      addMemberBtn.addActionListener(this);
		panel_7.add(addMemberBtn);
		
		JButton printCardBtn = new JButton("Print Card");
		printCardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MembershipCard frame = new MembershipCard();
				Calendar date = Calendar.getInstance();
			    date.setTime(new Date());
			    Format f = new SimpleDateFormat("MM-yyyy");
			    date.add(Calendar.YEAR,5);
				frame.createCard(lnameTxt.getText() + ", " + fnameTxt.getText(), memberIDtxt.getText(), f.format(date.getTime()), memberTypeCombo.getSelectedItem().toString());
				frame.setVisible(true);
				
			}
		});
		panel_7.add(printCardBtn);
		
		JPanel panel_13 = new JPanel();
		panel_2.add(panel_13, BorderLayout.CENTER);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_13.add(panel_14, BorderLayout.NORTH);
		panel_14.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Member ID:");
		panel_14.add(lblNewLabel_5);
		
		searchMtxt = new JTextField();
		panel_14.add(searchMtxt);
		searchMtxt.setColumns(10);
		
		searchMBtn = new JButton("Search");
      searchMBtn.addActionListener(this);
		panel_14.add(searchMBtn);
		
		JLabel label = new JLabel("");
		panel_14.add(label);
		
		 btnDeselect_1 = new JButton("Deselect");
		btnDeselect_1.addActionListener(this);
		panel_14.add(btnDeselect_1);
		
		JLabel label_1 = new JLabel("");
		panel_14.add(label_1);
		
		memberList = new JList();
      memberList.addListSelectionListener(this);
		panel_13.add(memberList, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		memberPanel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
	   generateIdBtn = new JButton("Generate New ID");
		generateIdBtn.addActionListener(this);
		
		JLabel lblNewLabel_8 = new JLabel("Type");
		panel_1.add(lblNewLabel_8);
		
		memberTypeCombo = new JComboBox(vtMemberType); //  ------------>> declared the combo box on the top
		panel_1.add(memberTypeCombo);
		panel_1.add(generateIdBtn);
		
		JLabel lblNewLabel_6 = new JLabel("Member ID");
		panel_1.add(lblNewLabel_6);
		
		memberIDtxt = new JTextField();
		memberIDtxt.setEditable(false);
		panel_1.add(memberIDtxt);
		memberIDtxt.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Password:");
		panel_1.add(lblNewLabel_11);
		
		passwordTxt = new JPasswordField();
		panel_1.add(passwordTxt);
		passwordTxt.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("First Name");
		panel_1.add(lblNewLabel_7);
		
		fnameTxt = new JTextField();
		panel_1.add(fnameTxt);
		fnameTxt.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		panel_1.add(lblLastName);
		
		lnameTxt = new JTextField();
		lnameTxt.setColumns(10);
		panel_1.add(lnameTxt);
		
		JLabel lblPhone = new JLabel("Phone");
		panel_1.add(lblPhone);
		
		phoneTxt = new JTextField();
		phoneTxt.setColumns(10);
		panel_1.add(phoneTxt);
		
		JLabel lblEmail = new JLabel("Email");
		panel_1.add(lblEmail);
		
		emailTxt = new JTextField();
		emailTxt.setColumns(10);
		panel_1.add(emailTxt);
		
		JPanel reportsPanel = new JPanel();
		tabbedPane.addTab("Reports", null, reportsPanel, null);
		reportsPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_25 = new JPanel();
		reportsPanel.add(panel_25, BorderLayout.NORTH);
		panel_25.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_27 = new JPanel();
		panel_25.add(panel_27);
		
		JLabel lblFrom = new JLabel("From: ");
		panel_27.add(lblFrom);
		
		 fromMonth = new JComboBox();
		 panel_27.add(fromMonth);
		fromMonth.setModel(new DefaultComboBoxModel(new String[] {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		
		fromYear = new JTextField();
		panel_27.add(fromYear);
		fromYear.setToolTipText("YYYY");
		fromYear.setColumns(10);
		
		JLabel lblTo = new JLabel("To:");
		panel_27.add(lblTo);
		
		toMonth = new JComboBox();
		panel_27.add(toMonth);
		toMonth.setModel(new DefaultComboBoxModel(new String[] {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		
		toYear = new JTextField();
		panel_27.add(toYear);
		toYear.setToolTipText("YYYY");
		toYear.setColumns(10);
		
		JPanel panel_28 = new JPanel();
		panel_25.add(panel_28);
		
		 reportType = new JComboBox();
		 panel_28.add(reportType);
		 reportType.setModel(new DefaultComboBoxModel(new String[] {"Checked-Out Items", "Overdue Items", "Fees Owed", "Fees Paid"}));
		 
		 createReportBtn = new JButton("Create Report");
		 panel_28.add(createReportBtn);
		createReportBtn.addActionListener(this);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		reportsPanel.add(scrollPane_3, BorderLayout.CENTER);
		
		reportTable = new JTable();
		scrollPane_3.setViewportView(reportTable);
		
		JPanel panel_26 = new JPanel();
		reportsPanel.add(panel_26, BorderLayout.SOUTH);
		
		JButton printReportBtn = new JButton("Print");
		printReportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MessageFormat footer = new MessageFormat(new Date() + " - Page {0,number,integer}");
				MessageFormat header = new MessageFormat("Dream College Library - " + reportType.getSelectedItem());
				
				try {
				    reportTable.print(JTable.PrintMode.FIT_WIDTH, header, footer); 
				} catch (java.awt.print.PrinterException e) {
				     System.err.format("Cannot print %s%n", e.getMessage()); 
				}
			}
		});
		panel_26.add(printReportBtn);
      
		if(loginType.charAt(0) != '1')
    	{
			tabbedPane.setEnabledAt(2, false);
			tabbedPane.setEnabledAt(3, false);
			tabbedPane.setEnabledAt(4, false);
			memberIdTxt.setEditable(false);
			memberIdTxt.setText(loginType);
			checkoutMemberID = Integer.parseInt(memberIdTxt.getText());
			confirmMemberBtn.setEnabled(false);
			confirmMemberBtn.setVisible(false);
			payBtn.setEnabled(false);
			payBtn.setVisible(false);
			cashTxt.setEditable(false);
			cashTxt.setVisible(false);
			lblNewLabel.setVisible(false);
		}
		
      DreamCollegeLibrary.setVisible(true);
      
      memberList.setListData(connect.getMember());
	}
	
	
////////////////ACTION EVENTS & HELPER METHODS///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public void resetID() //generates a new memberID 
	{
	       Random randomId = new Random();
	       int id = 0;
	       String memberType = "";
	       int index = memberTypeCombo.getSelectedIndex();      
	       if(index == 0) //CHECK MEMBER TYPE 
	         memberType = "f";
	       else if(index == 1)
	         memberType = "l";
	       else if(index == 2)
	         memberType = "s";
	       
	       if(memberType.equals("f"))
	    	   id = 2000000 + randomId.nextInt(2999999 - 2000000 + 1);
	       else if(memberType.equals("l"))
	    	   id = 1000000 + randomId.nextInt(1999999 - 1000000 + 1);
	       else if(memberType.equals("s"))
	    	   id = 3000000 + randomId.nextInt(3999999 - 3000000 + 1);
	    	   
	       memberIDtxt.setText(String.valueOf(id));	}
		
   public void actionPerformed(ActionEvent e)
   {  
     if(e.getSource() == addMemberBtn) //ADDMEMBER BUTTON EVENT
     {   
     String memberID = memberIDtxt.getText();     //GET INPUTS
     String password = passwordTxt.getText();
     String firstName = fnameTxt.getText();     
     String lastName = lnameTxt.getText();     
     String phone = phoneTxt.getText();     
     String email = emailTxt.getText();     
     String memberType = "";     
     int index = memberTypeCombo.getSelectedIndex();      
       if(index == 0) //CHECK MEMBER TYPE 
         memberType = "f";
       else if(index == 1)
         memberType = "l";
       else if(index == 2)
         memberType = "s";
       memberList.removeListSelectionListener(this);
       
       
      connect.AddMember(memberID,password,firstName,lastName,phone,email,"1",memberType); //ADD MEMBER METHOD IN CONNECTDB CLASS
      memberList.setListData(connect.getMember());
      memberList.addListSelectionListener(this);
     }
     
     if(e.getSource() == generateIdBtn)
     {
    	 resetID();
     }
     
     if(e.getSource() == updateMemberBtn)
     {    	 
    	 String memberID = memberIDtxt.getText();     //GET INPUTS
         String pass = passwordTxt.getText();         
         String firstName = fnameTxt.getText();     
         String lastName = lnameTxt.getText();     
         String phone = phoneTxt.getText();     
         String email = emailTxt.getText();     
         String memberType = "";     
         int index = memberTypeCombo.getSelectedIndex();      
           if(index == 0) //CHECK MEMBER TYPE 
             memberType = "f";
           else if(index == 1)
             memberType = "l";
           else if(index == 2)
             memberType = "s";
           String[] tempArray = String.valueOf(memberList.getSelectedValue()).split(" ");
           int id = Integer.parseInt(tempArray[0]);
           memberList.removeListSelectionListener(this);
         connect.updateMember(memberID, firstName, lastName, phone, email, "1", memberType, id, pass);        
         memberList.setListData(connect.getMember());
         memberList.addListSelectionListener(this);
     }
     if(e.getSource() == removeMemberBtn)
     {
       String[] tempArray = String.valueOf(memberList.getSelectedValue()).split(" ");
       int id = Integer.parseInt(tempArray[0]);
       
       memberList.removeListSelectionListener(this);
       
       connect.removeMember(id);
       
       memberList.setListData(connect.getMember());
       memberList.addListSelectionListener(this);
     }
     if(e.getSource() == searchMBtn)
     {     
       String search = searchMtxt.getText();
       if(!search.equals(""))
       {
	       
	       if(!connect.getSearchMember(search).isEmpty())
	       {
		       memberList.removeListSelectionListener(this);     		      
		       memberList.setListData(connect.getSearchMember(search));
		       memberList.addListSelectionListener(this);
	       }
       }
       else
       {
       memberList.removeListSelectionListener(this);       
       memberList.setListData(connect.getMember());
       memberList.addListSelectionListener(this);
       }        
     }
     
     if(e.getSource() == addResourceBtn)
     {
    	 	System.out.println("add resource btn");
    	 	
    	 	int itemID = Integer.parseInt(isbntxt.getText());
    	 	String title = titleTxt.getText();
    	 	String author = authorTxt.getText();
    	 	int year = Integer.parseInt(yearCombo.getSelectedItem().toString());
    	 	int month = Integer.parseInt(monthCombo.getSelectedItem().toString());
    	 	String edition = editionTxt.getText();
    	 	int qtyTotal = Integer.parseInt(qtyText.getText());
    	 	String type = typeCombo.getSelectedItem().toString();
    	 	String desc = descriptionTxt.getText();
    	 	String location = locationTxt.getText();
    	 	
    		table.getSelectionModel().removeListSelectionListener(this);
    	 	connect.addResource(itemID, title, author, year,month,edition ,qtyTotal,type,desc, location);
    	 	itemVector = connect.getResourceList();
    	 	model = new DefaultTableModel(itemVector, names);
    	 	table.setModel(model);
    		searchItems = connect.getFullResourceList();
    		model_1 = new DefaultTableModel(searchItems, columnNames);
    		table_1.setModel(model_1);
    		table.getSelectionModel().addListSelectionListener(this);

     }
     if(e.getSource() == updateResourceBtn)
	   {
    	 System.out.println("update resource btn");
 	 	
 	 	int itemID = Integer.parseInt(isbntxt.getText());
 	 	String title = titleTxt.getText();
 	 	String author = authorTxt.getText();
 	 	int year = Integer.parseInt(yearCombo.getSelectedItem().toString());
 	 	int month = Integer.parseInt(monthCombo.getSelectedItem().toString());
 	 	String edition = editionTxt.getText();
 	 	int qtyTotal = Integer.parseInt(qtyText.getText());
 	 	String type = typeCombo.getSelectedItem().toString();
 	 	String desc = descriptionTxt.getText();
 	 	String location = locationTxt.getText();
 	 	
		table.getSelectionModel().removeListSelectionListener(this);
 	 	connect.updateResource(itemID, title, author, year,month,edition ,qtyTotal,type,desc, location);
 	 	itemVector = connect.getResourceList();
 	 	model = new DefaultTableModel(itemVector, names);
 	 	table.setModel(model);
		table.getSelectionModel().addListSelectionListener(this);
		searchItems = connect.getFullResourceList();
		model_1 = new DefaultTableModel(searchItems, columnNames);
		table_1.setModel(model_1);
	   }
     if(e.getSource() == removeResourceBtn)
     {
    	 int row  = table.getSelectedRow();
		 int id = Integer.parseInt(String.valueOf(table.getValueAt(row, 0)));
       
		 table.getSelectionModel().removeListSelectionListener(this);
       
       connect.removeResource(id);
       
       itemVector = connect.getResourceList();
	 	model = new DefaultTableModel(itemVector, names);
	 	table.setModel(model);
		table.getSelectionModel().addListSelectionListener(this);
		searchItems = connect.getFullResourceList();
		model_1 = new DefaultTableModel(searchItems, columnNames);
		table_1.setModel(model_1);
     }
     if(e.getSource() == searchBtn)
     {
    	 System.out.println("search");
    	 if(searchCombo.getSelectedIndex() == 0)
    	 {
    		 	searchItems = connect.getSearchItems("itemID", searchTxt.getText());
    			model_1 = new DefaultTableModel(searchItems, columnNames);
    			table_1.setModel(model_1);
    	 }
    	 if(searchCombo.getSelectedIndex() == 1)
    	 {
    		 searchItems = connect.getSearchItems("title", searchTxt.getText());
    		 model_1 = new DefaultTableModel(searchItems, columnNames);
 			 table_1.setModel(model_1);
    	 }
    	 if(searchCombo.getSelectedIndex() == 2)
    	 {
    		 searchItems= connect.getSearchItems("author", searchTxt.getText());
    		 model_1 = new DefaultTableModel(searchItems, columnNames);
 			 table_1.setModel(model_1);
    	 }
     }
     if(e.getSource() == itemSearchBtn)
     {
    	 System.out.println("search");
    	 if(searchResourceCombo.getSelectedIndex() == 0)
    	 {
    		 	itemVector = connect.searchItems("itemID", searchResourceTxt.getText());
    		 	model = new DefaultTableModel(itemVector, names);
    			table.setModel(model);
    	 }
    	 if(searchResourceCombo.getSelectedIndex() == 1)
    	 {
    		 itemVector = connect.searchItems("title", searchResourceTxt.getText());
    		 model = new DefaultTableModel(itemVector, names);
 			 table.setModel(model);
    	 }
    	 if(searchResourceCombo.getSelectedIndex() == 2)
    	 {
    		 itemVector= connect.searchItems("author", searchResourceTxt.getText());
    		 model = new DefaultTableModel(itemVector, names);
 			 table.setModel(model);
    	 }
     }
     if(e.getSource() == btnDeselect)
     {
    	 	table.getSelectionModel().removeListSelectionListener(this);
    	 	table.getSelectionModel().clearSelection();
			table.getSelectionModel().addListSelectionListener(this);
			
			isbntxt.setText("");
			titleTxt.setText("");
			authorTxt.setText("");
			editionTxt.setText("");
			qtyText.setText("");
			locationTxt.setText("");
			descriptionTxt.setText("");
     }
     if(e.getSource() == btnDeselect_1)
     {    	 				
			memberList.removeListSelectionListener(this);
	         memberList.setSelectedIndex(-1);
	         memberList.addListSelectionListener(this);
			
			memberIDtxt.setText("");
			passwordTxt.setText("");
			fnameTxt.setText("");
			lnameTxt.setText("");
			phoneTxt.setText("");
			emailTxt.setText("");
     }
     if(e.getSource() == addCheckoutBtn)
     {
    	 try{
    	 
	         int id = Integer.parseInt(isbnTxt.getText());   	 
	         
	    	 if(connect.getItem(id).isEmpty())
	    	 {
	    		 JOptionPane.showMessageDialog(DreamCollegeLibrary,"Resource deos not exist in database",
	 				    "Checkout Warning", JOptionPane.WARNING_MESSAGE);
	    	 }
	    	 	    	 	    	 
	    	 else
	    	 {
		    	 checkItems.add(connect.getItem(id));
		    	 checkoutModel = new DefaultTableModel(checkItems, checkNames);
		    	 checkoutTable.setModel(checkoutModel);
	    	 }
    	 }
    	 catch(Exception ex)
    	 {
    		 JOptionPane.showMessageDialog(DreamCollegeLibrary,"Please enter valid item and member IDs ",
  				    "Checkout Warning",JOptionPane.WARNING_MESSAGE);
    	 }
     }
     
     if(e.getSource() == clearCheckoutBtn)
     {
    	 checkItems.clear();
    	 checkoutModel = new DefaultTableModel(checkItems, checkNames);
    	 checkoutTable.setModel(checkoutModel);
    	 isbnTxt.setText("");
     }
     
     if(e.getSource() == removeCheckoutBtn)
     {
    	 int row = checkoutTable.getSelectedRow();
    	 checkItems.removeElementAt(row);
    	 checkoutModel = new DefaultTableModel(checkItems, checkNames);
    	 checkoutTable.setModel(checkoutModel);
     }
     if(e.getSource() == checkoutBtn)
     {
    	 try{
	    	 int id = checkoutMemberID;
	    	 boolean worked = false;
	    	 for(int i = 0 ; i< checkItems.size(); i++)
	    	 {
	    		 
	    		 Vector vt = checkItems.get(i);  
	    		 if(connect.checkedOut(Integer.parseInt(String.valueOf(vt.get(0))), id))
		    	 {
		    		 JOptionPane.showMessageDialog(DreamCollegeLibrary,"Member already has item "+String.valueOf(vt.get(0)),
		    				    "Checkout Warning",JOptionPane.WARNING_MESSAGE);
		    	 }
	    		 else if(connect.getItemQtyAvailable(Integer.parseInt(String.valueOf(vt.get(0)))) <= 0)
		    	 {
		    		 JOptionPane.showMessageDialog(DreamCollegeLibrary,"Sorry this item is out of stock",
		    				    "Checkout Warning",JOptionPane.WARNING_MESSAGE);
		    	 }
	    		 else if(Double.parseDouble(totaltxt.getText()) > 50.0)
		    	 {
		    		 JOptionPane.showMessageDialog(DreamCollegeLibrary,"Member is restricted. Pay fines to lift restriction",
		    				    "Checkout Warning",JOptionPane.WARNING_MESSAGE);
		    	 }
		    	 else
		    	 {
		    		 connect.checkout(Integer.parseInt(String.valueOf(vt.get(0))), id);
		    		 worked = true;
		    	 }
	    	 }
	    	 if(worked)
	    	 {
	    	 checkItems.clear();
	    	 checkoutModel = new DefaultTableModel(checkItems, checkNames);
	    	 checkoutTable.setModel(checkoutModel);
	    	 isbnTxt.setText("");
	    	 searchItems = connect.getFullResourceList();
	 		 model_1 = new DefaultTableModel(searchItems, columnNames);
	 		 table_1.setModel(model_1);
	    	 }
    	}
    	 catch(Exception ex)
    	 {
    		 
    		 JOptionPane.showMessageDialog(DreamCollegeLibrary,ex.getMessage(),
   				    "Checkout Warning",JOptionPane.WARNING_MESSAGE);
    	 }
     }
     if(e.getSource() == confirmMemberBtn)
     {
    	 
    	 checkoutMemberID = Integer.parseInt(memberIdTxt.getText());   	 
    	 
     }
     if(e.getSource() == showBtn)
     {
    	 try{
        	 connect.getSelectedMember(checkoutMemberID).get(0);    	     	 
        	 
        	 connect.updateFeeDue(checkoutMemberID);
        	 
        	 borrowedList.setListData(connect.getBorrowed(checkoutMemberID));
        	 
        	 feesList.setListData(connect.getFees(checkoutMemberID));
        	 
        	 totaltxt.setText(String.valueOf(connect.getTotalFees(checkoutMemberID)));
        	 }
        	 catch(Exception ex)
        	 {

        		 JOptionPane.showMessageDialog(DreamCollegeLibrary,"Member ID entered does not exist",
       				    "Checkout Warning",JOptionPane.WARNING_MESSAGE);
        	 }
     }
     if(e.getSource() == payBtn)
     {
    	 if(cashTxt.getText() != null && cashTxt.getText() != "" && Double.parseDouble(cashTxt.getText()) >= Double.parseDouble(totaltxt.getText()))
    	 {
    		 double cash = Double.parseDouble(cashTxt.getText());
    		 double totalOwed = Double.parseDouble(totaltxt.getText());
    		 double change = cash - totalOwed;
    		 Object[] options = {"Print Receipt", "No Receipt"};
    		 int n = JOptionPane.showOptionDialog(DreamCollegeLibrary, "Change Due: " + change, "Would you like a receipt?",
    				 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[1]);
    		 if(n == 0)
    		 {
    			 System.out.println( "print receipt");
    			 Receipt r = new Receipt();
    			 r.createReceipt(totalOwed, cash, change, connect.getFees(checkoutMemberID));    			
    			 r.setVisible(true);
    			 r.printReceipt();
    		 }
    			 ////////PRINT RECEIPT///////////!!
    		 connect.updatePaid(checkoutMemberID);
    		
    	 }
     }
     if(e.getSource() == btnCheckIn)
     {
    	 try{
        	 System.out.println("CHECKIN BTN");

	    	 int id = checkoutMemberID;
	    	 for(int i = 0 ; i< checkItems.size(); i++)
	    	 {
	    		 Vector vt = checkItems.get(i);  		 
	    		 System.out.println(Integer.parseInt(String.valueOf(vt.get(0))));
	    		 connect.checkIn(Integer.parseInt(String.valueOf(vt.get(0))), id);
	    	 }
	    	 checkItems.clear();
	    	 checkoutModel = new DefaultTableModel(checkItems, checkNames);
	    	 checkoutTable.setModel(checkoutModel);
	    	 isbnTxt.setText("");
	    	 searchItems = connect.getFullResourceList();
		 	 model_1 = new DefaultTableModel(searchItems, columnNames);
		 	 table_1.setModel(model_1);
    	 }
    	 catch(Exception ex)
    	 {
    		 JOptionPane.showMessageDialog(DreamCollegeLibrary,"Error",
   				    "Checkout Warning",JOptionPane.WARNING_MESSAGE);
    	 }
     }
     if(e.getSource() == createReportBtn)
     {
    	 String fMonth, tMonth;
    	 String fYear,tYear;
    	 Vector<String> vt = new Vector<String>();
    	 if(reportType.getSelectedIndex() == 0)
    	 {
	    	 vt.addElement("ItemID");
	    	 vt.addElement("Title");
	    	 vt.addElement("Type");
	    	 vt.addElement("MemberID");
	    	 vt.addElement("checkout date");
	    	 vt.addElement("due date");    	 
	    	 reportModel = new DefaultTableModel(connect.checkoutReport(),vt);
	    	 reportTable.setModel(reportModel);
    	 }
    	 else 
    		 if(reportType.getSelectedIndex() == 1)
    		 {
    			 vt.addElement("ItemID");
    	    	 vt.addElement("Title");
    	    	 vt.addElement("Type");
    	    	 vt.addElement("MemberID");
    	    	 vt.addElement("checkout date");
    	    	 vt.addElement("due date");    	 
    	    	 reportModel = new DefaultTableModel(connect.overdueReport(),vt);
    	    	 reportTable.setModel(reportModel);
    		 }
    		 else 
        		 if(reportType.getSelectedIndex() == 2)
        		 {
        	    	 vt.addElement("MemberID");
        			 vt.addElement("ItemID");
        	    	 vt.addElement("Title");
        	    	 vt.addElement("Type");
        	    	 vt.addElement("checkout date");
        	    	 vt.addElement("due date"); 
        	    	 vt.addElement("fee due ($)");
        	    	 reportModel = new DefaultTableModel(connect.feeReport(),vt);
        	    	 reportTable.setModel(reportModel);
        		 }
        		 else 
            		 if(reportType.getSelectedIndex() == 3)
            		 {
            	    	 vt.addElement("MemberID");
            			 vt.addElement("ItemID");
            	    	 vt.addElement("Title");
            	    	 vt.addElement("Type");
            	    	 vt.addElement("checkout date");
            	    	 vt.addElement("due date"); 
            	    	 vt.addElement("fee paid ($)");
            	    	 reportModel = new DefaultTableModel(connect.feeReport(),vt);
            	    	 reportTable.setModel(reportModel);
            		 }
     }
     
   }
    
   public void valueChanged(ListSelectionEvent e)
   {
	   if(e.getSource() == memberList)
	   {
		   String[] tempArray = String.valueOf(memberList.getSelectedValue()).split(" ");
      
    	  int id = Integer.parseInt(tempArray[0]);
    	  Vector<String> memberVector = connect.getSelectedMember(id);
          memberIDtxt.setText(memberVector.get(0));
          fnameTxt.setText(memberVector.get(1));
          lnameTxt.setText(memberVector.get(2));
          phoneTxt.setText(memberVector.get(3));
          emailTxt.setText(memberVector.get(4));
	   }
	   
	   if(e.getSource() == table.getSelectionModel())
	   {
		   System.out.println("table selection event");
		   int row  = table.getSelectedRow();
		   int id = Integer.parseInt(String.valueOf(table.getValueAt(row, 0)));
		   
		  Vector<String> temp = connect.getSelectedItem(id);
		   		   
		    isbntxt.setText(temp.get(0));
   	 		titleTxt.setText(temp.get(1));
   	 		authorTxt.setText(temp.get(2));
   	 		
   	 		yearCombo.setSelectedItem(temp.get(3));
	 		monthCombo.setSelectedItem(temp.get(4));
	 		
   	 		editionTxt.setText(temp.get(5));
   	 		qtyText.setText(temp.get(6));
   	 		typeCombo.setSelectedItem(temp.get(7));
   	 		descriptionTxt.setText(temp.get(8)); 	 		
   	 		locationTxt.setText(temp.get(9));		   
	   }          
   }  
   public void itemStateChanged(ItemEvent e)
   {
	   newFilter();
   }
   private void newFilter() {
	    RowFilter<DefaultTableModel, Object> rf = null;
	    List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(
	            2);
	        if (chckbxBooks.isSelected()) {
	            filters.add(RowFilter.regexFilter("Book"));
	        }
	        if (chckbxCdsdvds.isSelected()) {
	            filters.add(RowFilter.regexFilter("DVD or CD"));
	        }
	        if (chckbxMagazinesnewspapers.isSelected()) {
	            filters.add(RowFilter.regexFilter("Magazine"));
	            filters.add(RowFilter.regexFilter("Newspaper"));
	        }
	        if (chckbxArticles.isSelected()) {
	            filters.add(RowFilter.regexFilter("Article"));
	        }

	    try {
	        rf = RowFilter.orFilter(filters);
	    } catch (java.util.regex.PatternSyntaxException e) {
	        return;
	    }
	    sorter.setRowFilter(rf);
	}
}


