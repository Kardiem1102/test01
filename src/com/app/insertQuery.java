package com.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class insertQuery {
	public Connection conn;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;
	
	public void insertStudent(String[] info) {
		conn = DBConnect.getConnection();
		
		String query = "INSERT INTO tbl_student(student_id"
				+",student_name,student_age,department_id"
				+",address_id)"
				+"VALUES(?,?,?,?,?)";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			
			for(int i = 0; i < 5; i++) {
				String temp = info[i];
				if(temp.charAt(0) < 48 || temp.charAt(0) > 57) {
					preparedStatement.setString(i+1, temp);
				}else {
					preparedStatement.setInt(i+1, Integer.parseInt(temp));
				}
			}
			
			preparedStatement.executeUpdate();
		}catch(SQLException e){
			System.out.println("학생테이블 insert 오류");
			e.printStackTrace();
		}finally{
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
	}
	
	public void insertAddress(String[] info) {
		conn = DBConnect.getConnection();
		
		String query = "INSERT INTO tbl_address(address_id"
				+",address_postal_code,address_line1"
				+",address_line2)"
				+"VALUES(?,?,?,?)";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			
			for(int i = 0; i < 4; i++) {
				String temp = info[i];
				if(temp.charAt(0) < 48 || temp.charAt(0) > 57) {
					preparedStatement.setString(i+1, temp);
				}else {
					preparedStatement.setInt(i+1, Integer.parseInt(temp));
				}
			}
			
			preparedStatement.executeUpdate();
		}catch(SQLException e){
			System.out.println("주소테이블 insert 오류");
			e.printStackTrace();
		}finally{
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
	}
	
	public void insertDepartment(String[] info) {
		conn = DBConnect.getConnection();
		
		String query = "INSERT INTO tbl_department(department_id"
				+",deepartment_name)"
				+"VALUES(?,?)";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			
			for(int i = 0; i < 2; i++) {
				String temp = info[i];
				if(temp.charAt(0) < 48 || temp.charAt(0) > 57) {
					preparedStatement.setString(i+1, temp);
				}else {
					preparedStatement.setInt(i+1, Integer.parseInt(temp));
				}
			}
			
			preparedStatement.executeUpdate();
		}catch(SQLException e){
			System.out.println("부서테이블 insert 오류");
			e.printStackTrace();
		}finally{
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
	}
}
