package com.assign.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbManager {
	private static Connection conn =ConnectionManager.getInstance().getConnection();
	
	public static void loadDriver(){
		String driver ="com.mysql.jdbc.Driver";
		
		//System.out.println("Loading driver...");
		try {
			try {
				Class.forName(driver).newInstance();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Driver loaded!");
	}
	
	public static void Insert(User u1) throws SQLException, ClassNotFoundException{
		loadDriver();
		
		String sql = "INSERT INTO User(user_name) VALUES (?)";
		PreparedStatement pstmt =conn.prepareStatement(sql);
		pstmt.setString(1,u1.getName());
		
		pstmt.executeUpdate();
		
	}
	
	public static Question SelectQues(String id) throws SQLException, ClassNotFoundException{
		
		loadDriver();
		
		String sql = "select question_id, q_text, ques_y, ques_n from question where question_id ='" + id + "'";
		Statement stmt =conn.createStatement();
		
		ResultSet rs =stmt.executeQuery(sql);
		
		Question q1 = new Question();
		if (rs.next()){
			q1.setQuestion_id(rs.getInt("question_id"));
	        q1.setQues_txt(rs.getString("q_text"));
	        q1.setLink_ques_yes(rs.getString("ques_y"));
	        q1.setLink_ques_no(rs.getString("ques_n"));
		}
		else{
			System.out.println("No data Found for questions");
		}

		stmt.close();
		return q1;

	}
	
	public static Career getCareer(User user,String id) throws SQLException, ClassNotFoundException{
	
		loadDriver();
		
		int user_id = 0;
		
		String sql1 = "select user_id from user where user_name ='" + user.getName() + "'";
		Statement stmt1 =conn.createStatement();
		
		ResultSet rs1 =stmt1.executeQuery(sql1);
		if (rs1.next()){
			user_id = rs1.getInt("user_id");
		}
		else{
			System.out.println("No data Found for the user's id");
		}
		
		
		String sql = "select career, career_id from career where career_id in (select career_id from question where question_id ='" + id + "')";
		Statement stmt =conn.createStatement();
		
		ResultSet rs =stmt.executeQuery(sql);
		Career c1 = new Career();
		if (rs.next()){
			c1.setCareer(rs.getString("career"));
			c1.setCareer_id(rs.getInt("career_id"));
		}
		else{
			System.out.println("No data Found for user's career");
		}
		
		String sql_update = "update User set ideal_career=? where user_id=?";
		PreparedStatement pstmt =conn.prepareStatement(sql_update);
		pstmt.setInt(1,c1.getCareer_id());
		pstmt.setInt(2,user_id);
		
		pstmt.executeUpdate();

		stmt.close();
		return c1;

	}
	
	public static void insertResponse(User u1, Response r) throws SQLException, ClassNotFoundException{
		
		loadDriver();
		
		int user_id = 0; 
		
		String sql1 = "select user_id from user where user_name ='" + u1.getName() + "'";
		Statement stmt1 =conn.createStatement();
		
		ResultSet rs1 =stmt1.executeQuery(sql1);
		if (rs1.next()){
			user_id = rs1.getInt("user_id");
		}
		else{
			System.out.println("No data Found");
		}
		
		r.setUser_id(user_id);
		
		String sql = "INSERT INTO Response(user_id, question_id, response_txt) VALUES (?,?,?)";
		PreparedStatement pstmt =conn.prepareStatement(sql);
		pstmt.setInt(1,r.getUser_id());
		pstmt.setInt(2,r.getQuestion_id());
		pstmt.setString(3,r.getResponse_txt());
		
		pstmt.executeUpdate();

	}

}
