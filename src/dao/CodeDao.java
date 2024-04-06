package dao;
import dbms.DBManager;
import vo.*;

public class CodeDao extends DBManager {
	
	private boolean isMonitoring = true;
	
	private SearchVo search = null;
	private String   code   = null;
	
	private String sql = "";
	
	// �⺻ ������
	public CodeDao() {}
	
	public CodeDao(SearchVo search)
	{
		this.search = search;
		
		if(this.search == null)
		{
			this.search = new SearchVo();
		}
		// <SQL ����> code ��������
		this.code   = this.search.getuCode();
	}
	
	String uMail = null;
	/* ȸ������ �� �������� URL Ŭ�� �� �ڵ� Ȯ�� �޼ҵ� */	
	public String joinCode(SearchVo vo)
	{
		this.sql = "";
		if (this.OpenDB() == false) return uMail;
		
//		�� �Ķ��Ÿ�� ���� �����ڵ� = DB uCerty ��ġ ���� Ȯ��
		this.sql = " select uMail from users where uCerti = '" + vo.getCode() + "' ";
		if( this.isMonitoring ) { System.out.println( "codeDao :: codeDao() / \n"+sql); }	
		this.OpenQuery(sql);

		if(this.GetNext())
		{
//			�� ��ġO : mail �� ��ȯ
			uMail = this.GetValue("uMail");
		}
		this.CloseQuery();
		this.CloseDB();
		
		return uMail;
	}
	
	/* ���� URL �ڵ�� Mail ���� �޴� �޼ҵ�*/
	public boolean joinCodeOk(String uMail)
	{
		if (this.OpenDB() == false) return false;
		
//		�� <ȸ������> T: �̸��� ����X �� U: �̸��� ����O ����
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
