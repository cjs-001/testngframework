package datas;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
;


public class DBConn {

	public  void data() throws SQLException
	{

		String host ="localhost";
		String port="3306";		
		
	    Connection conn= DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/testngdata","root","2000");
	   Statement s = conn.createStatement(); 
	    ResultSet rs= s.executeQuery("select * from logindata where name = 'rahul';");
	    
	    rs.next();
	    System.out.println(rs.getString(1));
	   System.out.println(rs.getString("name"));
	   System.out.println(rs.getString("password")); 
	}
	
	public static List<String> data1() throws SQLException
	{

		String host ="localhost";
		String port="3306";		
		
	    Connection conn= DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/testngdata","root","2000");
	   Statement s = conn.createStatement(); 
	   //    SELECT * FROM view_name;
	   ResultSet rs1= s.executeQuery("SELECT * FROM view_name;");
	   List<String> list1 = new ArrayList<String>();
	   
	   while(rs1.next()){
           
		   list1.add(rs1.getString("name"));
           
       }
	   
	   return  list1;
	   
	}
	
	// get access to particular column in table and store all the row values 
	
	public  HashMap<String, String> data2(String name) throws SQLException
	{
	   
		
		String host ="localhost";
		String port="3306";		
		
	    Connection conn= DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/testngdata","root","2000");
	   Statement s = conn.createStatement();
	   
	   
	    ResultSet rs= s.executeQuery("select * from logindata where name = "+"'"+name+"';");
	   
	   HashMap<String, String> map = new HashMap<String, String>();
	   
	    while(rs.next()){
            
	    	
	    	map.put("name", rs.getString("name"));
	    	map.put("email", rs.getString("email"));
	    	map.put("password", rs.getString("password"));
	    	map.put("product", rs.getString("product"));
	    	
            
         }
	    
	    System.out.println(map);
	    System.out.println(rs.findColumn("name"));
	    
	    return map;

	}
	
//to access to all the data in table and store it 	
	public static  Object[][] data3() throws SQLException
	{
		

	   
		
		String host ="localhost";
		String port="3306";		
		
	    Connection conn= DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/testngdata","root","2000");
	   Statement s = conn.createStatement();
	   
	   
	   ResultSet rs= s.executeQuery("select * from logindata;");
	   
	   
	    
	   int columncount = rs.getMetaData().getColumnCount();
	   
	   System.out.println(columncount);

	   List<String> names = data1();
	   
	   
	   int rowcount =names.size();
	   

	   
	   
	   System.out.println(rowcount);
	   
	   Object[][] result=new Object[rowcount][columncount];
	   
	   int j=0;
	   while(rs.next())
		{
			
			for(int k=0;k<columncount;k++)
			{
				
				
				result[j][k] =rs.getString(k+1);
			}
			j++;
			
		}
	   return result;
	   
	}
	
	
	
	
	
	
	public static void main(String[] args) throws SQLException, IOException {
	    
	    
             
			
		Object[][] data = data3();
		
		
		System.out.println(data);
	   
	   

	    
	    
	    

	
		
		
	}

}
