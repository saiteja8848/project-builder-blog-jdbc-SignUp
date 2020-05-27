package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;
import utility.ConnectionManager;

public class UserDAO implements UserDaoInterface {


	public int signUp(User user) throws SQLException {
		if (ConnectionManager.getConnection() != null) {

			System.out.println(user.getEmail()+" "+user.getPassword());
			Statement stmt = ConnectionManager.getConnection().createStatement();
			String sql = "INSERT INTO SIGNUP VALUES("+"'"+user.getEmail()+"'"+","+"'"+user.getPassword()+"'"+","+"DATE"+" "+"'"+user.getDate()+"'"+")";
			return stmt.executeUpdate(sql);
		} else
			return 0;
	}

	public boolean loginUser(User user) throws SQLException {
		boolean status = false;

		
		if (ConnectionManager.getConnection() != null) {
			Statement stmt = ConnectionManager.getConnection().createStatement();
			String sql = "SELECT * FROM SIGNUP";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())
			{
				if (rs.getString(1).contentEquals(user.getEmail())) {
					status = true;
					break;
				}
			}

		}
		return status;
	}

}