package vo;

public class LikesVo {
	private String lNO;			// ��õ ���� ��ȣ
	private String uNO;			// ȸ�� ��ȣ
	private String cbNO;		// �����Խ��� �� ��ȣ
	private String cbTitle;		// ���� ����
	private String cnt;			// �Խñ� ��õ ��

	// ����
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

	
	// ����
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
