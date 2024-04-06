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
	
	// 나의 담은 목록 리스트를 반환하는 메소드
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
	
	// 담기를 수행하는 메소드
	public int Insert(KeepVo kv) {
		if (this.OpenDB()==false) return -1;
		sql = "insert into keep (uNO, tbNO) values "
			+ "("+kv.getuNO()+", "+kv.getTbNO()+")";
		if( this.isMonitoring ) System.out.println("keepdao : insert \n " + sql);
		int result = this.RunSQL(sql);
		this.CloseDB();
		return result;
	}
	
	// 담기를 취소하는 메소드
	public int Delete(KeepVo kv) {
		if (this.OpenDB()==false) return -1;
		sql = "delete from keep where uNO = "+kv.getuNO()+" and tbNO = "+kv.getTbNO();
		if( this.isMonitoring ) System.out.println("keepdao : delete \n " + sql);
		int result = this.RunSQL(sql);
		this.CloseQuery();
		this.CloseDB();
		return result;
	}
	
	// 내가 담았었는지 확인하는 메소드
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
	
	
	
	// 생성된 리스트의 개수를 얻는 메소드
	public int GetListSize() {
		if (this.klist==null) return 0;
		else return this.klist.size();
	}
	
	// 리스트에서 인덱스로 게시글 정보를 얻는 메소드
	public KeepVo GetItem(int index) {
		if (this.klist == null) return null;
		else return this.klist.get(index);
	}
	
	// 생성된 리스트 전체를 반환하는 메소드
	public ArrayList<KeepVo> GetListAll() {
		return this.klist;
	}

	
	
	
	// 내가 담은 관광지만 출력하는 메소드 =================== by.윤진주
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


	// 담은 여행지를 삭제하는 메소드 =================== by.윤진주
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
