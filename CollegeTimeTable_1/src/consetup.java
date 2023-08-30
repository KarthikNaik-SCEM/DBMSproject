/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import java.sql.DriverManager;
/**
 *
 * @author USER
 */
public class consetup {
    static Connection con;
    
    public static Connection createCon() {
	
	 
	 try {
                Class.forName("com.mysql.cj.jdbc.Driver");
	 
		 
//		 create connection
		 
                String user = "root";
                String password ="Frenzy12345!";
                String url = "jdbc:mysql://localhost:3306/timetablecollege";
	     
                con = DriverManager.getConnection(url,user,password);
                System.out.println("connected");
	 
 }
 catch(Exception e) {
     System.out.println("not connected");
	 e.printStackTrace();
 }
	
	 return con;
	
}  
    
}
