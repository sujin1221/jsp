package domain;

public class PagingVO {
	
	private int pageNo; //화면에 출력되는 페이지네이션 번호
	private int qty;  //한 페이지에 보여줄 게시글 수 (10개)

	
	//검색 멤버변수 추가
	private String type; //검색대상
	private String keyword;//검색어
	
	
	public PagingVO() { //처음리스트로 들어갔을 때 규칙
		//페이지네이션을 클릭하기 전 기본값 설정
		this.pageNo=1;
		this.qty=10;
		
	}
	
	//페이지네이션을 클릭하면 설정되는 값
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	public PagingVO(int pageNo, int qty, String type, String keyword) {
		this.pageNo = pageNo;
		this.qty = qty;
		this.type = type;
		this.keyword = keyword;
	}
	
	
	public int getPageStart() { //db에서 사용되는 시작번지 0번지부터 시작
		//1 => 0 / 2=> 10 / 3=> 20
		return (pageNo-1)*qty;
		
	}
	
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}

	
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "PagingVO [pageNo=" + pageNo + ", qty=" + qty + ", type=" + type + ", keyword=" + keyword + "]";
	}
	
	
	

	
}
