import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ConnectDB extends MainScreen
{
	public static final int MYSQL_DUPLICATE_PK = 1062;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://nikhilb.cloudapp.net/se11";
    static final String USER = "seusr11";
    static final String PASS = "pwiT4X";   
    static Connection conn = null;
    static Statement stmt = null;
    static String type;
    

    static void connect(String sql) {
    	   
    	   try{
    	      
    		   Class.forName("com.mysql.jdbc.Driver");
    		   System.out.println("Connecting to database...");
    		   conn = DriverManager.getConnection(DB_URL,USER,PASS);	 
    	      
    	      //STEP 4: Execute a query
    	      
    	      stmt = conn.createStatement();      
    	      
    	      stmt.executeUpdate(sql);

    	      stmt.close();
    	      
    	      conn.close();
    	        	        	    
    	   }catch(SQLException se){
	   if (se.getErrorCode() == MYSQL_DUPLICATE_PK) { 
	        // Duplicate entry
		   System.out.println("cannot use as id");
		   MainScreen window = new MainScreen();
		   window.resetID();
	    } else {
	        // Other SQL Exception
	        se.printStackTrace();
	    }  
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
   System.out.println("Goodbye!");
}
  
  public static Vector<String> connectToQuery(String sql, String type) {	
	   Vector<String> queryVector = new Vector();	     
	   try{
	      Class.forName("com.mysql.jdbc.Driver");
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);	      
	      stmt = conn.createStatement();	      
	        ResultSet rs = stmt.executeQuery(sql);
	        if(type.equals("getMember"))
	        {
		       while(rs.next())
		        { 
		         int id  = rs.getInt("memberID");
		         String fName = " " +rs.getString("fName");
		         String lName = " "+rs.getString("lName");
		         //String phone = rs.getString("phone");
		         //String email = rs.getString("email");
		         //String membertype = rs.getString("type");
		         queryVector.add(id+fName+lName);		         
		        }	  
	        }
	        else if(type.equals("getSelectedMember"))
	        {
		       while(rs.next())
		        { //(memberID,fName,lName,phone,email,active,type)
		         int id  = rs.getInt("memberID");
		         String fName =  rs.getString("fName");
		         String lName = rs.getString("lName");
		         String phone = rs.getString("phone");
		         String email = rs.getString("email");
		         String membertype = rs.getString("type");
		         queryVector.add(String.valueOf(id));
		         queryVector.add(fName);
		         queryVector.add(lName);
		         queryVector.add(phone);
		         queryVector.add(email);
		         queryVector.add(membertype);
		        }	  
	        }
	        else if(type.equals("getSearchMember"))
	        {
		       while(rs.next())
		        { //(memberID,fName,lName,phone,email,active,type)
		         int id  = rs.getInt("memberID");
		         String fName =  rs.getString("fName");
		         String lName = rs.getString("lName");
		         queryVector.add(id+" " + fName+" "+lName);		         

		        }	  
	        }
            else if(type.equals("login"))
	        {
		       while(rs.next())
		        { 
		         int id  = rs.getInt("memberAID");		      
		         String password = rs.getString("password");
		         queryVector.add(String.valueOf(id) + ":" + password);        
		        }	  
	        }
            
	        if(type.equals("getSelectedItem"))
	        {
		       while(rs.next())
		        { 
		         int id  = rs.getInt("itemID");
		         String title = " " +rs.getString("title");
		         String author = " "+rs.getString("author");
		         String year = " "+rs.getString("year");
		         String month = " "+rs.getString("month");
		         String edition = rs.getString("edition");		         
		         int qtyTotal = rs.getInt("qtyTotal");
		         String itemType = rs.getString("type");
		         String desc = rs.getString("itemDesc");
		         String location = rs.getString("location");
		         queryVector = new Vector<String>();
		         
		         queryVector.addElement(String.valueOf(id));	
		         queryVector.addElement(title);	
		         queryVector.addElement(author);
		         queryVector.addElement(year);
		         queryVector.addElement(month);
		         queryVector.addElement(edition);	
		         queryVector.addElement(String.valueOf(qtyTotal));
		         queryVector.addElement(itemType);	
		         queryVector.addElement(desc);	
		         queryVector.addElement(location);
		         		         

		        }			       
	        }
	        if(type.equals("getItem"))
	        {
	        	 while(rs.next())
			        { 
			         int id  = rs.getInt("itemID");
			         String title = " " +rs.getString("title");
			         String author = " "+rs.getString("author");
			         String edition = rs.getString("edition");	
			         String itemType = rs.getString("type");
			         queryVector = new Vector<String>();
			         
			         queryVector.addElement(String.valueOf(id));	
			         queryVector.addElement(title);	
			         queryVector.addElement(author);
			         queryVector.addElement(edition);	
			         queryVector.addElement(itemType);	
			         		         
			        }	
	        }
	         if(type.equals("getQtyAvail"))
            {
            	while(rs.next())
		        { 
		         int qty  = rs.getInt("qtyAvailable");		
		         queryVector.add(String.valueOf(qty));        
		        }	  
            }
	         if(type.equals("checkedOut"))
	         {
	        	 while(rs.next())
	        	 {
	        		 int checkedout = rs.getInt("CheckedOut");
	        		 queryVector.add(String.valueOf(checkedout));
	        	 }
	         }
	         if(type.equals("getBorrowed"))
	         {
	        	 while(rs.next())
	        	 {
	        		 int itemID = rs.getInt("checkout.itemId");
	        		 String title = rs.getString("title");
	        		 String author = rs.getString("author");
	        		 String dueDate = String.valueOf(rs.getDate("dueDate"));
	        		 queryVector.add("ID: "+String.valueOf(itemID) + "  Title: "+ title+ "  Author: "+ author+ "  Due "+ dueDate );
	        		 
	        	 }
	         }
	         if(type.equals("getFees"))
	         {
	        	 while(rs.next())
	        	 {
	        		 int itemID = rs.getInt("checkout.itemId");
	        		 String title = rs.getString("title");
	        		 String author = rs.getString("author");
	        		 double fee = rs.getDouble("feeDue");
	        		 queryVector.add("Item: "+String.valueOf(itemID) + ", "+ title+ "  ,"+ author+  "        $"+ fee );
	        		 
	        	 }
	         }
	         if(type.equals("getTotalFees"))
	         {
	        	 while(rs.next())
	        	 {
	        		
	        		 double fee = rs.getDouble("TotalFees");
	        		 queryVector.add(String.valueOf(fee));
	        		 
	        	 }
	         }

           
	      stmt.close();	      
	      conn.close();
	   }catch(SQLException se){
		   if (se.getErrorCode() == MYSQL_DUPLICATE_PK) { 
		        // Duplicate entry
			   System.out.println("cannot use as id");
	     MainScreen window = new MainScreen();
			   window.resetID();
		    } else {
		        // Other SQL Exception
		        se.printStackTrace();
		    }
	    
	   }catch(Exception e){
	      e.printStackTrace();
	   }finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
	   System.out.println("Goodbye!");
	   return queryVector;
	}
  public static Vector<Vector> connectItems(String sql, String type) {	
	   Vector<String> queryVector;
	   Vector<Vector> itemVector = new Vector<Vector>();
	   try{
	      Class.forName("com.mysql.jdbc.Driver");
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);	      
	      stmt = conn.createStatement();	      
	        ResultSet rs = stmt.executeQuery(sql);
	        if(type.equals("getResourceList"))
	        {
		       while(rs.next())
		        { 
		         int id  = rs.getInt("itemID");
		         String title = " " +rs.getString("title");
		         String author = " "+rs.getString("author");
		         String edition = rs.getString("edition");
		         String itemType = rs.getString("type");
		         int qtyTotal = rs.getInt("qtyTotal");
		         queryVector = new Vector<String>();
		         
		         queryVector.addElement(String.valueOf(id));	
		         queryVector.addElement(title);	
		         queryVector.addElement(author);
		         queryVector.addElement(edition);	
		         queryVector.addElement(itemType);	
		         queryVector.addElement(String.valueOf(qtyTotal));	
		         		         
		         itemVector.add(queryVector);

		        }			       
	        }     
	        if(type.equals("getFullResourceList"))
	        {
		       while(rs.next())
		        { 
		         int id  = rs.getInt("itemID");
		         String title = " " +rs.getString("title");
		         String author = " "+rs.getString("author");
		         String edition = " "+rs.getString("edition");
		         String itemType = " "+rs.getString("type");
		         int year = rs.getInt("year");
		         int month = rs.getInt("month");
		         int qtyTotal = rs.getInt("qtyTotal");
		         int qtyAvailable = rs.getInt("qtyAvailable");
		         String location = rs.getString("location");
		         queryVector = new Vector<String>();
		         
		         queryVector.addElement(String.valueOf(id));	
		         queryVector.addElement(title);	
		         queryVector.addElement(author);
		         queryVector.addElement(edition);	
		         queryVector.addElement(itemType);	
		         queryVector.addElement(String.valueOf(year));
		         queryVector.addElement(String.valueOf(month));
		         queryVector.addElement(String.valueOf(qtyTotal));	
		         queryVector.addElement(String.valueOf(qtyAvailable));
		         queryVector.addElement(location);
		         		         
		         itemVector.add(queryVector);

		        }			       
	        }  
	        if(type.equals("getSearchItems"))
	        {
		       while(rs.next())
		        { 
		         int id  = rs.getInt("itemID");
		         String title = " " +rs.getString("title");
		         String author = " "+rs.getString("author");
		         String edition = " "+rs.getString("edition");
		         String itemType = " "+rs.getString("type");
		         int year = rs.getInt("year");
		         int month = rs.getInt("month");
		         int qtyTotal = rs.getInt("qtyTotal");
		         int qtyAvailable = rs.getInt("qtyAvailable");
		         String location = rs.getString("location");
		         queryVector = new Vector<String>();
		         
		         queryVector.addElement(String.valueOf(id));	
		         queryVector.addElement(title);	
		         queryVector.addElement(author);
		         queryVector.addElement(edition);	
		         queryVector.addElement(itemType);	
		         queryVector.addElement(String.valueOf(year));
		         queryVector.addElement(String.valueOf(month));
		         queryVector.addElement(String.valueOf(qtyTotal));	
		         queryVector.addElement(String.valueOf(qtyAvailable));
		         queryVector.addElement(location);
		         		         
		         itemVector.add(queryVector);

		        }			       
	        }
	        if(type.equals("searchItems"))
	        {
	        	while(rs.next())
		        { 
		         int id  = rs.getInt("itemID");
		         String title = " " +rs.getString("title");
		         String author = " "+rs.getString("author");
		         String edition = " "+rs.getString("edition");
		         String itemType = " "+rs.getString("type");
		         int qtyTotal = rs.getInt("qtyTotal");
		         queryVector = new Vector<String>();
		         
		         queryVector.addElement(String.valueOf(id));	
		         queryVector.addElement(title);	
		         queryVector.addElement(author);
		         queryVector.addElement(edition);	
		         queryVector.addElement(itemType);	
		         queryVector.addElement(String.valueOf(qtyTotal));	
		         		         
		         itemVector.add(queryVector);

		        }			       
	   		}
	        if(type.equals("checkoutReport"))
	        {
			       while(rs.next())
			        { 
			         int id  = rs.getInt("itemID");
			         String title = " " +rs.getString("title");
			         String itemType = " "+rs.getString("type");
			         int memberid = rs.getInt("memberBID");
			         String checkoutdate = rs.getString("checkOutDate");
			         String duedate = rs.getString("dueDate");

			         
			         queryVector = new Vector<String>();
			         
			         queryVector.addElement(String.valueOf(id));	
			         queryVector.addElement(title);		
			         queryVector.addElement(itemType);	
			         queryVector.addElement(String.valueOf(memberid));
			         queryVector.addElement(checkoutdate);
			         queryVector.addElement(duedate);	
			         		         
			         itemVector.add(queryVector);
			    	  

			        }	
	        }
	        if(type.equals("overdueReport"))
	        {
	        	while(rs.next())
		        { 
		         int id  = rs.getInt("itemID");
		         String title = " " +rs.getString("title");
		         String itemType = " "+rs.getString("type");
		         int memberid = rs.getInt("memberBID");
		         String checkoutdate = rs.getString("checkOutDate");
		         String duedate = rs.getString("dueDate");

		         
		         queryVector = new Vector<String>();
		         
		         queryVector.addElement(String.valueOf(id));	
		         queryVector.addElement(title);		
		         queryVector.addElement(itemType);	
		         queryVector.addElement(String.valueOf(memberid));
		         queryVector.addElement(checkoutdate);
		         queryVector.addElement(duedate);	
		         		         
		         itemVector.add(queryVector);
		    	  

		        }	
	        }
	        if(type.equals("feeReport"))
	        {
	        	while(rs.next())
		        { 
		         int id  = rs.getInt("itemID");
		         String title = " " +rs.getString("title");
		         String itemType = " "+rs.getString("type");
		         int memberid = rs.getInt("memberBID");
		         String checkoutdate = rs.getString("checkOutDate");
		         String duedate = rs.getString("dueDate");
		         String fee = rs.getString("feeDue");
		         
		         queryVector = new Vector<String>();
		         
		         queryVector.addElement(String.valueOf(memberid));
		         queryVector.addElement(String.valueOf(id));	
		         queryVector.addElement(title);		
		         queryVector.addElement(itemType);	
		         queryVector.addElement(checkoutdate);
		         queryVector.addElement(duedate);
		         queryVector.addElement(fee);
		         		         
		         itemVector.add(queryVector);
		    	  

		        }	
	        }
	              
	      stmt.close();	      
	      conn.close();
	   }catch(SQLException se){
		   if (se.getErrorCode() == MYSQL_DUPLICATE_PK) { 
		        // Duplicate entry
			   System.out.println("cannot use as id");
	     MainScreen window = new MainScreen();
			   window.resetID();
		    } else {
		        // Other SQL Exception
		        se.printStackTrace();
		    }
	    
	   }catch(Exception e){
	      e.printStackTrace();
	   }finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
	   System.out.println("Goodbye!");
	   return itemVector;
	}

  
///////////////////////////////////////MEMBER METHODS///////////////////////////////////////////////////////////////////////////////  
  
   public static void AddMember(String memberID,String password,String fName, String lName, String phone, String email,
   String active, String type) //ADD MEMBER TO DATABASE THROUGH MEMBER TAB
   {  	   
	   String sql;
	      sql = "INSERT INTO member (memberID,fName,lName,phone,email,active,type)"+
	       " VALUES ("+memberID+",'"+fName+"','"+lName+"','"+phone+"','"+email+"','"+active+"','"+type+"');";
	      connect(sql);
         sql = "INSERT INTO login (memberAID,password)" + "VALUES ("+memberID+", '"+password+"');"; 
         connect(sql);	   	   
     }
   
   public static boolean updateMember(String memberID,String fName, String lName, String phone, String email,
		   String active, String type, int id, String pass)
   {
	   String sql;
	   sql = "UPDATE member SET memberID = '" + memberID +"', fName = '" + fName + "', lName = '"+
	   lName+"', phone = '"+phone+"', email = '"+email+"', active = '"+active+"', type = '"+type+"' WHERE memberID="+id+";";
	   connect(sql);
      if(!pass.equals(""))
      {
      sql = "UPDATE login SET password = '" + pass +"' WHERE memberAID = '"+memberID+"';";
      connect(sql);
      }
	   return true;
   }
     
     public static Vector<String> getMember() //SHOW MEMBER IN LIST ON MEMBER TAB
     { 
       String sql;
       sql = "SELECT memberID, fName, lName, phone, email, active, type from se11.member;";            
       return connectToQuery(sql, "getMember");	
     }
     
     public static Vector<String> getSelectedMember(int id) //GET SELECTED MEMBER AND SHOW THEIR INFO IN EDIT PANEL ON MEMBER TAB
     {    	 
         String sql;
         sql = "SELECT * FROM se11.member WHERE memberID = " + id; 
         return connectToQuery(sql, "getSelectedMember");
     }
     
     public static Vector<String> getSearchMember(String search) //GET SELECTED MEMBER AND SHOW THEIR INFO IN EDIT PANEL ON MEMBER TAB
     {    	 
         String sql;
         sql = "SELECT memberID, fName, lName FROM se11.member WHERE memberID like '%" + search + "%' or fName like '%" + search + "%' or lName like '%" + search + "%';"; 
         return connectToQuery(sql, "getSearchMember");
     }
     
     public static Vector<String> getUserIdAndPassword() //GET SELECTED MEMBER AND SHOW THEIR INFO IN EDIT PANEL ON MEMBER TAB
     {    	 
         String sql;
         sql = "SELECT * FROM se11.login;"; 
         return connectToQuery(sql, "login");
     }   
     
     public static void removeMember(int id)
     {
         String sql;
         sql = "DELETE FROM login WHERE memberAID = " + id + ";";
         connect(sql);
         
         
         sql = "DELETE FROM member WHERE memberID = " + id+";";
         connect(sql);
         
     
     }
///////////////////////////////////////////////////ITEM METHODS///////////////////////////////////////////////////////////////////////////////     
     public static void addResource(int id, String title, String author, int year, int month, String edition, int qty, String type, String desc, String location) //ADD MEMBER TO DATABASE THROUGH MEMBER TAB
    		   {  	   
    			   String sql;
    			      sql = "INSERT INTO item (itemID, title, author, year, edition, type, qtyTotal, qtyAvailable, itemDesc, location, month)"+
    			       " VALUES ("+id+",'"+title+"','"+author+"','"+year+"','"+edition+"','"+type+"','"+qty+"','"+qty+"','"+desc+"','"+location+"','"+month+"');";
    			      connect(sql);
    			      
    		           	   
    		     }
     public static Vector<Vector> getResourceList() 
     { 
       String sql;
       sql = "SELECT itemID, title, author, year, edition, type, qtyTotal from se11.item;";            
       return connectItems(sql, "getResourceList");	
     }
     public static Vector<Vector> getFullResourceList() 
     { 
       String sql;
       sql = "SELECT itemID, title, author, year, edition, type, month, qtyTotal, qtyAvailable, location from se11.item;";            
       return connectItems(sql, "getFullResourceList");	
     }
     
     
     public static Vector<String> getSelectedItem(int id)
     {
    	 String sql;
         sql = "SELECT itemID, title, author, year, month, edition, qtyTotal, type, itemDesc, location from se11.item WHERE itemID= "+id+";";            
         return connectToQuery(sql, "getSelectedItem");	
     }
     
     public static boolean updateResource(int itemID,String title, String author, int year,int month,String edition , int qtyTotal,String type, String desc, String location)
     {
  	   String sql;
  	   sql = "UPDATE item SET itemID = '" + itemID +"', title = '" + title + "', author = '"+
  	   author+"', year = '"+year+"', edition = '"+edition+"', type = '"+type+"', itemDesc = '"+desc+"', month = '"+month+"', location = '"+location+"', qtyTotal = '"+qtyTotal+"' WHERE itemID= "+itemID+";";
  	   connect(sql);      
  	    return true;
     }
	 
     public static void removeResource(int id)
     {
         String sql;
         sql = "DELETE FROM item WHERE itemID = " + id + ";";
         connect(sql);
     
     }
/////////SEARCH///////////////////////////////     
     public static Vector<Vector> getSearchItems(String searchBy, String search)
     {
    	 String sql;
         sql = "SELECT itemID, title, author, year, edition, type, month, qtyTotal, qtyAvailable, location from se11.item WHERE "+searchBy+" LIKE '%"+search+"%';";            
         return connectItems(sql, "getSearchItems");	
     }
     public static Vector<Vector> searchItems(String searchBy, String search)
     {
    	 String sql;
         sql = "SELECT itemID, title, author, edition, type, qtyTotal from se11.item WHERE "+searchBy+" LIKE '%"+search+"%';";            
         return connectItems(sql, "searchItems");	
     }
//////////////checkout///////////////////////////     
     public static Vector<String> getItem(int id)
     {
    	 String sql;
    	 sql = "SELECT itemID, title, author, edition, type FROM item WHERE itemID = "+id+";";
    	 return connectToQuery(sql, "getItem");
     }     
     
     public static int getItemQtyAvailable(int id)
     {
    	 String sql;
    	 sql = "SELECT qtyAvailable FROM item WHERE itemID = " + id +";";
    	 return Integer.parseInt(connectToQuery(sql, "getQtyAvail").get(0));
    	 
     }
    
     public static boolean checkedOut(int itemId, int memberID)
     {
    	 String sql;
    	 sql = "SELECT count(itemId) as CheckedOut FROM se11.checkout WHERE itemID = "+itemId+" AND memberBID = "+memberID+" and isnull(returned);";
    	
    	 if( Integer.parseInt(connectToQuery(sql, "checkedOut").get(0)) > 0)
    		 return true;
    	 else 
    		 return false;
     }
     
     public static boolean checkout(int itemID, int memberID)
     {
    	 String sql;
    	 sql = "INSERT INTO checkout(checkOutDate, itemId, memberBID, dueDate) VALUES (curdate(), "+itemID+ ", "+memberID+", date_add(curdate(), INTERVAL 14 day));";
    	 connect(sql);
    	 sql = "UPDATE item SET qtyAvailable = qtyTotal - (SELECT count(itemID) from checkout WHERE itemID = "+ itemID+" and isnull(returned)) ;";
    	 connect(sql);
    	 return true;
     }
     
///////////////check Borrowed//////////////////////////////
     public static Vector<String> getBorrowed( int memberID)
     {
    	 String sql;
    	 sql = "SELECT checkout.itemID, title, author, dueDate FROM se11.checkout inner join item on item.itemid = checkout.itemid where memberBID = "+memberID+" and isnull(returned) order by dueDate;";
    	 return connectToQuery(sql, "getBorrowed");
    	 
     }
     
/////////////////check fees///////////////////////////////     
     public static boolean updateFeeDue(int memberID)
     {
    	 String sql;
    	 sql = "UPDATE checkout set feeDue = (curdate() - dueDate + 1) WHERE isnull(returned) AND curdate() > dueDate AND memberBID = "+memberID+" ;";
    	 connect(sql);
    	 return true;
     }
         
     public static Vector<String> getFees(int memberID)
     {
    	 String sql;
    	 sql = "SELECT checkout.itemID, title, author,  feeDue  FROM se11.checkout inner join item on item.itemid = checkout.itemid where isnull(paid) and memberBID = "+memberID+" and CurDate() > dueDate;";
    	 return connectToQuery(sql, "getFees");
     }
     
     public static double getTotalFees(int memberID)
     {
    	 String sql;
    	 sql = "SELECT sum(feeDue) as TotalFees FROM se11.checkout inner join item on item.itemid = checkout.itemid where isnull(paid) and memberBID = "+memberID+" and CurDate() > dueDate ;";
    	 return Double.parseDouble(connectToQuery(sql, "getTotalFees").get(0));
     }
     
     public static boolean updatePaid(int memberID)
     {
    	 String sql;
    	 sql = "UPDATE checkout set paid = 'true' WHERE curdate() > dueDate AND memberBID = "+memberID+";";
    	 connect(sql);
    	 return true;
     }
/////////////CHECKIN//////////////////////////////////////////
     public static boolean checkIn(int itemID, int memberID)
     {
    	 String sql;
    	 sql = "INSERT INTO checkin (returnDate, CheckoutID) VALUES (curdate(), (SELECT checkoutid from checkout WHERE checkout.memberBID = "+memberID+" and checkout.itemID = "+itemID+" and isnull(returned)))";
    	 connect(sql);
    	 
    	 sql = "UPDATE checkout SET returned = 'true' WHERE checkout.memberBID = "+memberID+" and checkout.itemID = "+itemID+" and isnull(returned) ;";
         connect(sql);
    	 
    	 sql = "UPDATE item SET qtyAvailable = qtyTotal - (SELECT count(itemID) from checkout WHERE itemID = "+ itemID+" and isnull(returned)) ;";
    	 connect(sql);
    	 return true;
     }
 /////////////////REPORTS////////////////////////////////////////
     public static Vector<Vector> checkoutReport()
     {
    	 String sql;
  
    	 sql = "Select checkout.itemID,item.title,item.`type`,memberBID,checkOutDate, dueDate from checkout inner join item on item.itemID = checkout.itemID where isnull(returned);";
    	return connectItems(sql,"checkoutReport");
    	
     }
     
     public static Vector<Vector> overdueReport()
     {
    	 String sql;
    	 sql = "Select checkout.itemID,item.title,item.`type`,memberBID, checkOutDate, dueDate from checkout inner join item on item.itemID = checkout.itemID where dueDate < curdate() and isnull(returned);";
    	 return connectItems(sql,"overdueReport");
     }
     
     public static Vector<Vector> feeReport()
     {
    	 String sql;
    	 sql = "Select memberBID, checkout.itemID,item.title,item.`type`, checkOutDate, dueDate, feeDue from checkout inner join item on item.itemID = checkout.itemID where !isnull(feedue);";
    	 return connectItems(sql,"feeReport");
     }
     public static Vector<Vector> paidReport()
     {
    	 String sql;
    	 sql = "Select memberBID, checkout.itemID,item.title,item.`type`, checkOutDate, dueDate, feeDue from checkout inner join item on item.itemID = checkout.itemID where !isnull(feedue) and  paid = 'true';";
    	 return connectItems(sql,"feeReport");
     }
     
}

