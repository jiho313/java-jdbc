package sample02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.ConnUtils;

public class ScoreDao {
	// 데이터 엑세스 오브젝트
	/**
	 * 성적정보를 전달받아서 sample_scores 테이블에 추가한다.
	 * @param score 성적정보
	 * @throws SQLException 
	 */
	
	public void insertScore(Score score) throws SQLException {
		String sql = "insert into sample_scores "
				+"(student_no, student_name, kor_score, eng_score, math_score)"
				+"values"
				+"(sample_student"
				+ "_seq.nextval, ?,?,?,?)";
		
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, score.getStudentName());
		pstmt.setInt(2, score.getKor());
		pstmt.setInt(3, score.getEng());
		pstmt.setInt(4, score.getMath());
		
		pstmt.executeUpdate();
		
		con.close();
		pstmt.close();
	}

	/**
	 * sample_scores 테이블의 모든 성적정보를 반환한다.
	 * @return 모든 성적정보, 성적정보가 존재하지 않으면 빈 {@List<Score>} 객체가 반환된다.
	 * @throws SQLException 
	 */
	public List<Score> getAllScores() throws SQLException{	// 반환할 객체가 여러개이기 때문에 List반환타입
		String sql = "select student_no, student_name, kor_score, eng_score, math_score, create_date "
				+ "from sample_scores "
				+ "order by student_no asc";
		
		List<Score> scoreList = new ArrayList<>();
		
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int no = rs.getInt("student_no");
			String name = rs.getNString("student_name");
			int kor = rs.getInt("kor_score");
			int eng = rs.getInt("eng_score");
			int math = rs.getInt("eng_score");
			Date createDate = rs.getDate("create_date");
		    
			Score score = new Score();
			score.setStudentNo(no);
			score.setStudentName(name);
			score.setKor(kor);
			score.setEng(eng);
			score.setMath(math);
			score.setCreateDate(createDate);
			
			scoreList.add(score);
		}
		con.close();
		pstmt.close();
		rs.close();
		
		return scoreList;
	}
	
	/**
	 * 전달받은 학생의 성적정보를 sample_scores 테이블에서 조회해서 반환한다.
	 * @param studentNo 학생번호
	 * @return 성적정보, 성적정보가 존재하지 않으면 null을 반환한다.
	 * @throws SQLException 
	 */
	public Score getScoreByStudentNo(int studentNo) throws SQLException { // 반환할 객체가 하나기 때문에 Score반환타입
		// 실행할 쿼리를 기준으로 매개변수를 정한다.
		String sql ="select student_no, student_name, kor_score, eng_score, math_score, create_date "
				+ "from sample_scores "
				+ "where student_no = ?";
		Score score = null;
		
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, studentNo);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			int no = rs.getInt("student_no");
			String name = rs.getString("student_name");
			int kor = rs.getInt("kor_score");
			int eng = rs.getInt("eng_score");
			int math = rs.getInt("math_score");
			Date createDate = rs.getDate("create_date");
			
			score = new Score();
			score.setStudentNo(no);
			score.setStudentName(name);
			score.setKor(kor);
			score.setEng(eng);
			score.setMath(math);
			score.setCreateDate(createDate);
		}

		con.close();
		pstmt.close();
		rs.close();
		
		return score;
	}
	
	/**
	 * 전달받은 학생의 성적정보를 sample_scores 테이블에서 삭제한다.
	 * @param studentNo 학생번호
	 * @throws SQLException 
	 */
	public void deleteScore(int studentNo) throws SQLException {
		String sql = "delete from sample_scores "
				+ "where student_no =?";
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, studentNo);
		pstmt.executeUpdate();
		
	}
	
	/**
	 * 수정된 정보가 포함된 성적정보를 전달받아서 sample_scores 테이블에서 반영시킨다.
	 * @param score 성적정보
	 */
	public void updateScore(Score score) {
		
	}
	

}
