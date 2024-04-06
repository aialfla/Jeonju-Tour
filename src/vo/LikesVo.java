package vo;

public class LikesVo {
	private String lNO;			// 추천 관리 번호
	private String uNO;			// 회원 번호
	private String cbNO;		// 일정게시판 글 번호
	private String cbTitle;		// 일정 제목
	private String cnt;			// 게시글 추천 수

	// 게터
	public String getlNO() {
		return lNO;
	}
	public String getuNO() {
		return uNO;
	}
	public String getCbNO() {
		return cbNO;
	}
	public String getCbTitle() {
		return cbTitle;
	}
	public String getCnt() {
		return cnt;
	}

	
	// 세터
	public void setlNO(String lNO) {
		this.lNO = lNO;
	}
	public void setuNO(String uNO) {
		this.uNO = uNO;
	}
	public void setCbNO(String cbNO) {
		this.cbNO = cbNO;
	}
	public void setCbTitle(String cbTitle) {
		this.cbTitle = cbTitle;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

}
