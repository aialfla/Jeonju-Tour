package vo;

public class TbVo {
	//필드
	private String tbNO;		// 추천 관광지 번호
	private String tbTitle;		// 이름
	private String tbAddr;		// 주소
	private String tbTel;		// 전화번호
	private String tbTime;		// 영업시간
	private String tbDetail;	// 상세설명
	private String tbPic;		// 대표사진
	private String tbCategory;	// 카테고리
	private String cbNO;		// 추천일정게시판 글번호
	private String where;		// 

	// 생성자
//	public TbVo() {
//		this.setTbPic(null);
//		this.setTbTime(null);
//		this.setTbDetail(null);
//	}
	
	// Getters /
	public String getTbNO() {
		return tbNO;
	}
	public String getTbTitle() {
		return tbTitle;
	}
	public String getTbAddr() {
		return tbAddr;
	}
	public String getTbTel() {
		return tbTel;
	}
	public String getTbTime() {
		return tbTime;
	}
	public String getTbDetail() {
		return tbDetail;
	}
	public String getTbPic() {
		return tbPic;
	}
	public String getTbCategory() {
		return tbCategory;
	}
	public String getCbNO() {
		return cbNO;
	}
	public String getWhere() {
		return where;
	}
	// Setters
	public void setTbNO(String tbNO) {
		this.tbNO = tbNO;
	}
	public void setTbTitle(String tbTitle) {
		this.tbTitle = tbTitle;
	}
	public void setTbAddr(String tbAddr) {
		this.tbAddr = tbAddr;
	}
	public void setTbTel(String tbTel) {
		if (tbTel == null) {
			this.tbTel = "정보 미제공";
		} else {
		this.tbTel = tbTel;
		}
	}
	public void setTbTime(String tbTime) {
		if(tbTime == null) {
			this.tbTime = "전화 문의";
		} else {
		this.tbTime = tbTime;
		}
	}
	public void setTbDetail(String tbDetail) {
		if (tbDetail == null) {
			this.tbDetail = "상세 설명이 제공되지 않습니다";
		} else {
			this.tbDetail = tbDetail;
		}
	}
	public void setTbPic(String tbPic) {
		if (tbPic == null) {
			this.tbPic = "default.jpg";
		} else {
		this.tbPic = tbPic;
		}
	}
	public void setTbCategory(String tbCategory) {
		this.tbCategory = tbCategory;
	}
	public void setCbNO(String cbNO) {
		this.cbNO = cbNO;
	}
	public void setWhere(String where) {
		this.where = where;
	}
}

