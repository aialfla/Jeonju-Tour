package dao;

import dbms.DBManager;
import vo.CbVo;
import vo.KeepVo;
import vo.LikesVo;

import java.util.ArrayList;

public class KeepDao extends DBManager {
	private ArrayList<KeepVo> klist = null;
	private boolean isMonitoring = true;	
	private String sql = "";
	
	// ���� ���� ��� ����Ʈ�� ��ȯ�ϴ� �޼ҵ�
	public ArrayList<KeepVo> SelectMyKeep(String uNO) {
		if (this.OpenDB()==false) return klist;
		sql = "select tbNO from keep where uNO = "+ uNO;
		if( this.isMonitoring ) System.out.println("Keep Dao : SelectMyKeep()" + sql);
		this.OpenQuery(sql);
		while (GetNext()) {
			if(klist == null) klist = new ArrayList<KeepVo>();
			KeepVo kv = new KeepVo();
			kv.setTbNO(this.GetValue("tbNO"));
			klist.add(kv);
		}
		this.CloseQuery();
		this.CloseDB();
		return klist;
	}
	
	// ��⸦ �����ϴ� �޼ҵ�
	public int Insert(KeepVo kv) {
		if (this.OpenDB()==false) return -1;
		sql = "insert into keep (uNO, tbNO) values "
			+ "("+kv.getuNO()+", "+kv.getTbNO()+")";
		if( this.isMonitoring ) System.out.println("keepdao : insert \n " + sql);
		int result = this.RunSQL(sql);
		this.CloseDB();
		return result;
	}
	
	// ��⸦ ����ϴ� �޼ҵ�
	public int Delete(KeepVo kv) {
		if (this.OpenDB()==false) return -1;
		sql = "delete from keep where uNO = "+kv.getuNO()+" and tbNO = "+kv.getTbNO();
		if( this.isMonitoring ) System.out.println("keepdao : delete \n " + sql);
		int result = this.RunSQL(sql);
		this.CloseQuery();
		this.CloseDB();
		return result;
	}
	
	// ���� ��Ҿ����� Ȯ���ϴ� �޼ҵ�
	public boolean Kept(String uNO, String tbNO) {
		if (this.OpenDB()==false) return false;
		sql = "select * from keep where uNO = "+uNO+" and tbNO = "+tbNO;
		this.OpenQuery(sql);
		if (GetNext()) {
			this.CloseQuery();
			this.CloseDB();
			return true;
		} else {
			this.CloseQuery();
			this.CloseDB();
			return false;
		}
	}
	
	
	
	// ������ ����Ʈ�� ������ ��� �޼ҵ�
	public int GetListSize() {
		if (this.klist==null) return 0;
		else return this.klist.size();
	}
	
	// ����Ʈ���� �ε����� �Խñ� ������ ��� �޼ҵ�
	public KeepVo GetItem(int index) {
		if (this.klist == null) return null;
		else return this.klist.get(index);
	}
	
	// ������ ����Ʈ ��ü�� ��ȯ�ϴ� �޼ҵ�
	public ArrayList<KeepVo> GetListAll() {
		return this.klist;
	}

	
	
	
	// ���� ���� �������� ����ϴ� �޼ҵ� =================== by.������
	public boolean SelectKeep(String uNO) {
		klist = null;
		if (this.OpenDB() == false) return false;
		sql = "SELECT b.tbTitle, b.tbAddr, kNO, a.tbNO, b.tbPic " 
				+ "FROM keep a " 
				+ "INNER JOIN tourboard b " 
				+ "ON a.tbNO = b.tbNO " 
				+ "WHERE a.uNO =" + uNO + " " 
				+ "ORDER BY kNO DESC";
		this.OpenQuery(sql);
		if( this.isMonitoring )
		{System.out.println("KeepDao :: SelectKeep() /\n" + sql);}
		while (this.GetNext()) {
			String kNO = this.GetValue("kNO");
			String tbTitle = this.GetValue("tbTitle");
			String tbAddr = this.GetValue("tbAddr");
			if (klist == null) klist = new ArrayList<KeepVo>();
			KeepVo kv = new KeepVo();
			kv.setTbPic(this.GetValue("tbpic"));
			kv.setkNO(kNO);
			kv.setTbNO(this.GetValue("tbNO"));
			kv.setTbTitle(tbTitle);
			kv.setTbAddr(tbAddr);
			klist.add(kv);
		}
		this.CloseQuery();
		this.CloseDB();
		return true;
	}


	// ���� �������� �����ϴ� �޼ҵ� =================== by.������
	public boolean Deletemine( String tbNO , String uNO )
	{
		if (this.OpenDB() == false) return false;
		sql  = " delete from keep where tbNO = " + tbNO + " AND uNO =" + uNO;
		this.RunSQL(sql);
		if( this.isMonitoring )
		{System.out.println("KeepDao :: Deletemine() /\n" + sql);}
		this.CloseDB();
		return true;
	}

	
}
