package vo;

import javax.servlet.http.HttpServletRequest;

public class SearchVo {

	private String division;		// ������ : �ֽż�(new), ��õ��(like)
	private String type;			// ����¡ : ����(D1), �ĵ���(D2), ü��(D3), �޽�(D4)
	private String no;				// �Խñ� �ѹ�
	private String code;			// �����ڵ�
	
	public SearchVo()
	{
		// �⺻ ���� : �ֽż�(new)
		this.division = "new";
		this.setDivision(null);
		
		// �⺻ ī�װ� : ����(D1)
		this.type = "D1";
		this.setDivision(null);
		
		// ����� �� �������� URL Ŭ�� �� �Ķ��Ÿ �ڵ� ��
		this.code = null;
		this.setDivision(null);
	}
	
	// request�� ���ڷ� �޴� ������
	public SearchVo(HttpServletRequest request)
	{
		this();
		// �Ķ��Ÿ ��
		// ī�װ�
		this.setType(request.getParameter("type"));
		// ����
		this.setDivision(request.getParameter("list"));
		// �ڵ�
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
			// �⺻�� <�ֽż�>
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
			// �⺻�� <����>
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
			// �⺻�� <����>
			this.no = "1";			
		} else
		{
			this.no = type;
		}
	}
	
	public void setCode(String code) {
		if(type == null || type.contentEquals(""))
		{
			// �⺻�� <null>
			this.code = null;			
		} else
		{
			this.code = code;
		}
	}
	
	// <SQL ����> �ڵ�
	public String getuCode()
	{
		String sql = "";
		sql = " '"+ this.getCode() + "' ";
		
		return sql;
	}
	
	// <SQL ����> WHERE ������ AND ~ ����
	public String getWherelist()
	{
		String sql = "";
		if(this.type == null || this.type.equals("D1"))
		{
			// ����
			sql = " T.tagNO = 'D1' "; 
		} else if (this.type.equals("D2")) {
			// �ĵ���
			sql = " T.tagNO = 'D2' "; 
		} else if (this.type.equals("D3")) {
			// ü��
			sql = " T.tagNO = 'D3' "; 
		} else if (this.type.equals("D4")) {
			// �޽�
			sql = " T.tagNO = 'D4' "; 
		}
		return sql;
	}
	
	// <SQL ����> Order by ~ ����
	public String getOrderlist()
	{
		String sql = "";
		if(this.division == null || this.division.equals("new"))
		{
			// �ֽż�
			sql = " Order by cbdate desc "; 
		} else if(this.division.equals("like")) {
			// ��õ��
			sql = " Order by lCnt desc "; 			
		}
		return sql;
	}
	
	/* <�� ������ �ϼ� �� �Ķ��Ÿ �� ������ ��� ����> ���� [��] */
	/* 
	// �� ���� �� ���ĵ� ������ �Խ��ǿ����� �Խñ� ��ȣ�� �̵��� �� ����� (�Ķ��Ÿ)�ּ� ��
	public String getViewLink(String cbNO)
	{
		String link = "";
		link += "&list=" + this.getDivision();
		return link;
	}
	*/
	
	
	// ��õ ���� �󼼺��� ������
	public String getViewLink( String no )
	{	
		String link = "";
		link  = "type="	 + this.getType();
		link += "&no="	 + this.getNo();
		return link;
	}
}

