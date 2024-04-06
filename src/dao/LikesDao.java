package dao;

import java.util.ArrayList;

import dbms.DBManager;
import vo.*;

public class LikesDao extends DBManager {
	private ArrayList<LikesVo> likelist = null;
	private boolean isMonitoring = true;
	String sql = "";

	/*
	// ��õ ������ȣ(lNO) ��ȸ
	public Boolean Inquiry(String uNO, String cbNO)
	{
		if (this.OpenDB() == false) return false;
		sql = "select * from likes where uNO = "+ uNO +" AND cbNO = "+ cbNO +"";
		System.out.println("LikesDao :: Inquiry() /\n" + sql);
		this.OpenQuery(sql);
		if (GetNext()) {
			return true;
		} else {
			return false;
		}
	}
	*/
	

	// �Խñ� ��õ �� ��ü ��ȸ
	public String boardInquiry (String cbNO)
	{
		String cnt = "";

		// DB ����
		if (this.OpenDB() == false) return cnt;
		sql = " select count(*) as cnt from likes where cbNO = "+ cbNO ;
		if (this.isMonitoring) System.out.println("LikesDao :: boardInquiry() /\n" + sql);
		
		this.OpenQuery(sql);
		LikesVo lv  = null;
		LikesDao ld = new LikesDao();
		
		if (GetNext())
		{
			lv = new LikesVo();
			lv.setCnt(this.GetValue("cnt"));
			cnt = lv.getCnt();
		}
		if (!GetNext())
		{
			this.CloseQuery();
			this.CloseDB();
			return cnt;
		}
		this.CloseQuery();
		this.CloseDB();
		return cnt;
	}
	
	// ��õ ������ȣ(lNO) ��ȸ �� ���� �� ���� �޼ҵ�
	public String Inquiry(LikesVo lv)
	{	
		// DB ����
		if (this.OpenDB() == false) return "N";

		// �α��� ���� ȸ����ȣ(uNO), ���� �� ��ȣ(cbNO)�� ���� ��õ ������ȣ(lNO) ��ȸ
		sql = " select * from likes where uNO = "+ lv.getuNO() +" AND cbNO = "+ lv.getCbNO() +" ";
		if (this.isMonitoring) System.out.println("LikesDao :: Inquiry() /\n" + sql);
		this.OpenQuery(sql);
		
		if(this.GetNext() != false)
		{
			this.Delete(lv);
			this.CloseQuery();
			this.CloseDB();
			return "N";
		}else{
			this.Insert(lv);
			this.CloseQuery();
			this.CloseDB();
			return "Y";
		}
	}
	
	// <��õ> ������ ���� ����
	public boolean Insert(LikesVo lv)
	{
		// DB ����
		if (this.OpenDB()==false) return false;
		
		// �α��� ���� ȸ����ȣ(uNO), ���� �� ��ȣ(cbNO)�� ���� ������ �Է�
		sql = " insert into likes (uNO, cbNO) values("+ lv.getuNO() + ", "+ lv.getCbNO() + ") ";
		if (this.isMonitoring) System.out.println("LikesDao :: Insert() /\n" + sql);
		this.RunSQL(sql);
		this.CloseQuery();
		this.CloseDB();
		
		return true;
	}
	
	// <��õ> ������ ���� ����
	public boolean Delete(LikesVo lv)
	{
		// DB ����
		if (this.OpenDB()==false) return false;
		
		// �α��� ���� ȸ����ȣ(uNO), ���� �� ��ȣ(cbNO)�� ���� ������ ����
		sql = " delete from likes where uNO = "+lv.getuNO()+" and cbNO = "+lv.getCbNO()+" ";
		if (this.isMonitoring) System.out.println("LikesDao :: Delete() /\n" + sql);
		
		this.RunSQL(sql);
		this.CloseQuery();
		this.CloseDB();
		
		return true;
	}	


	// ������ ����Ʈ�� ������ ��� �޼ҵ�
	public int GetListSize() {
		if (this.likelist==null) return 0;
		else return this.likelist.size();
	}
	
	// ����Ʈ���� �ε����� �Խñ� ������ ��� �޼ҵ�
	public LikesVo GetItem(int index) {
		if (this.likelist == null) return null;
		else return this.likelist.get(index);
	}
	
	// ������ ����Ʈ ��ü�� ��ȯ�ϴ� �޼ҵ�
	public ArrayList<LikesVo> GetListAll()
	{
		return this.likelist;
	}


	// ���� ��õ�� ������ ����ϴ� �޼ҵ� =================== by.������
	public boolean SelectLike(String uNO) {
		likelist = null;
		if (this.OpenDB() == false) return false;
		sql = "SELECT b.cbtitle, lNO, a.cbno " 
				+ "FROM likes a " 
				+ "INNER JOIN courseboard b " 
				+ "ON a.cbNO = b.cbNO " 
				+ "WHERE a.uNO =" + uNO + " " 
				+ "ORDER BY lNO DESC";
		this.OpenQuery(sql);
		if (this.isMonitoring) System.out.println("LikesDao :: SelectLike() /\n" + sql);
		while (this.GetNext()) {
			String lNO = this.GetValue("lNO");
			String cbNO = this.GetValue("cbNO");
			String cbTitle = this.GetValue("cbTitle");
			if (likelist == null) likelist = new ArrayList<LikesVo>();
			LikesVo lv = new LikesVo();
			lv.setlNO(lNO);
			lv.setCbNO(cbNO);
			lv.setCbTitle(cbTitle);
			likelist.add(lv);
		}
		this.CloseQuery();
		this.CloseDB();
		return true;
	}
	
	
	// ���� ��õ�� ������ ����ϴ� �޼ҵ� =================== by.������
	public boolean Deletemine( String cbNO, String uNO )
	{
		if (this.OpenDB() == false) return false;
		// �Խñ� ����
		sql  = " delete from likes where cbNO = " + cbNO + " AND uNO =" + uNO;
		this.RunSQL(sql);
		if( this.isMonitoring )
		{System.out.println("LikesDao :: Deletemine() /\n" + sql);}
		this.CloseDB();
		return true;
	}

	public String checkLike (String cbNO, String uNO) {
		String result = "";
		if (this.OpenDB() == false) return result;
		sql = "select lNO from likes where uNO = "+ uNO + " and cbNO = "+ cbNO;
		if (this.isMonitoring) System.out.println("likesDao : checkLike \n" +sql);
		this.OpenQuery(sql);
		if(this.GetNext()) {
			result = "Y";
		} else {
			result = "N";
		}
		return result;
	}
}
