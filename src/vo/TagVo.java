package vo;

public class TagVo {
	// �ʵ�
	private String tagNO;		// �±� ���� ��ȣ : A1~A4 / B1~B4 / C1~C4 / D1~C4
	private String tagWhat;		// �±� ���� : 20�� �̸� ~ 60�� �̻� / ȥ�� ~ ��ü / ���� ~ ������ �̻� / ���� �ĵ��� ü�� �޽� 
	
	// ����
	public String getTagNO() {
		return tagNO;
	}
	public String getTagWhat() {
		return tagWhat;
	}
	
	// ����
	public void setTagNO(String tagNO) {
		this.tagNO = tagNO;
	}
	public void setTagWhat(String tagWhat) {
		this.tagWhat = tagWhat;
	}
}
