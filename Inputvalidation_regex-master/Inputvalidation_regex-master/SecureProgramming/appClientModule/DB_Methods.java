import java.sql.*;
public class DB_Methods {
	//Function to Add a new person to the database 
	public int Add(Connection connection, String input1, String input2) throws SQLException {
		PreparedStatement posted=connection.prepareStatement("INSERT INTO shilpa VALUES('"+input1+"','"+input2+"')");
		int status=posted.executeUpdate();
		return status;
	}
	//Function to produce the list of the members of the database
	public void List(Connection connection) throws SQLException {
		String query = "Select * from shilpa";
		PreparedStatement st = connection.prepareStatement(query);
		ResultSet res = st.executeQuery(query);
		while(res.next())
		{
			String name= res.getString("Phone_Name");

			String number= res.getString("PhoneNumber");
			System.out.format("%s \t,\t  %s\n", name, number);
		}
		
		res.close();
	}
	//Function to delete a record based on Phone number
	public void Delete_PhoneNumber(Connection connection,String phone_num ) throws SQLException {
		PreparedStatement posted=connection.prepareStatement("DELETE FROM shilpa WHERE PhoneNumber=?");
		posted.setString(1, phone_num);
		int rowsDeleted= posted.executeUpdate();
		if(rowsDeleted>0) {
			System.out.println("A user was deleted successfully");
		}
	}
	//Function to delete record from table shilpa based on Name
	public void Delete_PhoneName(Connection connection,String phone_name ) throws SQLException {
		PreparedStatement posted=connection.prepareStatement("DELETE FROM shilpa WHERE Phone_Name=?");
		posted.setString(1, phone_name);
		int rowsDeleted= posted.executeUpdate();
		if(rowsDeleted>0) {
			System.out.println("A user was deleted successfully");
		}
	}
}
