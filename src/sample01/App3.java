package sample01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.ConnUtils;

public class App3 {

	public static void main(String[] args) throws Exception {
		
		// jobs 테이블의 모든 직종 정보를 조회하기
		String sql = "select job_id, job_title, min_salary, max_salary "
				+ "from jobs "
				+ "order by job_id asc";
		
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs= pstmt.executeQuery();
		
		while(rs.next()) {
			String jobId = rs.getString("job_id");
			String jobTitle = rs.getString("job_title");
			int minSalary = rs.getInt("min_salary");
			int maxSalary = rs.getInt("max_salary");
			
			System.out.println("직업아이디: " + jobId);
			System.out.println("직업이름: " + jobTitle);
			System.out.println("최소급여: " + minSalary);
			System.out.println("최대급여: " + maxSalary);
			System.out.println();
		}
		
		con.close();
		pstmt.close();
		rs.close();
		
	}
}
