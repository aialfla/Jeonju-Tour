package vo;

import java.util.ArrayList;

public class CbVo {
	// �ʵ�
	private String cbNO;							// �����Խ��� �� ��ȣ
	private String cbTitle;							// ����
	private String cbPeriod;						// ��������
	private String cbNote;							// �󼼳���
	private String cbDate;							// �ۼ���
	private String cbPublic;						// ��������
	private String uNO;								// ȸ�� ��ȣ
	private String uName;							// ȸ�� �̸�
	private String lCnt;							// ��õ��
	private String age;								// ����
	private String person;							// �ο�
	private String purpose;							// ����
	private String uLike;							// �α��� ȸ�� ��õ����
	private ArrayList<String> userLike  = null;		// �α��� ȸ�� ��õ����
	private String sOrder;							// ���� ����
	private String tbPic;							// ��ǥ����
	
	
	// ����
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

	
	// ����
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
