package dao;

import java.util.ArrayList;

import dbms.DBManager;
import vo.TagVo;
import vo.TbVo;

public class TagDao extends DBManager {
	private ArrayList<TagVo> taglist = null;
	String sql = "";
	
	// ��õ ���� �Խñ��� �±� ����� �������� �޼ҵ�
	public ArrayList<TagVo> SelectCbTag(String cbNO) {
		taglist = null;
		if (this.OpenDB() == false) return taglist;
		sql = "select tagNO, (select tagwhat from tag where tagno = cbtag.tagno) as tagwhat from cbtag where cbno = "+ cbNO;
		this.OpenQuery(sql);
		while (this.GetNext()) {
			String tagWhat = this.GetValue("tagWhat");
			String tagNO = this.GetValue("tagno");
			if (taglist == null) taglist = new ArrayList<TagVo>();
			TagVo tagv = new TagVo();
			tagv.setTagNO(tagNO);
			tagv.setTagWhat(tagWhat);
			taglist.add(tagv);
		}
		this.CloseQuery();
		this.CloseDB();
		return taglist;
	}
	
	// ��õ �������� �±� ����� �������� �޼ҵ�
	public ArrayList<TagVo> SelectTbTag(String tbNO) {
		taglist = null;
		if (this.OpenDB() == false) return taglist;
		sql = "select (select tagwhat from tag where tagno = tbtag.tagno) as tagwhat from tbtag where tbno = "+ tbNO;
		this.OpenQuery(sql);
		while (this.GetNext()) {
			String tagWhat = this.GetValue("tagWhat");
			if (taglist == null) taglist = new ArrayList<TagVo>();
			TagVo tagv = new TagVo();
			tagv.setTagWhat(tagWhat);
			taglist.add(tagv);
		}
		this.CloseQuery();
		this.CloseDB();
		return taglist;
	}
	
	
	// �±׸���Ʈ�� ���̸� ��ȯ�ϴ� �޼ҵ�
	public int GetListSize() {
		if (this.taglist==null) return 0;
		else return this.taglist.size();
	}
	
	
	// ������ �±׸� �����ϴ� �޼ҵ�
		public boolean Delete( String cbNO )
		{
			if (this.OpenDB() == false) return false;
			sql  = " delete from cbtag where cbNO = " + cbNO;
			this.RunSQL(sql);
//			System.out.println(sql);
			this.CloseDB();
			return true;
		}
		
		public boolean Insert(String cbNO, String TagNO) {
			if (this.OpenDB() == false) return false;
			sql = "insert into cbtag (cbNO, tagNO) values "
				+ "("+cbNO+", '"+ TagNO +"')";
			this.RunSQL(sql);
//			System.out.println("tagDao :: insert\n" + sql);
			this.CloseDB();
			return true;
		}
		
}
