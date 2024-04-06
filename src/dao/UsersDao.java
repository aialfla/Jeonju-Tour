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
	
	// �̸��� �ߺ� �˻�
	// ID_ERROR : ����, DUPLICATE : �ߺ�, NOT_DUPLICATE : ��밡��
	public int IsDuplicate (String mail)
	{
		System.out.println(mail);
		// DB ����
		if(this.OpenDB() == false)
		{
			return MAIL_ERROR;
		}
		
		// �̸��� �ߺ� �˻�
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
	
	// ȸ������
	public boolean Join(UsersVo vo)
	{
		// �̸��� �ߺ� �˻�
		if(this.IsDuplicate(vo.getuMail()) == UsersDao.DUPLICATE)
		{
			return false;
		}
		
		// DB����
		if(this.OpenDB() == false) return false;
		
		// ȸ������ SQL
		
		String sql = "insert into users(uMail, uPW, uName, uiskakao, ulevel, uCerti) "
				+ "values('"+vo.getuMail()+"', md5('"+vo.getuPW()+"'), '"+vo.getuName()+"', '"+vo.getuIsKakao()+"', '"+vo.getuLevel()+"' , '"+vo.getuCerti()+"')";
		
		if(this.isMonitoring)
		{
			System.out.println("UsersDao :: Join() / \n"+sql);
		}
		this.RunSQL(sql);
		
		// DB ���� ����
		this.CloseDB();
		return true;
	}
	
	
	// �α���
	public UsersVo Login(String mail, String pw) {
		UsersVo uv = null;
		
		// [1] DB ����
		if (this.OpenDB() == false) return uv;
		
		// [2] id, pw�� �̿��Ͽ� ����� ������ �޾ƿ´�
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
		// [3] �α��� ����� vo�� ����
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

