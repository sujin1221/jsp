package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	private SqlSession sql;
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(BoardVO bvo) {
		log.info("insert check 3");
		int isOk = sql.insert("BoardMapper.add", bvo);
		if(isOk>0) {sql.commit();}
		return isOk;
	}

	@Override
	public List<BoardVO> list() {
		log.info("list check 3");
		return sql.selectList("BoardMapper.list");
	}

	@Override
	public BoardVO detail(int bno) {
		log.info("detail check 3");
		return sql.selectOne("BoardMapper.detail", bno);
	}

	@Override
	public int modify(BoardVO bvo) {
		log.info("modify check 3");
		int isOk = sql.update("BoardMapper.modify", bvo);
		if(isOk>0) {sql.commit();}
		return isOk;
	}

	@Override
	public void readCount(int bno) {
		int isOk = sql.update("BoardMapper.read", bno);
		if(isOk>0) {sql.commit();}
	}

	@Override
	public int remove(int bno) {
		int isOk = sql.delete("BoardMapper.remove", bno);
		if(isOk>0) {sql.commit();}
		return isOk;
	}
}
