package dao;

import java.util.ArrayList;

import dbms.DBManager;
import vo.*;

public class TbDao extends DBManager {
	private String sql = "";
	public String tags ="";
	ArrayList<TbVo> tlist = null;
	
	// ������ ��ȣ�� ���õ� ��õ �������� �ҷ����� �޼ҵ�
	public TbVo SelectOne(String tbNO) {
		TbVo tv = null;
		if (this.OpenDB() == false) return tv;
		sql = "select tbtitle, tbaddr, tbtel, tbtime, tbdetail, tbpic from tourboard "
				+ "where tbNO = "+ tbNO;
		this.OpenQuery(sql);
		if (!this.GetNext()) {
			CloseQuery();
			CloseDB();
			return tv;
		} else {
			String tbTitle = this.GetValue("tbtitle");
			String tbAddr = this.GetValue("tbaddr");
			String tbTel = this.GetValue("tbtel");
			String tbTime = this.GetValue("tbtime");
			String tbDetail = this.GetValue("tbdetail");
			String tbPic = this.GetValue("tbpic");
			tv = new TbVo();
			tv.setTbNO(tbNO);
			tv.setTbTitle(tbTitle);
			tv.setTbAddr(tbAddr);
			tv.setTbTel(tbTel);
			tv.setTbTime(tbTime);
			tv.setTbDetail(tbDetail);
			tv.setTbPic(tbPic);
		}
		this.CloseQuery();
		this.CloseDB();
		return tv;
	}
	
	// ��õ ������ ��Ͽ��� ī�װ��� ���͸��ؼ� �ҷ����� �޼ҵ�
	public boolean SelectAll(String where) {
		tlist = null;
		if (this.OpenDB() == false) return false;
		sql = "select tbno, tbtitle, tbaddr, tbpic from tourboard "
			+ "where tbCategory = '"+where+"'";
		this.OpenQuery(sql);
		while (this.GetNext()) {
			String tbTitle = this.GetValue("tbtitle");
			String tbAddr = this.GetValue("tbaddr");
			String tbNO = this.GetValue("tbno");
			String tbPic = this.GetValue("tbpic");
			if (tlist == null) tlist = new ArrayList<TbVo>();
			TbVo tv = new TbVo();
			tv.setTbNO(tbNO);
			tv.setTbTitle(tbTitle);
			tv.setTbAddr(tbAddr);
			tv.setTbPic(tbPic);
			tlist.add(tv);
		}
		this.CloseQuery();
		this.CloseDB();
		return true;
	}
	
	// ��õ ������ ��Ͽ��� ī�װ��� ���͸��ؼ� �ҷ����� �޼ҵ�
	public boolean SelectKeepAll(String uNO, String where) {
		tlist = null;
		if (this.OpenDB() == false) return false;
		sql = "select a.tbno, tbtitle, tbaddr, tbpic from tourboard a inner join keep b on a.tbno = b.tbno "
				+ "where uNO = " + uNO + " and tbCategory = '"+where+"'";
		System.out.println(sql);
		this.OpenQuery(sql);
		while (this.GetNext()) {
			String tbTitle = this.GetValue("tbtitle");
			String tbAddr = this.GetValue("tbaddr");
			String tbNO = this.GetValue("tbno");
			String tbPic = this.GetValue("tbpic");
			if (tlist == null) tlist = new ArrayList<TbVo>();
			TbVo tv = new TbVo();
			tv.setTbNO(tbNO);
			tv.setTbTitle(tbTitle);
			tv.setTbAddr(tbAddr);
			tv.setTbPic(tbPic);
			tlist.add(tv);
		}
		this.CloseQuery();
		this.CloseDB();
		return true;
	}
	
	// ��õ ������ ��Ͽ��� ������ �±׸� ���� ����� ī�װ��� ���͸��ؼ� �ҷ����� �޼ҵ�
	public boolean SelectAll(ArrayList<TagVo> taglist, String where) {
		tlist = null;
		
		if (taglist != null) {
			tags += " and (";
			for (TagVo tagv : taglist) {
				tags+= "tagno = '" + tagv.getTagNO() + "' or ";
			}
			tags = tags.substring(0,tags.length()-4);
			tags += ")";
		}
		
		if (this.OpenDB() == false) return false;
		sql = "select distinct a.tbno, tbtitle, tbaddr, tbpic from tourboard a "
				+ "inner join tbtag b on a.tbno = b.tbno "
				+ "where tbCategory = '"+where +"'"+ tags;
		System.out.println(sql);
		this.OpenQuery(sql);
		while (this.GetNext()) {
			String tbTitle = this.GetValue("tbtitle");
			String tbAddr = this.GetValue("tbaddr");
			String tbNO = this.GetValue("tbno");
			String tbPic = this.GetValue("tbpic");
			if (tlist == null) tlist = new ArrayList<TbVo>();
			TbVo tv = new TbVo();
			tv.setTbNO(tbNO);
			tv.setTbTitle(tbTitle);
			tv.setTbAddr(tbAddr);
			tv.setTbPic(tbPic);
			tlist.add(tv);
		}
		this.CloseQuery();
		this.CloseDB();
		return true;
	}
	
	// ������ ����Ʈ�� ������ ��� �޼ҵ�
	public int GetListSize() {
		if (this.tlist==null) return 0;
		else return this.tlist.size();
	}
	
	// ����Ʈ���� �ε����� �Խñ� ������ ��� �޼ҵ�
	public TbVo GetItem(int index) {
		if (this.tlist == null) return null;
		else return this.tlist.get(index);
	}
	
	// ������ ����Ʈ ��ü�� ��ȯ�ϴ� �޼ҵ�
	public ArrayList<TbVo> GetListAll() {
		return this.tlist;
	}
	
	// �±� �Ķ���͸� �ۼ��ϴ� �޼ҵ�
	public String GetTagParam(ArrayList<TagVo> taglist) {
		String tag = ""; 				// �±׸���Ʈ�� ���ڿ��� ��������
		String result =""; 				// ���ϰ����� �� ����� ���ڿ�
		for (TagVo tagv : taglist) { 	// �±׸���Ʈ�� �ִ� tagVO�� ����
			tag += tagv.getTagNO(); 	// tag�� ���ڿ��� ������ ����
		}
		while(tag.length() != 0) {		// �±� ���ڿ��� ���̰� 0�� �ɶ� ���� ����ġ���� �ݺ��Ұ��ε�
			switch(tag.charAt(0)) {		// �±׹��ڿ��� �� �ձ��ڿ� ����
			case 'A':
				result+="&age="+tag.charAt(0)+tag.charAt(1);	// result�� �α��ڸ� �߶� ����
				tag = tag.substring(2, tag.length());
				break;
			case 'B':
				result+="&head="+tag.charAt(0)+tag.charAt(1);
				tag = tag.substring(2, tag.length());
				break;
			case 'C':
				result+="&period="+tag.charAt(0)+tag.charAt(1);
				tag = tag.substring(2, tag.length());
				break;
			case 'D':
				result+="&prps="+tag.charAt(0)+tag.charAt(1);
				tag = tag.substring(2, tag.length());
				break;
			default :
				tag = tag.substring(2, tag.length());
				break;
			}
		}
		return result;
	}
}
