package com.FJerseyProject.webapp;

import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;


public class UserRepository {
		Connection con = null;
	public UserRepository(){
		String url = "jdbc:mysql://localhost:3306/restapi";
		String username = "root";
		String password = "sameer123";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<User> getUsers(){
		List users = new ArrayList<User>();
		String sql = "select * from user";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				User u =new User();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setAge(rs.getInt(3));
				users.add(u);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	public User getUserById(int id) {
		String sql = "select * from user where id="+id;
		User u =new User();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setAge(rs.getInt(3));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
	public void createUser(User u) {
		String sql = "insert into user values (?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1,u.getId());
			st.setString(2, u.getName());
			st.setInt(3, u.getAge());
			st.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateUser(User u) {
		String sql = "update user set name=? , age=? where id=?";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(3,u.getId());
			st.setString(1, u.getName());
			st.setInt(2, u.getAge());
			st.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteUser(int id) {
	String sql = "delete from user where id = ?";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1,id);
			st.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
