package vo;

public class TbVo {
	//�ʵ�
	private String tbNO;		// ��õ ������ ��ȣ
	private String tbTitle;		// �̸�
	private String tbAddr;		// �ּ�
	private String tbTel;		// ��ȭ��ȣ
	private String tbTime;		// �����ð�
	private String tbDetail;	// �󼼼���
	private String tbPic;		// ��ǥ����
	private String tbCategory;	// ī�װ�
	private String cbNO;		// ��õ�����Խ��� �۹�ȣ
	private String where;		// 

	// ������
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
			this.tbTel = "���� ������";
		} else {
		this.tbTel = tbTel;
		}
	}
	public void setTbTime(String tbTime) {
		if(tbTime == null) {
			this.tbTime = "��ȭ ����";
		} else {
		this.tbTime = tbTime;
		}
	}
	public void setTbDetail(String tbDetail) {
		if (tbDetail == null) {
			this.tbDetail = "�� ������ �������� �ʽ��ϴ�";
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

