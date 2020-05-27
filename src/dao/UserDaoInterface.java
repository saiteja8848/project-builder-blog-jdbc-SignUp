package dao;

import java.sql.SQLException;

import model.User;

public interface UserDaoInterface{
	public int signUp(User user) throws SQLException;
	boolean loginUser(User user) throws SQLException;
	
}
