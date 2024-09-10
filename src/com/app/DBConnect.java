package com.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	public static Connection getConnection() {
		Connection conn = null;
		
		String userName = "web5";
		String passwd = "0910";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, userName,passwd);
			System.out.println("연결 성공");
		}catch(ClassNotFoundException e){
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}catch(SQLException e) {
			System.out.println("연결 정보 부정확");
			e.printStackTrace();
		}
		
		return conn;
	}
	public static void main(String[] args) {
		Connection conn = getConnection();
	}
}
