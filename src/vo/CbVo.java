package vo;

import java.util.ArrayList;

public class CbVo {
	// 필드
	private String cbNO;							// 일정게시판 글 번호
	private String cbTitle;							// 제목
	private String cbPeriod;						// 여행일자
	private String cbNote;							// 상세내용
	private String cbDate;							// 작성일
	private String cbPublic;						// 공개여부
	private String uNO;								// 회원 번호
	private String uName;							// 회원 이름
	private String lCnt;							// 추천수
	private String age;								// 나이
	private String person;							// 인원
	private String purpose;							// 목적
	private String uLike;							// 로그인 회원 추천여부
	private ArrayList<String> userLike  = null;		// 로그인 회원 추천여부
	private String sOrder;							// 일정 순서
	private String tbPic;							// 대표사진
	
	
	// 게터
	public String getCbNO() {
		return cbNO;
	}
	public String getCbTitle() {
		return cbTitle;
	}
	public String getCbPeriod() {
		return cbPeriod;
	}
	public String getCbNote() {
		return cbNote;
	}
	public String getCbDate() {
		return cbDate;
	}
	public String getCbPublic() {
		return cbPublic;
	}
	public String getuNO() {
		return uNO;
	}
	public String getuName() {
		return uName;
	}
	public String getlCnt() {
		return lCnt;
	}
	public String getPerson() {
		return person;
	}
	public String getPurpose() {
		return purpose;
	}
	public String getAge() {
		return age;
	}
	public String getuLike() {
		return uLike;
	}

	public String getsOrder() {
		return sOrder;
	}

	public String getTbPic() {
		return tbPic;
	}

	
	// 세터
	public void setCbNO(String cbNO) {
		this.cbNO = cbNO;
	}
	public void setCbTitle(String cbTitle) {
		this.cbTitle = cbTitle;
	}
	public void setCbPeriod(String cbPeriod) {
		this.cbPeriod = cbPeriod;
	}
	public void setCbNote(String cbNote) {
		this.cbNote = cbNote;
	}
	public void setCbDate(String cbDate) {
		this.cbDate = cbDate;
	}
	public void setCbPublic(String cbPublic) {
		this.cbPublic = cbPublic;
	}
	public void setuNO(String uNO) {
		this.uNO = uNO;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public void setlCnt(String lCnt) {
		this.lCnt = lCnt;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public ArrayList<String> getUserLike() {
		return userLike;
	}
	public void setUserLike(ArrayList<String> userLike) {
		this.userLike = userLike;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public void setuLike(String uLike) {
		this.uLike = uLike;
	}
	public void setsOrder(String sOrder) {
		this.sOrder = sOrder;
	}
	public void setTbpic(String tbPic) {
		if (tbPic == null) tbPic = "default.jpg";
		this.tbPic = tbPic;
	}
}
