package vo;

public class SdVo {
	//�ʵ�
	private String sNO;			// ���� ���� ��ȣ
	private String cbNO;		// �����Խ��� �� ��ȣ
	private String sDay;		// ���� ����
	private String sOrder;		// ���� ����
	private String kNO;			// ��� ���� ��ȣ
	private String tbNO;		// ��õ ������ ��ȣ
	private String tbTitle;		// ������ ����
	private String sTrans;		// �̵����� ��ȣ
	private String sDistance;	// �Ÿ�
	private String sTime;		// �ҿ� �ð�
	private String tbPic;		// ��ǥ����


	//����
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

	//����
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
		if(sTrans == null) sTrans = "����";
		this.sTrans = sTrans;
	}
	
	public void setsDistance(String sDistance) {
		if(sDistance == null) sDistance = "0Km";
		this.sDistance = sDistance;
	}
	
	public void setsTime(String sTime) {
		if(sTime == null) sTime = "0��";
		this.sTime = sTime;
	}
	
	public void setTbpic(String tbPic) {
		if (tbPic == null) tbPic = "default.jpg";
		this.tbPic = tbPic;
	}

}
