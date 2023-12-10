package handler;

import domain.PagingVO;

public class PagingHandler {
	//list 하단에 나오는 페이지네이션 핸들링 클래스
	private int startPage; //현재 화면에서 보여줄 시작 페이지네이션번호 => 1/11/21 ...
	private int endPage; // 현재 페이지에서 보여줄 끝 페이지네이션번호 => 10/20/30
	private int realEndPage; //실제 전체리스트의 끝페이지 번호
	private boolean prev, next; //이전, 다음페이지의 존재여부
	
	private int totalCount; //전체 글 수
	private PagingVO pgvo; //현재pageNo / qty = 현재 사용자가 클릭한 번호와 한 화면에 표시되는 개수
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo; //1,10
		//컨트롤러에서 DB조회 후 파라미터로 전송
		this.totalCount = totalCount; //311 =>>
		
		//1~10 / 11~20 / 21~30
		//페이지번호 1~10을 클릭해도 시작은 1, 끝은 10
		// 1=> 10 / 2=> 10 / 3=>10 ... / 11=> 20 ...
		//페이지번호 / 한 화면의 페이지 네이션 수 * 한 화면의 페이지네이션 수
		//1 / 10 => 0.1 => 1=> (올림)1 * 10 => 10
		//2 / 10 => 0.2 => 1=> (올림)1 * 10 => 10
		//11 / 10 => 1.1 =>(올림) 2 = > 2 * 10 = > 20
		//21 / 10 => 2.1 =>(올림) 3 = > 3 * 10 => 30
		
		this.endPage = (int)Math.ceil(pgvo.getPageNo() / (double)pgvo.getQty()) * pgvo.getQty(); 
		
		this.startPage = this.endPage - 9;
		
		//전체 게시글 수 / 한화면에 게시되는 게시글 수
		// 101 //  101 / 10 => 10.1 올림 11페이지
		// 나머지 게시글이 하나라도 있다면 1페이지가 더 생겨야함.
		this.realEndPage = (int)Math.ceil(totalCount / (double)pgvo.getQty());
		
		//realEndPage = 11 / endPage = 20
		//진짜 끝페이지가 endPage와 같지 않을경우 처리
		if(this.realEndPage < this.endPage) {
			this.endPage = this.realEndPage;
		}
		
		
		//이전, 다음 유무
		//startPage 1,11,21
		this.prev = this.startPage >1;
		this.next = this.endPage < this.realEndPage;
		
		
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getRealEndPage() {
		return realEndPage;
	}

	public void setRealEndPage(int realEndPage) {
		this.realEndPage = realEndPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public PagingVO getPgvo() {
		return pgvo;
	}

	public void setPgvo(PagingVO pgvo) {
		this.pgvo = pgvo;
	}

	@Override
	public String toString() {
		return "PagingHandler [startPage=" + startPage + ", endPage=" + endPage + ", realEndPage=" + realEndPage
				+ ", prev=" + prev + ", next=" + next + ", totalCount=" + totalCount + ", pgvo=" + pgvo + "]";
	}
	
	
	
}
