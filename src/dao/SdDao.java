package dao;

import java.util.ArrayList;
import dbms.DBManager;
import vo.*;

public class SdDao extends DBManager  {
	private ArrayList<SdVo> slist;
	private boolean isMonitoring = true;
	private String sql = "";
	
	// 생성된 리스트의 개수를 얻는 메소드
	public int GetListSize() {
		if (this.slist==null) return 0;
		else return this.slist.size();
	}
	
	// 리스트에서 인덱스로 게시글 정보를 얻는 메소드
	public SdVo GetItem(int index) {
		if (this.slist == null) return null;
		else return this.slist.get(index);
	}
	
	// 생성된 리스트 전체를 반환하는 메소드
	public ArrayList<SdVo> GetListAll() {
		return this.slist;
	}
	
	// 스케줄 영역에 필요한 정보 모두 가져오기  =============================== by.윤진주
	public SdVo Read( String cbNO, int sDay )
	{
		SdVo sv = null;
		if (this.OpenDB() == false) return sv;
		sql  = "select tbNO, sDay,sOrder,sTrans,sDistance,sTime, \r\n" + 
				"(select tbTitle from tourboard where tbNO = schedule.tbNO) as tbTitle \r\n" + 
				"from schedule where cbNO = " + cbNO + " AND sDay = " + sDay + " order by sOrder;\r\n";
		if( this.isMonitoring )
		{System.out.println("sdDao :: Read() /\n" + sql);}
		this.OpenQuery(sql);
		// 조회되지 않으면 종료
		if( this.GetNext() == false )	
		{
			this.CloseQuery();
			this.CloseDB();
			return sv;
		}
		
		// sdVo 객체 생성 후 vo에 정보 셋팅
		sv = new SdVo();
		sv.setTbNO(this.GetValue("tbNO"));
		sv.setsDay(this.GetValue("sDay"));
		sv.setsOrder(this.GetValue("sOrder"));
		sv.setsTrans(this.GetValue("sTrans"));
		sv.setsDistance(this.GetValue("sDistance"));
		sv.setsTime(this.GetValue("sTime"));
		sv.setTbTitle(this.GetValue("tbTitle"));
		this.CloseQuery();
		this.CloseDB();
		
		return sv;
	}
	
	
	//  1일차 각 일정정보 가져오기  =============================== by.윤진주
		public SdVo Read( String cbNO, int sDay, int sOrder )
		{
			SdVo sv = null;
			if (this.OpenDB() == false) return sv;
			sql  = "select tbNO, sDay,sOrder,sTrans,sDistance,sTime, \r\n" + 
					"(select tbTitle from tourboard where tbNO = schedule.tbNO) as tbTitle \r\n" + 
					" from schedule "
					+ "where cbNO = " + cbNO + " AND sDay = " + sDay + " AND sOrder = " + sOrder;
			if( this.isMonitoring )
			{System.out.println("sdDao :: Read2() /\n" + sql);}
			this.OpenQuery(sql);
			// 조회되지 않으면 종료
			if( this.GetNext() == false )	
			{
				this.CloseQuery();
				this.CloseDB();
				return sv;
			}
			
			// sdVo 객체 생성 후 vo에 정보 셋팅
			sv = new SdVo();
			sv.setTbNO(this.GetValue("tbNO"));
			sv.setsDay(this.GetValue("sDay"));
			sv.setsOrder(this.GetValue("sOrder"));
			sv.setsTrans(this.GetValue("sTrans"));
			sv.setsDistance(this.GetValue("sDistance"));
			sv.setsTime(this.GetValue("sTime"));
			sv.setTbTitle(this.GetValue("tbTitle"));
			this.CloseQuery();
			this.CloseDB();
			
			return sv;
		}
	
	// 며칠동안 여행하는지 알려주는 메소드====================== by.남동현
	public String MaxDay(String cbNO) 
	{
		String max="";
		if (this.OpenDB() == false) return max;
		sql = "select Max(sDay) as max from schedule where cbNO="+cbNO;
		if( this.isMonitoring )
		{System.out.println("sdDao :: MaxDay() /\n" + sql);}
		this.OpenQuery(sql);
		if (this.GetNext()) {
			max = this.GetValue("max");
			if (max == null) max ="1";
		} else {
			this.CloseQuery();
			this.CloseDB();
			max = "1";
		}
		this.CloseQuery();
		this.CloseDB();
		return max;
	}
	
	public String MaxOrder(String cbNO, int sDay) 
	{
		String max="";
		if (this.OpenDB() == false) return max;
		sql = "select Max(sOrder) as max "
				+ "from schedule where cbNO=" + cbNO +" and sDay=" + sDay;
		if( this.isMonitoring )
		{System.out.println("sdDao :: MaxDay() /\n" + sql);}
		this.OpenQuery(sql);
		if (this.GetNext()) {
			max = this.GetValue("max");
			if (max == null) max ="1";
		} else {
			max = "1";
			this.CloseQuery();
			this.CloseDB();
		}
		this.CloseQuery();
		this.CloseDB();
		return max;
	}
	
	public boolean Insert(SdVo sv) {
		if (this.OpenDB() == false) return false;
		sql = "insert into schedule (cbno, sday, sorder, tbno, strans, sdistance, stime) values "
			+ "('"+sv.getCbNO()+"', '"+sv.getsDay()+"', '"+ sv.getsOrder() +"', '" +sv.getTbNO()+ "', '"+sv.getsTrans()+"', '"+ sv.getsDistance() +"', '" +sv.getsTime()+ "')";
		if (this.isMonitoring) System.out.println("sddao :: insert : "+ sql);
		this.RunSQL(sql);
		this.CloseDB();
		return true;
	}
	
	public boolean delete(String cbNO) {
		if (this.OpenDB() == false) return false;
		sql = "delete from schedule where cbno = "+ cbNO;
		if (this.isMonitoring) System.out.println("sddao :: delete : "+ sql);

		this.RunSQL(sql);
		this.CloseDB();
		return true;
	}
	
	public String GetTbPic(String cbNO) {
		String tbPic = "";
		if (this.OpenDB() == false) return tbPic;
		sql = "select b.tbpic from schedule a inner join tourboard b on a.tbNO = b.tbNO "
			+ "where a.cbNO = "+ cbNO +" and sorder = 1 and sday = 1";
		this.OpenQuery(sql);
		if (GetNext()) {
			tbPic = this.GetValue("tbpic");
		}
		
		this.CloseQuery();
		this.CloseDB();
		
		return tbPic;
	}
}
