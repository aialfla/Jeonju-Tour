package dao;

import java.util.UUID;

import com.mysql.cj.xdevapi.PreparableStatement;

import dbms.DBManager;
import vo.UsersVo;

public class UsersDao extends DBManager
{
	public final static int MAIL_ERROR = 0;
	public final static int DUPLICATE = 1;
	public final static int NOT_DUPLICATE = 2;
	
	private boolean isMonitoring = true;
	
	// 이메일 중복 검사
	// ID_ERROR : 오류, DUPLICATE : 중복, NOT_DUPLICATE : 사용가능
	public int IsDuplicate (String mail)
	{
		System.out.println(mail);
		// DB 연결
		if(this.OpenDB() == false)
		{
			return MAIL_ERROR;
		}
		
		// 이메일 중복 검사
		String sql = "select uNo from users where uMail= '"+ mail +"'";
		if( this.isMonitoring )
		{
			System.out.println("UsersDao :: IsDuplicate() / \n"+sql);
		}
		this.OpenQuery(sql);
		
		if(this.GetNext() == true)
		{
			this.CloseQuery();
			this.CloseDB();
			return DUPLICATE;
		}
		this.CloseQuery();
		this.CloseDB();
		
		return NOT_DUPLICATE;
	}
	
	// 회원가입
	public boolean Join(UsersVo vo)
	{
		// 이메일 중복 검사
		if(this.IsDuplicate(vo.getuMail()) == UsersDao.DUPLICATE)
		{
			return false;
		}
		
		// DB연결
		if(this.OpenDB() == false) return false;
		
		// 회원가입 SQL
		
		String sql = "insert into users(uMail, uPW, uName, uiskakao, ulevel, uCerti) "
				+ "values('"+vo.getuMail()+"', md5('"+vo.getuPW()+"'), '"+vo.getuName()+"', '"+vo.getuIsKakao()+"', '"+vo.getuLevel()+"' , '"+vo.getuCerti()+"')";
		
		if(this.isMonitoring)
		{
			System.out.println("UsersDao :: Join() / \n"+sql);
		}
		this.RunSQL(sql);
		
		// DB 연결 종료
		this.CloseDB();
		return true;
	}
	
	
	// 로그인
	public UsersVo Login(String mail, String pw) {
		UsersVo uv = null;
		
		// [1] DB 연결
		if (this.OpenDB() == false) return uv;
		
		// [2] id, pw를 이용하여 사용자 정보를 받아온다
		String sql = "";
		sql += "select uno, uname, umail, ulevel from users ";
		sql += "where umail = '"+mail+"' ";
		sql += "and upw = md5('"+pw+"') ";
		sql += "and ulevel != 'T' ";
//		System.out.println("AccountDao :: Login() /\n" + sql);
		this.OpenQuery(sql);
		if (GetNext()==false) {
			this.CloseQuery();
			this.CloseDB();
			return uv;
		}
		// [3] 로그인 사용자 vo를 생성
		uv = new UsersVo();
		uv.setuNO(this.GetValue("uNO"));
		uv.setuMail(mail);
		uv.setuName(this.GetValue("uName"));
		uv.setuLevel(this.GetValue("uLevel"));
		this.CloseQuery();
		this.CloseDB();
		return uv;
	}
}

