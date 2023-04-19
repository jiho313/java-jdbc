package sample02;

import java.util.Date;

public class Score {
	
	// 테이블에 해당하는 클래스
	private int studentNo;
	private String studentName;
	private int kor;
	private int eng;
	private int math;
	private Date createDate;
	
	public Score() {}

	public int getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public int getTotalScore() {
		return kor + eng + math;
	}
	
	public int getAverage() {
		return getTotalScore()/3;
	}
	
	
}
