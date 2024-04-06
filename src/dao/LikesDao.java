package dao;

import java.util.ArrayList;

import dbms.DBManager;
import vo.*;

public class LikesDao extends DBManager {
	private ArrayList<LikesVo> likelist = null;
	private boolean isMonitoring = true;
	String sql = "";

	/*
	// 추천 관리번호(lNO) 조회
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
	

	// 게시글 추천 수 전체 조회
	public String boardInquiry (String cbNO)
	{
		String cnt = "";

		// DB 연결
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
	
	// 추천 관리번호(lNO) 조회 → 생성 및 삭제 메소드
	public String Inquiry(LikesVo lv)
	{	
		// DB 연결
		if (this.OpenDB() == false) return "N";

		// 로그인 유저 회원번호(uNO), 선택 글 번호(cbNO)를 통해 추천 관리번호(lNO) 조회
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
	
	// <추천> 데이터 쿼리 생성
	public boolean Insert(LikesVo lv)
	{
		// DB 연결
		if (this.OpenDB()==false) return false;
		
		// 로그인 유저 회원번호(uNO), 선택 글 번호(cbNO)를 통해 데이터 입력
		sql = " insert into likes (uNO, cbNO) values("+ lv.getuNO() + ", "+ lv.getCbNO() + ") ";
		if (this.isMonitoring) System.out.println("LikesDao :: Insert() /\n" + sql);
		this.RunSQL(sql);
		this.CloseQuery();
		this.CloseDB();
		
		return true;
	}
	
	// <추천> 데이터 쿼리 삭제
	public boolean Delete(LikesVo lv)
	{
		// DB 연결
		if (this.OpenDB()==false) return false;
		
		// 로그인 유저 회원번호(uNO), 선택 글 번호(cbNO)를 통해 데이터 삭제
		sql = " delete from likes where uNO = "+lv.getuNO()+" and cbNO = "+lv.getCbNO()+" ";
		if (this.isMonitoring) System.out.println("LikesDao :: Delete() /\n" + sql);
		
		this.RunSQL(sql);
		this.CloseQuery();
		this.CloseDB();
		
		return true;
	}	


	// 생성된 리스트의 개수를 얻는 메소드
	public int GetListSize() {
		if (this.likelist==null) return 0;
		else return this.likelist.size();
	}
	
	// 리스트에서 인덱스로 게시글 정보를 얻는 메소드
	public LikesVo GetItem(int index) {
		if (this.likelist == null) return null;
		else return this.likelist.get(index);
	}
	
	// 생성된 리스트 전체를 반환하는 메소드
	public ArrayList<LikesVo> GetListAll()
	{
		return this.likelist;
	}


	// 내가 추천한 일정만 출력하는 메소드 =================== by.윤진주
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
	
	
	// 내가 추천한 일정을 취소하는 메소드 =================== by.윤진주
	public boolean Deletemine( String cbNO, String uNO )
	{
		if (this.OpenDB() == false) return false;
		// 게시글 삭제
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
