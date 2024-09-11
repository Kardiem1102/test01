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
	

	// - 학생 번호, 이름, 전공, 우편번호, 주소1, 주소2를 조회하기
	// 주소1, 2는 ||로 합쳐서 address_detail이라는 alias로 변경
	public String[][] selectAllStudentInfo() {
		conn = DBConnect.getConnection();
		
		String query = "SELECT student_id, student_name, department_name"
				+",address_postal_code,address_line1 ||' '|| address_line2 AS address_detail "
				+"FROM (tbl_student JOIN tbl_department USING(department_id)) "
				+"JOIN tbl_address USING (address_id)";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			
			resultSet.last();
			int row = resultSet.getRow();
			resultSet.beforeFirst();

			String[][] result = new String[row][4];
			if(resultSet.next()) {
				for(int i = 0; i < row; i++){
					result[i][0] = Integer.toString(resultSet.getInt(1));
					result[i][1] = resultSet.getString(2);
					result[i][2] = resultSet.getString(3);
					result[i][3] = resultSet.getString(4);
				}
			}
			
		}catch(SQLException e){
			System.out.println("학생정보 불러오기 오류");
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
		return result;
	}
	
	//- student 테이블의 나이가 22살인 학생들의 학과 정보 조회하기 (매개변수에 22 넣으면 됨)
	public String[][] selectDepartment_ByAge(int Age) {
		conn = DBConnect.getConnection();
		
		String query = "SELECT student_name, student_age, department_name"
				+"FROM (tbl_student JOIN tbl_department USING(department_id)) "
				+"WHERE student_age = ?"
		
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1,Age);
			
			resultSet = preparedStatement.executeQuery();
			
			resultSet.last();
			int row = resultSet.getRow();
			resultSet.beforeFirst();

			String[][] result = new String[row][3];
			if(resultSet.next()) {
				for(int i = 0; i < row; i++){
					result[i][0] = Integer.toString(resultSet.getInt(1));
					result[i][1] = resultSet.getString(2);
					result[i][2] = resultSet.getString(3);
				}
			}
			
		}catch(SQLException e){
			System.out.println("특정 나이 학생의 학과정보 불러오기 오류");
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
		return result;
	}

	//- 서울시 강남구에 사는 학생의 이름과 학과정보 조회하기(이름을 넣어야겠다)
	public String[][] selectNameDepartment_ByAdd1(String address) {
		conn = DBConnect.getConnection();
		
		String query = "SELECT student_name, department_name, address_line1"
				+"FROM (tbl_student JOIN tbl_department USING(department_id)) "
				+"JOIN tbl_address USING (address_id) "
				+"WHERE address_line1 LIKE('%?%') AND address_line1 LIKE('%?%')"
		
		try {
			preparedStatement = conn.prepareStatement(query);

			String[] ad_split = address.split(" ");
			preparedStatement.setString(1,ad_split[0]);
			preparedStatement.setString(2,ad_split[1]);

			resultSet = preparedStatement.executeQuery();
			
			resultSet.last();
			int row = resultSet.getRow();
			resultSet.beforeFirst();

			String[][] result = new String[row][3];
			if(resultSet.next()) {
				for(int i = 0; i < row; i++){
					result[i][0] = Integer.toString(resultSet.getInt(1));
					result[i][1] = resultSet.getString(2);
					result[i][2] = resultSet.getString(3);
				}
			}
			
		}catch(SQLException e){
			System.out.println("특정 시도에 거주하는 학생 학과 불러오기 오류");
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
		return result;
	}

	//- 맹구의 주소를 tbl_address테이블의 3번으로 수정하기
	// update긴 한데 일단은 여기다 넣자
	public String updateAdress_ByName(String name, int address_id) {
		conn = DBConnect.getConnection();
		
		String query = "UPDATE tbl_student SET address_id = ? "
				+"WHERE student_name = ?"
		
		try {
			preparedStatement = conn.prepareStatement(query);

			preparedStatement.setInt(1,address_id);
			preparedStatement.setString(2,name);

			int result = preparedStatement.executeUpdate();
			
		}catch(SQLException e){
			System.out.println("학생의 주소코드 바꾸기 실패");
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
		return (Integer.toString(result) + "건이 변경되었습니다");
	}
}
