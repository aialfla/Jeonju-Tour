package dao;

import java.util.ArrayList;

import dbms.DBManager;
import vo.*;

public class ReplyDao extends DBManager {
	private ArrayList<ReplyVo> rlist;
	private boolean isMonitoring = true;
	private String sql = "";
	
	// 댓글을 전부 불러오는 메소드
	public ArrayList<ReplyVo> SelectAll(String tbNO) {
		rlist = null;
		ReplyVo rv = null;
		if (this.OpenDB() == false) return rlist;
		sql = "select uno, rno, rnote, substr(rdate,3,8) as rdate, "
			+ "(select uname from users where uNO = reply.uNO) as uName "
			+ "from reply "
			+ "where tbNO = " + tbNO +" "
			+ "order by rdate";
		if (this.isMonitoring) System.out.println("replydao : selectall : \n"+sql);
		this.OpenQuery(sql);
		while (this.GetNext()) {
			String uNO = this.GetValue("uno");
			String rNO = this.GetValue("rno");
			String rNote = this.GetValue("rnote");
			String rDate = this.GetValue("rdate");
			String uName = this.GetValue("uName");
			if (rlist == null) rlist = new ArrayList<ReplyVo>();
			rv = new ReplyVo();
			rv.setuNO(uNO);
			rv.setrNO(rNO);
			rv.setrNote(rNote);
			rv.setrDate(rDate);
			rv.setuName(uName);
			rlist.add(rv);
		}
		this.CloseQuery();
		this.CloseDB();
		return rlist;
	}
	
	
	// 관광지목록의 자기가 작성한 댓글을 전부 불러오는 메소드
	public ArrayList<ReplyVo> SelectViewMine(String tbNO, String uNO) {
		rlist = null;
		ReplyVo rv = null;
		if (this.OpenDB() == false) return rlist;
		sql = "select uno, rno, rnote, substr(rdate,3,8) as rdate, "
			+ "(select uname from users where uNO = reply.uNO) as uName "
			+ "from reply "
			+ "where tbNO = " + tbNO +" and uNO =" + uNO + " "
			+ "order by rdate desc";
		if (this.isMonitoring) System.out.println("Replydao : SelectViewMine" + sql);
		this.OpenQuery(sql);
		while (this.GetNext()) {
			String rNO = this.GetValue("rno");
			String rNote = this.GetValue("rnote");
			String rDate = this.GetValue("rdate");
			String uName = this.GetValue("uName");
			if (rlist == null) rlist = new ArrayList<ReplyVo>();
			rv = new ReplyVo();
			rv.setrNO(rNO);
			rv.setuNO(uNO);
			rv.setrNote(rNote);
			rv.setrDate(rDate);
			rv.setuName(uName);
			rlist.add(rv);
		}
		this.CloseQuery();
		this.CloseDB();
		return rlist;
	}
	
	// 댓글을 입력하는 메소드
	public boolean Insert(ReplyVo rv) {
		if (this.OpenDB() == false) return false;
		String sql = "";
		sql += "insert into reply ";
		sql += "(uno, tbno, rdate, rnote) values (";
		sql += ""+ rv.getuNO() +", ";
		sql += ""+ rv.getTbNO() +", ";
		sql += "now(), ";
		sql += "'"+ this._R(rv.getrNote()) +"')";
		if (this.isMonitoring) System.out.println("ReplyDao :: Insert() -1- /\n" + sql);
		this.RunSQL(sql);
		
		// 등록된 댓글의 번호를 얻는다
		sql = "select last_insert_id() as rNO";
		this.OpenQuery(sql);
		this.GetNext();
		String rNO = this.GetValue("rNO");
		if (this.isMonitoring) System.out.println("ReplyDao :: Insert() -2- /\n" + sql);
		// 등록된 댓글 번호로 vo를 완성한다
		rv.setrNO(rNO);
		this.CloseQuery();
		this.CloseDB();
		return true;
	}
	
	// 댓글을 삭제하는 메소드
	public boolean Delete(String rNO, String uNO) {
		if (this.OpenDB() == false) return false;
		String sql = "";
		sql += "delete from reply where rNO = "+rNO +" and uNO = "+ uNO;
		if (this.isMonitoring) System.out.println("ReplyDao :: Delete() /\n" + sql);
		if (this.RunSQL(sql) < 1) {
			this.CloseDB();
			if (this.isMonitoring) System.out.println("댓글을 삭제하지 못했습니다");
			return false;
		}
		this.CloseDB();
		return true;
	}
	
	
	// 생성된 리스트의 개수를 얻는 메소드
	public int GetListSize() {
		if (this.rlist==null) return 0;
		else return this.rlist.size();
	}
	
	// 리스트에서 인덱스로 게시글 정보를 얻는 메소드
	public ReplyVo GetItem(int index) {
		if (this.rlist == null) return null;
		else return this.rlist.get(index);
	}
	
	// 생성된 리스트 전체를 반환하는 메소드
	public ArrayList<ReplyVo> GetListAll() {
		return this.rlist;
	}
	
	
	// 내가 쓴 리뷰를 불러오는 메소드 ===================== by.윤진주
	public boolean Selectmine(String uNO) {
		rlist = null;
		if (this.OpenDB() == false) return false;
		sql = "select rno, rnote, substr(rdate,3,8) as rdate, b.tbTitle, a.tbNO "
				+ "from reply a inner join tourboard b "
				+ "on a.tbNO = b.tbNO where uNO =" + uNO + " "
				+ "order by rno desc";
		this.OpenQuery(sql);
		if( this.isMonitoring )
		{System.out.println("ReplyDao :: Selectmine() /\n" + sql);}
		while (this.GetNext()) {
			String rNO = this.GetValue("rNO");
			String rNote = this.GetValue("rNote");
			String rDate = this.GetValue("rDate");
			String tbTitle = this.GetValue("tbTitle");
			if (rlist == null) rlist = new ArrayList<ReplyVo>();
			ReplyVo rv = new ReplyVo();
			rv.setrNO(rNO);
			rv.setrNote(rNote);
			rv.setrDate(rDate);
			rv.setTbTitle(tbTitle);
			rv.setTbNO(this.GetValue("tbNO"));
			rlist.add(rv);
		}
		this.CloseQuery();
		this.CloseDB();
		return true;
	}

	
	// 내가 쓴 리뷰를 삭제하는 메소드 ===================== by.윤진주
	public boolean Deletemine( String rNO, String uNO )
	{
		if (this.OpenDB() == false) return false;
		sql  = " delete from reply where rNO = " + rNO + " AND uNO =" + uNO;
		this.RunSQL(sql);
		if( this.isMonitoring )
		{System.out.println("ReplyDao :: Deletemine() /\n" + sql);}
		this.CloseDB();
		return true;
	}
}
