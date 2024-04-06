package vo;

public class TagVo {
	// 필드
	private String tagNO;		// 태그 관리 번호 : A1~A4 / B1~B4 / C1~C4 / D1~C4
	private String tagWhat;		// 태그 구분 : 20대 미만 ~ 60대 이상 / 혼자 ~ 단체 / 당일 ~ 일주일 이상 / 관광 식도락 체험 휴식 
	
	// 게터
	public String getTagNO() {
		return tagNO;
	}
	public String getTagWhat() {
		return tagWhat;
	}
	
	// 세터
	public void setTagNO(String tagNO) {
		this.tagNO = tagNO;
	}
	public void setTagWhat(String tagWhat) {
		this.tagWhat = tagWhat;
	}
}
