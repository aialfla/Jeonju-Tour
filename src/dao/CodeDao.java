package dao;
import dbms.DBManager;
import vo.*;

public class CodeDao extends DBManager {
	
	private boolean isMonitoring = true;
	
	private SearchVo search = null;
	private String   code   = null;
	
	private String sql = "";
	
	// 기본 생성자
	public CodeDao() {}
	
	public CodeDao(SearchVo search)
	{
		this.search = search;
		
		if(this.search == null)
		{
			this.search = new SearchVo();
		}
		// <SQL 구문> code 가져오기
		this.code   = this.search.getuCode();
	}
	
	String uMail = null;
	/* 회원가입 후 메일인증 URL 클릭 후 코드 확인 메소드 */	
	public String joinCode(SearchVo vo)
	{
		this.sql = "";
		if (this.OpenDB() == false) return uMail;
		
//		→ 파라메타로 보낸 인증코드 = DB uCerty 일치 여부 확인
		this.sql = " select uMail from users where uCerti = '" + vo.getCode() + "' ";
		if( this.isMonitoring ) { System.out.println( "codeDao :: codeDao() / \n"+sql); }	
		this.OpenQuery(sql);

		if(this.GetNext())
		{
//			→ 일치O : mail 값 반환
			uMail = this.GetValue("uMail");
		}
		this.CloseQuery();
		this.CloseDB();
		
		return uMail;
	}
	
	/* 메일 URL 코드로 Mail 값을 받는 메소드*/
	public boolean joinCodeOk(String uMail)
	{
		if (this.OpenDB() == false) return false;
		
//		→ <회원구분> T: 이메일 인증X → U: 이메일 인증O 변경
		this.sql = " UPDATE users set uLevel = 'U' where uMail = '"+ this.uMail +"' ";
		this.RunSQL(sql);
		if( this.isMonitoring )
		{
			System.out.println("codeDao :: joinCodeOk() /\n" + sql);
		}
		this.CloseDB();
		
		return true;
	}
}
