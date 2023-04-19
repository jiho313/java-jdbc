package sample01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import util.ConnUtils;

public class App2 {
	
	public static void main(String[] args) throws Exception {
		
		String sql = "select EMPLOYEE_ID, FIRST_NAME, JOB_ID, SALARY, DEPARTMENT_ID, MANAGER_ID "
				+ "from EMPLOYEES "
				+ "order by EMPLOYEE_ID asc";
		
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		System.out.println("1.직원번호2.이름3.직업아이디4.급여5.부서아이디");
		while (rs.next()) {
		    int no = rs.getInt("EMPLOYEE_ID");
		    String name = rs.getString("FIRST_NAME");
		    String jobId = rs.getString("JOB_ID");
		    int salary = rs.getInt("SALARY");
		    int departmentId = rs.getInt("DEPARTMENT_ID");
//		    Date date = rs.getDate("create_date");
		    System.out.print(no +"\t");
		    System.out.print(name +"\t");
		    System.out.print(jobId +"\t");
		    System.out.print(salary +"\t");
		    System.out.print(departmentId);
//		    System.out.println("직원번호: " + no);
//		    System.out.println("이름: " + name);
//		    System.out.println("직업아이디: " + jobId);
//		    System.out.println("급여: " + salary);
//		    System.out.println("부서아이디: " + departmentId);
//		    System.out.println("생성날짜: " + date);
		    System.out.println();
		}
		
		rs.close();
		pstmt.close();
		con.close();
		
		// 데이터 베이스에서 학생 정보를 조회하기 위한 코드
		// 조회할 정보를 쿼리문으로 적고 String타입 sql변수에 저장한다.
//		String sql = "select student_no, student_name, kor_score, eng_score, math_score, create_date  "
//				+ "from sample_scores "
//				+ "order by student_no asc";
//		
//		Connection con = ConnUtils.getConnection();
//		PreparedStatement pstmt = con.prepareStatement(sql);
//		ResultSet rs = pstmt.executeQuery();
//		
//		while (rs.next()) {
//		    int no = rs.getInt("student_no");
//		    String name = rs.getString("student_name");
//		    int kor = rs.getInt("kor_score");
//		    int eng = rs.getInt("eng_score");
//		    int math = rs.getInt("math_score");
//		    Date date = rs.getDate("create_date");
//		    System.out.println("학번: " + no);
//		    System.out.println("이름: " + name);
//		    System.out.println("국어: " + kor);
//		    System.out.println("영어: " + eng);
//		    System.out.println("수학: " + math);
//		    System.out.println("생성날짜: " + date);
//		    System.out.println();
//		}
//		
//		rs.close();
//		pstmt.close();
//		con.close();
//		
	}
}
