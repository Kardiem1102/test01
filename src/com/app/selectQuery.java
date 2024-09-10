package com.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class selectQuery {
	public Connection conn;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;
	
	public void insertStudent(String[] info) {
		conn = DBConnect.getConnection();
		
		String query = "SELECT student_id, student_name, department_name"
				+",address_postal_code,address_line1 ||' '|| address_line2 AS address_detail "
				+"FROM (tbl_student JOIN tbl_department USING(department_id)) "
				+"JOIN tbl_address USING (address_id)";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				
			}
			
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
	
}
