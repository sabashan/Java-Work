package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DBConnection;
import javafx.beans.binding.BooleanExpression;

public class LoginModal {

	Connection connection;

	public LoginModal() {

		try {

			this.connection = DBConnection.getConnection();

		} catch (SQLException ex) {
 
			ex.printStackTrace();
		}
		
		if(this.connection == null) {
			
			System.exit( 1);
			
		}

	}
	
	
	public boolean isDataBaseConnected() {
		
		return this.connection != null;
		
	}
	
	
	public boolean islogined(String name, String password, String Department) throws Exception{
		
		PreparedStatement pr = null;
		
		ResultSet rs = null;
		
		String sql = "SELECT * FROM login WHERE userId = ? AND password = ? AND Department = ? ";
		
		try {
			
			pr = this.connection.prepareStatement(sql);
			pr.setString(1, name);
			pr.setString(2, password);
			pr.setString(3, Department);
			rs = pr.executeQuery();
			
			
			boolean boll1;
			
			if(rs.next()) {
				
				return true;
			}
			
			return false;
		}catch(SQLException ex) {
			
			return false;
		}
		
		finally {
			{
				pr.close();
				rs.close();
				
			}
		}
		
	}

}
