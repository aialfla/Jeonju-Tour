package vo;

public class UsersVo
{
	// �ʵ�
	private String uNO;		// ȸ�� ��ȣ
	private String uMail;	// �̸��� �ּ�
	private String uPW;		// ��й�ȣ
	private String uName;	// ȸ�� �̸�
	private String uLevel;	// ȸ�� ��� 
	private String uCerti;	// ���� ��ȣ
	private String uIsKakao;// ȸ�� ����

	
	// ������
	public UsersVo() {
		
	}
	
	// Getters
	public String getuNO() {
		return uNO;
	}
	public String getuMail() {
		return uMail;
	}
	public String getuPW() {
		return uPW;
	}
	public String getuName() {
		return uName;
	}
	public String getuLevel() {
		return uLevel;
	}
	
	public String getuCerti() {
		return uCerti;
	}

	public String getuIsKakao() {
		return uIsKakao;
	}

	//Setters
	public void setuNO(String uNO) {
		this.uNO = uNO;
	}
	public void setuMail(String uMail) {
		this.uMail = uMail;
	}
	public void setuPW(String uPW) {
		this.uPW = uPW;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public void setuLevel(String uLevel) {
		this.uLevel = uLevel;
	}
	public void setuCerti(String uCerti) {
		this.uCerti = uCerti;
	}
	public void setuIsKakao(String uIsKakao) {
		this.uIsKakao = uIsKakao;
	}
	
	



	public void PrintInfo() {
		System.out.println("<UsersVo>");
		System.out.println("<PrintInfo>");
		System.out.println("ȸ����ȣ"+uNO);
		System.out.println("�̸���"+uMail);
		System.out.println("�̸�"+uName);
		System.out.println("����"+uLevel);

	}
	
	
}
