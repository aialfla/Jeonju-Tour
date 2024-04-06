package vo;

public class SdVo {
	//필드
	private String sNO;			// 일정 관리 번호
	private String cbNO;		// 일정게시판 글 번호
	private String sDay;		// 여행 일차
	private String sOrder;		// 일정 순서
	private String kNO;			// 담기 관리 번호
	private String tbNO;		// 추천 관광지 번호
	private String tbTitle;		// 여행지 제목
	private String sTrans;		// 이동수단 번호
	private String sDistance;	// 거리
	private String sTime;		// 소요 시간
	private String tbPic;		// 대표사진


	//게터
	public String getsNO() {
		return sNO;
	}
	
	public String getCbNO() {
		return cbNO;
	}

	public String getsDay() {
		return sDay;
	}

	public String getsOrder() {
		return sOrder;
	}

	public String getkNO() {
		return kNO;
	}

	public String getTbNO() {
		return tbNO;
	}

	public String getTbTitle() {
		return tbTitle;
	}
	
	public String getsTrans() {
		return sTrans;
	}

	public String getsDistance() {
		return sDistance;
	}

	public String getsTime() {
		return sTime;
	}

	public String getTbPic() {
		return tbPic;
	}

	//세터
	public void setsNO(String sNO) {
		this.sNO = sNO;
	}
	
	public void setCbNO(String cbNO) {
		this.cbNO = cbNO;
	}
	
	public void setsDay(String sDay) {
		if(sDay == null) sDay = "1";
		this.sDay = sDay;
	}
	
	public void setsOrder(String sOrder) {
		this.sOrder = sOrder;
	}
	
	public void setkNO(String kNO) {
		this.kNO = kNO;
	}
	
	public void setTbNO(String tbNO) {
		this.tbNO = tbNO;
	}
	
	public void setTbTitle(String tbTitle) {
		this.tbTitle = tbTitle;
	}
	
	public void setsTrans(String sTrans) {
		if(sTrans == null) sTrans = "도보";
		this.sTrans = sTrans;
	}
	
	public void setsDistance(String sDistance) {
		if(sDistance == null) sDistance = "0Km";
		this.sDistance = sDistance;
	}
	
	public void setsTime(String sTime) {
		if(sTime == null) sTime = "0분";
		this.sTime = sTime;
	}
	
	public void setTbpic(String tbPic) {
		if (tbPic == null) tbPic = "default.jpg";
		this.tbPic = tbPic;
	}

}
