package vo;

public class KeepVo {
	// �ʵ�
	private String kNO; 		// ��� ���� ��ȣ
	private String uNO; 		// ȸ�� ��ȣ
	private String tbNO;		// ��õ ������ ��ȣ
	private String tbTitle;		// ������ ����
	private String tbAddr;		// ������ �ּ�
	private String tbPic;		// ������ ��ǥ����

	// ����
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
	
	// ����
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
