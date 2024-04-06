package dao;
import java.util.ArrayList;
import dbms.DBManager;
import vo.*;

public class CbDao extends DBManager {
	
	private boolean isMonitoring = true;
	
	private SearchVo search  = null;
	private String   where   = "";
	private String   orderBy = "";
	
	private ArrayList<CbVo> clist     = null;
	
	
	private String sql = "";
	
	// 기본생성자
	public CbDao() {}
	
	public CbDao(SearchVo search)
	{
		this.search = search;
		
		if(this.search == null)
		{
			this.search = new SearchVo();
		}
		// SQL 구문 WHERE ··· AND ~ 가져오기
		this.where   = this.search.getWherelist();
		// SQL 구문 Order by ~ 가져오기
		this.orderBy = this.search.getOrderlist();
	}
	
	// <board_course> 추천일정 게시판에 글 작성하기
	public String Insert(CbVo cv) {
		String cbNO ="";
		if (this.OpenDB() == false) return cbNO;
		// 추천 일정 게시판에 글 작성하기
		sql = "insert into courseboard "
			+ "(cbtitle, cbperiod, cbnote, cbpublic, uNO) values "
			+ "('"+cv.getCbTitle()+"', '"+cv.getCbPeriod()+"', '"+this._R(cv.getCbNote())+ "', '"+cv.getCbPublic()+"', "+ cv.getuNO() +")";
		if( this.isMonitoring ) System.out.println("CbDao :: Insert() -1- : "+sql);
		this.RunSQL(sql);
		
		// 작성한 게시글 번호 받아오기
		sql = "select last_insert_id() as cbNO";
		if( this.isMonitoring ) System.out.println("CbDao :: Insert() -2- /\n" + sql);
		this.OpenQuery(sql);
		this.GetNext();
		cbNO = this.GetValue("cbNO");
		cv.setCbNO(cbNO);
		
		this.CloseQuery();
		this.CloseDB();
		return cbNO;
	}
	
	// <board_course> 추천일정 게시글 하나 불러오기
	public CbVo SelectOne(String cbNO) {
		CbDao cd = new CbDao();
		CbVo cv = null;
		if (this.OpenDB() == false) return cv;
		sql = "select cbtitle, cbperiod, cbnote, cbpublic from courseboard where cbno = "+cbNO;
		this.OpenQuery(sql);
		if (!GetNext()) {
			this.CloseQuery();
			this.CloseDB();
		}
		cv = new CbVo();
		cv.setCbTitle(this.GetValue("cbtitle"));
		cv.setCbPeriod(this.GetValue("cbperiod"));
		cv.setCbNote(this.GetValue("cbnote"));
		cv.setCbPublic(this.GetValue("cbpublic"));
		
		this.CloseQuery();
		this.CloseDB();
		
		return cv;
	}
	
	// <board_course> 추천일정 게시글을 수정하는 메소드
	public boolean Modify(String cbNO, CbVo cv) {
		if (this.OpenDB() == false) return false;
		sql = "update courseboard set "
			+ "cbtitle = '"+this._R(cv.getCbTitle())+"', "
			+ "cbperiod = '"+cv.getCbPeriod()+"', "
			+ "cbnote = '"+this._R(cv.getCbNote())+"', "
			+ "cbpublic = '"+cv.getCbPublic()+"'"
			+ "where cbno =" + cbNO;
		if( this.isMonitoring ) System.out.println(sql);
		this.RunSQL(sql);
		
		TagDao tagd = new TagDao();
		tagd.Delete(cbNO);
		SdDao sd = new SdDao();
		sd.delete(cbNO);
		
		this.CloseDB();
		return true;
	}
			
	
	// <board_course> 추천일정 전체 리스트 불러오기
	public boolean SelectAll()
	{
		this.sql = "";				// SQL 초기화
		   clist = null;			// 리스트 값 초기화
	
		if (this.OpenDB() == false) return false;
		
		this.sql = " select C.cbno, C.cbtitle, C.cbDate, C.cbPublic, "
				 + " (select count(*) from likes L where L.cbNO = C.cbNO) AS lCnt, "
				 + " count(*) AS TCnt "
				 + " from courseboard C INNER JOIN cbTAG T on C.cbNO = T.cbNO "
				 + " where C.cbPublic = 'Y' AND " + this.where + " "
				 + " Group By C.cbNO "
				 + this.orderBy + " "; 
			
			/*
	 		<SQL> 정렬 값
			// 최신순
			+ "order by cbdate desc ";
			
			// 추천순
			+ "order by lCnt desc ";
			 */
			
		if( this.isMonitoring ) { System.out.println( "cbDao :: SelectAll() / \n"+sql); }	
		this.OpenQuery(sql);
		
		while (this.GetNext())
		{
			String cbNO    = this.GetValue("cbNO");
			String cbTitle = this.GetValue("cbTitle");
			String lCnt    = this.GetValue("lCnt");
			
			if (clist == null) clist = new ArrayList<CbVo>();
		
			CbVo cv = new CbVo();
			cv.setCbNO   (cbNO);
			cv.setCbTitle(cbTitle);
			cv.setlCnt   (lCnt);
			clist.add(cv);
		}
		
		this.CloseQuery();
		this.CloseDB();
		
		
		if( this.clist != null )
		{
			for(CbVo item : this.clist)
			{
				item.setUserLike(this.likeUserList(item.getCbNO()));
			}
		}
		return true;
	}
	
	// <board_course> 각 게시글의 추천인 목록 메소드
	public ArrayList<String> likeUserList(String cbNO)
	{
		ArrayList<String> result = null;
		if (this.OpenDB() == false) return result;
		
		sql = " select uNO from likes where cbNO = " +cbNO+ "";
		if( this.isMonitoring ) System.out.println("cbDao :: likeUserList() /\n" + sql);
		this.OpenQuery(sql);
		
		while(this.GetNext())
		{
			if(result == null)
			{
				result = new ArrayList<String>(); 
			} 
			String uNO = this.GetValue("uNO");
			result.add(uNO);
		}
		this.CloseQuery();
		this.CloseDB();
		
		return result;
	}

	// 생성된 리스트의 개수를 얻는 메소드
	public int GetListSize() {
		if (this.clist==null) return 0;
		else return this.clist.size();
	}

	
	// 리스트에서 인덱스로 게시글 정보를 얻는 메소드
	public CbVo GetItem(int index) {
		if (this.clist == null) return null;
		else return this.clist.get(index);
	}
	
	// 생성된 리스트 전체를 반환하는 메소드
	public ArrayList<CbVo> GetListAll() {
		return this.clist;
	}
	
	
	// 내가 쓴 일정 출력하는 메소드 =================== by.윤진주
	public boolean SelectMine(String uNO) {
		clist = null;
		if (this.OpenDB() == false) return false;
		sql = " select cbno, cbtitle, cbdate "
			+ " from courseboard "
			+ " where uNO ="+ uNO + " "
			+ " order by cbdate desc ";
		
		this.OpenQuery(sql);
		if( this.isMonitoring )
		{System.out.println("cbDao :: SelectMine() /\n" + sql);}
		while (this.GetNext())
		{
			String cbNO    = this.GetValue("cbNO");
			String cbTitle = this.GetValue("cbTitle");
			if (clist == null) clist = new ArrayList<CbVo>();
			
			CbVo cv = new CbVo();
			
			cv.setCbNO(cbNO);
			cv.setCbTitle(cbTitle);
			clist.add(cv);
		}
		this.CloseQuery();
		this.CloseDB();
		
		return true;
	}


	// 내가 쓴 일정을 삭제하는 메소드 ===================== by.윤진주
	public boolean Deletemine( String cbNO, String uNO )
	{
		if (this.OpenDB() == false) return false;
		
		// 태그 삭제
		TagDao td = new TagDao();
		td.Delete(cbNO);
		
		// 게시글 삭제
		sql = "delete from likes where cbNO = " + cbNO;
		this.RunSQL(sql);
		
		sql = "delete from schedule where cbno = " + cbNO;
		this.RunSQL(sql);
		
		sql  = " delete from courseboard where cbNO = " + cbNO + " AND uNO =" + uNO;
		this.RunSQL(sql);
		if( this.isMonitoring )
		{System.out.println("cbDao :: Deletemine() /\n" + sql);}
		this.CloseDB();
		return true;
	}

	
	//  글 번호로 정보 받기 ===================================== by.윤진주
	public CbVo Read( String cbNO )
	{
		CbVo cv = null;
		if (this.OpenDB() == false) return cv;
		sql  = "select uNO,cbTitle,cbPeriod,cbNote,cbDate, " + 
				"(select uname from users where uNO = (select uNO FROM courseboard where cbNO = " + cbNO + ")) as uname, \r\n" + 
				"(select count(a.uNO) as lCnt from likes a join courseboard b on a.cbNO = b.cbNO where b.cbNO = " + cbNO + " ) as lCnt, \r\n" + 
				"(select tagWhat from tag where tagNO = (select tagNO from cbtag where substr(tagNO,1,1) ='B' and cbNO = " + cbNO + " )) as person,\r\n" + 
				"(select tagWhat from tag where tagNO = (select tagNO from cbtag where substr(tagNO,1,1) ='D' and cbNO = " + cbNO + " )) as purpose, \r\n" + 
				"(select tagWhat from tag where tagNO = (select tagNO from cbtag where substr(tagNO,1,1) ='A' and cbNO = " + cbNO + " )) as age \r\n" + 
				"from courseboard where cbNO = " + cbNO;
		if( this.isMonitoring )
		{System.out.println("cbDao :: Read() /\n" + sql);}
		this.OpenQuery(sql);
		// 조회되지 않으면 종료
		if( this.GetNext() == false )	
		{
			this.CloseQuery();
			this.CloseDB();
			return cv;
		}
		
		// CbVo 객체 생성 후 vo에 정보 셋팅
		cv = new CbVo();
		cv.setuNO(this.GetValue("uNO"));
		cv.setCbTitle(this.GetValue("cbTitle"));
		cv.setCbPeriod(this.GetValue("cbPeriod"));
		cv.setCbNote(this.GetValue("cbNote"));
		cv.setCbDate(this.GetValue("cbDate"));
		cv.setuName(this.GetValue("uName"));
		cv.setlCnt(this.GetValue("lCnt"));
		cv.setPerson(this.GetValue("person"));
		cv.setPurpose(this.GetValue("purpose"));
		cv.setAge(this.GetValue("age"));

		this.CloseQuery();
		this.CloseDB();

		return cv;
	}
	
	//  글 번호로 정보 받기 ===================================== by.윤진주
	public CbVo Read( String cbNO , String uNO)
	{
		CbVo cv = null;
		if (this.OpenDB() == false) return cv;
		sql  = "select uNO,cbTitle,cbPeriod,cbNote,cbDate, " + 
				"(select uNO from likes where uNO = "+ uNO +" AND cbNO = " + cbNO + ") as uLike, " + 
				"(select uname from users where uNO = (select uNO FROM courseboard where cbNO = " + cbNO + ")) as uname, \r\n" + 
				"(select count(a.uNO) as lCnt from likes a join courseboard b on a.cbNO = b.cbNO where b.cbNO = " + cbNO + " ) as lCnt, \r\n" + 
				"(select tagWhat from tag where tagNO = (select tagNO from cbtag where substr(tagNO,1,1) ='B' and cbNO = " + cbNO + " )) as person,\r\n" + 
				"(select tagWhat from tag where tagNO = (select tagNO from cbtag where substr(tagNO,1,1) ='D' and cbNO = " + cbNO + " )) as purpose, \r\n" + 
				"(select tagWhat from tag where tagNO = (select tagNO from cbtag where substr(tagNO,1,1) ='A' and cbNO = " + cbNO + " )) as age \r\n" + 
				"from courseboard where cbNO = " + cbNO;
		if( this.isMonitoring )
		{System.out.println("cbDao :: Read() /\n" + sql);}
		this.OpenQuery(sql);
		// 조회되지 않으면 종료
		if( this.GetNext() == false )	
		{
			this.CloseQuery();
			this.CloseDB();
			return cv;
		}
		
		// CbVo 객체 생성 후 vo에 정보 셋팅
		cv = new CbVo();
		cv.setuNO(this.GetValue("uNO"));
		cv.setCbTitle(this.GetValue("cbTitle"));
		cv.setCbPeriod(this.GetValue("cbPeriod"));
		cv.setCbNote(this.GetValue("cbNote"));
		cv.setCbDate(this.GetValue("cbDate"));
		cv.setuName(this.GetValue("uName"));
		cv.setlCnt(this.GetValue("lCnt"));
		cv.setPerson(this.GetValue("person"));
		cv.setPurpose(this.GetValue("purpose"));
		cv.setAge(this.GetValue("age"));
		cv.setuLike(this.GetValue("uLike"));
		
		this.CloseQuery();
		this.CloseDB();
		
		return cv;
	}
	
	// 추천수 알아내기 
	public boolean SelectUserlike(String cbNO, String uNO) {
		clist = null;
		if (this.OpenDB() == false) return false;
		sql = " select uNO from likes where cbNO = " + cbNO + " and uNO=" + uNO;
		this.OpenQuery(sql);
		if( this.isMonitoring )
		{System.out.println("cbDao :: SelectUserlike() /\n" + sql);}
		
		// CbVo 객체 생성 후 vo에 정보 셋팅
		CbVo cv = new CbVo();
		cv.setuLike(this.GetValue("userLike"));
		
		this.CloseQuery();
		this.CloseDB();
		
		return true;
	}

	

}
