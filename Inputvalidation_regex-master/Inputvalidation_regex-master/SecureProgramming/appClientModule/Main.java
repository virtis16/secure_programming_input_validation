import java. io.*;
import java.util.*;
import java.lang.*;
import java.util.regex.*;
import java.sql.*;

public class Main {
	
	public static void main(String args[])throws  Exception
	{
		
		    String method_name=args[0];
		    Connection connection=getConnection();
		    DB_Methods dbm=new DB_Methods();

			if (connection != null) {
				if(method_name.equalsIgnoreCase("add"))
		        {
		              	
					int status_name= checkname(args[1]);
		        	int status_phonenumber=checkno(args[2]);
		        	if (status_name==0 && status_phonenumber==0)
		        	{
		        		System.out.println(args[1]+" Is a valid Name "+args[2]+" Is a valid number ");
		        		int insert_status=dbm.Add(connection,args[1], args[2]);
		        		if(insert_status>0)
		        		{
		        			System.out.println("Insert to the DB is success with return_status value: "+ insert_status);
		        		}
		        		System.err.println("Exit 0");
		        		System.exit(0);
		        		
		        	}
		        	else {
		        	
		        		if(status_name!=0)
		        			System.out.println("It is not a valid name");
		        		else if(status_phonenumber!=0)
		        			System.out.println("It is not a valid number");
		        		System.err.println("Exit 1");
		        		System.exit(1);
		        	}
		        }
		        else if(method_name.equalsIgnoreCase("list")){
		        	
		        	dbm.List(connection);
		        	//list
		        }
		        else if(method_name.equalsIgnoreCase("delete")){
		        	//delete
		        	int status_name= checkname(args[1]);
		        	int status_phonenumber=checkno(args[1]);
		        	if (status_name==0) {
		        		dbm.Delete_PhoneName(connection, args[1]);
		        		System.exit(0);
		        	}
		        	else if(status_phonenumber==0)
		        	{
		        		dbm.Delete_PhoneNumber(connection, args[1]);
		        		System.exit(0);
		        	}
		        	else {
		        		System.err.println("Exit 1");
		        		System.exit(1);
		        	}
		        }
			} 
			else {
				System.out.println("Failed to make connection!");
			}              
    
    }
public static Connection getConnection() throws Exception{
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("Where is your MySQL JDBC Driver?");
		e.printStackTrace();
		return null;
	}

	System.out.println("MySQL JDBC Driver Registered!");
	Connection connection = null;

	try {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");

	} catch (SQLException e) {
		System.out.println("Connection Failed! Check output console");
		e.printStackTrace();
		return null;
	}
	return connection;
}
	
public static int checkname(String s){
        
		String input = s;
		//String n1="^(([a-zA-Z]['])?[a-zA-Z]+[/,]?\\s)?$"; 
		//String n2="^(([a-zA-Z]['])?[a-zA-Z]+[/-]?[a-zA-Z]*\\s)?$";  	//[/-]?[a-zA-Z]*
		//String n1="^(([a-zA-Z]['])[a-zA-Z]+[-]?[a-zA-Z]*\\s)$";
		String n="^((([a-zA-Z]['])?[a-zA-Z]+[/,]?\\s*)?(([a-zA-Z]['])?[a-zA-Z]+[/-]?[a-zA-Z]*\\s*)?[a-zA-Z]*\\.?)$";
		Pattern p = Pattern.compile(n);
		Matcher m = p.matcher(input);
		
		if (m.matches())
		{
		    return 0;
		}
		else
		{
		    return 1;
		}
}
public static int checkno(String s){
    
	String input = s;
	String n="^((((\\+?\\d{1,3})?\\s{0,1}\\(?[0-9]{0,3}\\)?)\\s{0,1}\\d{3}(\\s{1}|\\-)\\d{4})|(\\d{5}|\\d{5}[.]\\d{5}))$";
	//String n ="^(\\+?\\d{1,3})?\\s{0,1}\\(?[0-9]{0,3}\\)?$";				//"^((\\+?\\d{1,3})?\\s{0,1}\\(?[0-9]{0,3}\\)?\\s\\d{3}\\s{1}\\d{4})$";					//"^\\s*(?:\\+?(\\d{1,3}))?[-. (](\\d{3})[-. )](\\d{3})[-. ](\\d{4})(?: *x(\\d+))?\\s$";
	Pattern p = Pattern.compile(n);
	Matcher m = p.matcher(input);
	
	if (m.matches())
	{
	    return 0;
	}
	else
	{
	    return 1;
	}
}

}