package vo;

public class ReplyVo {
	// �ʵ�
	private String rNO;
	private String rNote;
	private String rDate;
	private String uNO;
	private String tbNO;
	private String uName;
	private String tbTitle;
	
	//����
	public String getrNO() {
		return rNO;
	}
	public String getrNote() {
		return rNote;
	}
	public String getrDate() {
		return rDate;
	}
	public String getuNO() {
		return uNO;
	}
	public String getuName() {
		return uName;
	}
	public String getTbNO() {
		return tbNO;
	}
	public String getTbTitle() {
		return tbTitle;
	}
	
	// ����
	public void setrNO(String rNO) {
		this.rNO = rNO;
	}
	public void setrNote(String rNote) {
		this.rNote = rNote;
	}
	public void setrDate(String rDate) {
		this.rDate = rDate;
	}
	public void setuNO(String uNO) {
		this.uNO = uNO;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public void setTbNO(String tbNO) {
		this.tbNO = tbNO;
	}
	public void setTbTitle(String tbTitle) {
		this.tbTitle = tbTitle;
	}
}
