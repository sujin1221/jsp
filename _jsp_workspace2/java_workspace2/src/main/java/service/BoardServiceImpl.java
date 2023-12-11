package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.BoardController;
import domain.BoardVO;
import domain.PagingVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;
import repository.CommentDAO;
import repository.CommentDAOImpl;

public class BoardServiceImpl implements BoardService {

	//로그 기록 객체 생성
	private static final Logger log = 
			LoggerFactory.getLogger(BoardServiceImpl.class);
	
	private BoardDAO bdao; //interface로 생성
	private CommentDAO cdao;
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl(); // class 로 생성 bdao 구현 객체생성
	}

	@Override
	public int register(BoardVO bvo) {
		log.info(">>>> insert check2");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList(PagingVO Pgvo) {
		log.info(">>>> list check2");
		return bdao.selectList(Pgvo);
	}

	@Override
	public BoardVO getDetail(int bno) {
		log.info(">>>> detail check2");
		int isOK = bdao.readCountUpdate(bno);
		return bdao.getDetail(bno);
	}

	@Override
	public int edit(BoardVO bvo) {
		log.info(">>>> edit check2");
		return bdao.update(bvo);
	}

	@Override
	public int remove(int bno) {
		log.info(">>>> delete check2");
		cdao = new CommentDAOImpl();
		int isOk = cdao.removeAll(bno);
		log.info(">>>delete all {}", isOk);
		return bdao.delete(bno);
	}

	@Override
	public int getTotalcount(PagingVO pgvo) {
	log.info(">>>> total check2");
		return bdao.total(pgvo);
	}


}
