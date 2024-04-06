package vo;

import javax.servlet.http.HttpServletRequest;

public class SearchVo {

	private String division;		// 정　렬 : 최신순(new), 추천순(like)
	private String type;			// 페이징 : 관광(D1), 식도락(D2), 체험(D3), 휴식(D4)
	private String no;				// 게시글 넘버
	private String code;			// 인증코드
	
	public SearchVo()
	{
		// 기본 정렬 : 최신순(new)
		this.division = "new";
		this.setDivision(null);
		
		// 기본 카테고리 : 관광(D1)
		this.type = "D1";
		this.setDivision(null);
		
		// 사용자 → 메일인증 URL 클릳 → 파라메타 코드 값
		this.code = null;
		this.setDivision(null);
	}
	
	// request를 인자로 받는 생성자
	public SearchVo(HttpServletRequest request)
	{
		this();
		// 파라메타 값
		// 카테고리
		this.setType(request.getParameter("type"));
		// 정렬
		this.setDivision(request.getParameter("list"));
		// 코드
		this.setCode(request.getParameter("code"));
	}

	// getter
	public String getDivision() {
		return division;
	}
	public String getType() {
		return type;
	}
	public String getNo() {
		return no;
	}
	public String getCode() {
		return code;
	}
	
	// setter
	public void setDivision(String division)
	{
		if(division == null || division.contentEquals(""))
		{
			// 기본값 <최신순>
			this.division = "new";			
		} else
		{
			this.division = division;
		}
	}
	
	public void setType(String type)
	{
		if(type == null || type.contentEquals(""))
		{
			// 기본값 <관광>
			this.type = "D1";			
		} else
		{
			this.type = type;
		}
	}
	
	public void setNo(String no)
	{
		if(no == null || no.contentEquals(""))
		{
			// 기본값 <관광>
			this.no = "1";			
		} else
		{
			this.no = type;
		}
	}
	
	public void setCode(String code) {
		if(type == null || type.contentEquals(""))
		{
			// 기본값 <null>
			this.code = null;			
		} else
		{
			this.code = code;
		}
	}
	
	// <SQL 구문> 코드
	public String getuCode()
	{
		String sql = "";
		sql = " '"+ this.getCode() + "' ";
		
		return sql;
	}
	
	// <SQL 구문> WHERE ··· AND ~ 생성
	public String getWherelist()
	{
		String sql = "";
		if(this.type == null || this.type.equals("D1"))
		{
			// 관광
			sql = " T.tagNO = 'D1' "; 
		} else if (this.type.equals("D2")) {
			// 식도락
			sql = " T.tagNO = 'D2' "; 
		} else if (this.type.equals("D3")) {
			// 체험
			sql = " T.tagNO = 'D3' "; 
		} else if (this.type.equals("D4")) {
			// 휴식
			sql = " T.tagNO = 'D4' "; 
		}
		return sql;
	}
	
	// <SQL 구문> Order by ~ 생성
	public String getOrderlist()
	{
		String sql = "";
		if(this.division == null || this.division.equals("new"))
		{
			// 최신순
			sql = " Order by cbdate desc "; 
		} else if(this.division.equals("like")) {
			// 추천순
			sql = " Order by lCnt desc "; 			
		}
		return sql;
	}
	
	/* <뷰 페이지 완성 후 파라메타 값 보낼때 사용 예정> 보류 [박] */
	/* 
	// 글 선택 시 정렬된 상태의 게시판에서의 게시글 번호로 이동할 때 사용할 (파라메타)주소 값
	public String getViewLink(String cbNO)
	{
		String link = "";
		link += "&list=" + this.getDivision();
		return link;
	}
	*/
	
	
	// 추천 일정 상세보기 페이지
	public String getViewLink( String no )
	{	
		String link = "";
		link  = "type="	 + this.getType();
		link += "&no="	 + this.getNo();
		return link;
	}
}

