package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.PagingVO;
import domain.boardVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {    
     private static final Logger log = 
		LoggerFactory.getLogger(BoardServiceImpl.class);
     private BoardDAO bdao; //interface로 생성
     
     public BoardServiceImpl() {
    	 bdao = new BoardDAOImpl(); //class로 생성
     }

	@Override
	public int register(boardVO bvo) {
		log.info(">>>>> insert check2");
		return bdao.insert(bvo);
	}

	@Override
	public List<boardVO> getList(PagingVO pgvo) {
		log.info(">>>>> list check2");
		return bdao.selectList(pgvo);
	}

	@Override
	public boardVO getDetail(int bno) {
		log.info(">>>>> detail check 2");
		//detail 체크시 readcount+1
		int isOk = bdao.readCountUpdate(bno);
		return bdao.getDetail(bno);
	}

	@Override
	public int modify(boardVO bvo) {
		log.info(">>>>> modify check 2");
		
		return bdao.update(bvo);
	}

	@Override
	public int remove(int bno) {
		log.info(">>>>> remove check 2");
		return bdao.delete(bno);
	}

	@Override
	public int getTotal(PagingVO pgvo) {
		log.info(">>>>> total check 2");
		return bdao.getTotal(pgvo);
	}
}
