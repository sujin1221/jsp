package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	private BoardDAO bdao;
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}
	@Override
	public int insert(BoardVO bvo) {
		log.info("insert check 1");
		return bdao.insert(bvo);
	}
	@Override
	public List<BoardVO> list() {
		log.info("list check 1");
		return bdao.list();
	}
	@Override
	public BoardVO detail(int bno) {
		log.info("detail check 1");
		bdao.readCount(bno);
		return bdao.detail(bno);
	}
	@Override
	public int modify(BoardVO bvo) {
		log.info("modify check 1");
		return bdao.modify(bvo);
	}
	@Override
	public int remove(int bno) {
		log.info("remove check 1");
		return bdao.remove(bno);
	}

}
