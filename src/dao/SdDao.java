package dao;

import java.util.ArrayList;
import dbms.DBManager;
import vo.*;

public class SdDao extends DBManager  {
	private ArrayList<SdVo> slist;
	private boolean isMonitoring = true;
	private String sql = "";
	
	// ������ ����Ʈ�� ������ ��� �޼ҵ�
	public int GetListSize() {
		if (this.slist==null) return 0;
		else return this.slist.size();
	}
	
	// ����Ʈ���� �ε����� �Խñ� ������ ��� �޼ҵ�
	public SdVo GetItem(int index) {
		if (this.slist == null) return null;
		else return this.slist.get(index);
	}
	
	// ������ ����Ʈ ��ü�� ��ȯ�ϴ� �޼ҵ�
	public ArrayList<SdVo> GetListAll() {
		return this.slist;
	}
	
	// ������ ������ �ʿ��� ���� ��� ��������  =============================== by.������
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
		// ��ȸ���� ������ ����
		if( this.GetNext() == false )	
		{
			this.CloseQuery();
			this.CloseDB();
			return sv;
		}
		
		// sdVo ��ü ���� �� vo�� ���� ����
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
	
	
	//  1���� �� �������� ��������  =============================== by.������
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
			// ��ȸ���� ������ ����
			if( this.GetNext() == false )	
			{
				this.CloseQuery();
				this.CloseDB();
				return sv;
			}
			
			// sdVo ��ü ���� �� vo�� ���� ����
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
	
	// ��ĥ���� �����ϴ��� �˷��ִ� �޼ҵ�====================== by.������
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
