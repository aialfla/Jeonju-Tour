package dao;

import java.util.ArrayList;

import dbms.DBManager;
import vo.*;

public class TbDao extends DBManager {
	private String sql = "";
	public String tags ="";
	ArrayList<TbVo> tlist = null;
	
	// 관광지 번호로 선택된 추천 관광지를 불러오는 메소드
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
	
	// 추천 관광지 목록에서 카테고리로 필터링해서 불러오는 메소드
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
	
	// 추천 관광지 목록에서 카테고리로 필터링해서 불러오는 메소드
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
	
	// 추천 관광지 목록에서 포함한 태그를 가진 결과를 카테고리로 필터링해서 불러오는 메소드
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
	
	// 생성된 리스트의 개수를 얻는 메소드
	public int GetListSize() {
		if (this.tlist==null) return 0;
		else return this.tlist.size();
	}
	
	// 리스트에서 인덱스로 게시글 정보를 얻는 메소드
	public TbVo GetItem(int index) {
		if (this.tlist == null) return null;
		else return this.tlist.get(index);
	}
	
	// 생성된 리스트 전체를 반환하는 메소드
	public ArrayList<TbVo> GetListAll() {
		return this.tlist;
	}
	
	// 태그 파라미터를 작성하는 메소드
	public String GetTagParam(ArrayList<TagVo> taglist) {
		String tag = ""; 				// 태그리스트를 문자열로 담을것임
		String result =""; 				// 리턴값으로 줄 결과물 문자열
		for (TagVo tagv : taglist) { 	// 태그리스트에 있는 tagVO를 전부
			tag += tagv.getTagNO(); 	// tag에 문자열로 줄줄이 달음
		}
		while(tag.length() != 0) {		// 태그 문자열의 길이가 0이 될때 까지 스위치문을 반복할것인데
			switch(tag.charAt(0)) {		// 태그문자열의 맨 앞글자에 따라서
			case 'A':
				result+="&age="+tag.charAt(0)+tag.charAt(1);	// result에 두글자를 잘라서 넣음
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
