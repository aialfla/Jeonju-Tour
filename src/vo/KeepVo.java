package vo;

public class KeepVo {
	// 필드
	private String kNO; 		// 담기 관리 번호
	private String uNO; 		// 회원 번호
	private String tbNO;		// 추천 여행지 번호
	private String tbTitle;		// 여행지 제목
	private String tbAddr;		// 여행지 주소
	private String tbPic;		// 여행지 대표사진

	// 게터
	public String getkNO() {
		return kNO;
	}
	public String getuNO() {
		return uNO;
	}
	public String getTbNO() {
		return tbNO;
	}
	public String getTbTitle() {
		return tbTitle;
	}
	public String getTbAddr() {
		return tbAddr;
	}
	public String getTbPic() {
		return tbPic;
	}
	
	// 세터
	public void setkNO(String kNO) {
		this.kNO = kNO;
	}
	public void setuNO(String uNO) {
		this.uNO = uNO;
	}
	public void setTbNO(String tbNO) {
		this.tbNO = tbNO;
	}
	public void setTbTitle(String tbTitle) {
		this.tbTitle = tbTitle;
	}
	public void setTbAddr(String tbAddr) {
		this.tbAddr = tbAddr;
	}
	public void setTbPic(String tbPic) {
		this.tbPic = tbPic;
	}
	
	
	
}
