package sample01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class App1 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 1. jdbc 드라이버를 JVM의 드라이버 레지스트리에 등록하기
		Class.forName("oracle.jdbc.OracleDriver");
		
		// 2. JVM의 드라이버 레지스트리에 등록된 jdbc 드라이버를 이용해서
		//	  데이터베이스와 연결을 담당하는 Connection 인터페이스 구현객체 획득하기
		// thin -> 오라클 jdbc 드라이버의 타입4 드라이버
		// @ 내 컴퓨터에서 실행되는 경로
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "hr";
		String password = "zxcv1234";
		// 모든 SQL연결 작업에 대해서 Exception이 발생한다.
		Connection conn = DriverManager.getConnection(url, username, password);
		System.out.println(conn);

		
		// 3. SQL을 작성한다. 디폴트가 커밋이라 인서트하는 순간 저장된다.
		// 값이 들어갈 구문이 미리 정해져있는 것이 PreparedStatement다.
		String sql = "insert into sample_scores"
				+"(student_no, student_name, kor_score, eng_score, math_score)"
				+"values"
				+"(sample_student_seq.nextval, ?, ?, ?, ?)";
		
		// 4. PreparedStatment객체를 획득하기
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		// 5. 바인딩변수(?)에 값 설정하기
		pstmt.setString(1, "안중근");
		pstmt.setInt(2, 80);
		pstmt.setInt(3, 50);
		pstmt.setInt(4, 60);
		
		// 6. SQL을 DBMS로 전송해서 실행시키기
		int rowCount = pstmt.executeUpdate();
		System.out.println(rowCount + "개의 행이 추가되었습니다.");
		
		// 7. 자원 반납하기
		pstmt.close();
		conn.close();
	}
}
