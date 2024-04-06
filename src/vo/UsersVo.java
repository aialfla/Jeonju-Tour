package vo;

public class UsersVo
{
	// 필드
	private String uNO;		// 회원 번호
	private String uMail;	// 이메일 주소
	private String uPW;		// 비밀번호
	private String uName;	// 회원 이름
	private String uLevel;	// 회원 등급 
	private String uCerti;	// 인증 번호
	private String uIsKakao;// 회원 구분

	
	// 생성자
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
		System.out.println("회원번호"+uNO);
		System.out.println("이메일"+uMail);
		System.out.println("이름"+uName);
		System.out.println("레벨"+uLevel);

	}
	
	
}
