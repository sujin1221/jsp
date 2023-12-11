package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {

	private static final Logger log = 
			LoggerFactory.getLogger(BoardDAOImpl.class);
	
	//DB연결
	private SqlSession sql;
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(BoardVO bvo) {
		log.info(">>>> insert check3");
		 int isOk = sql.insert("boardMapper.add", bvo);
		 if(isOk > 0) {
			 sql.commit();
		 }
		return isOk;
	}

	@Override
	public List<BoardVO> selectList(PagingVO Pgvo) {
		log.info(">>>> list check3");
		return sql.selectList("boardMapper.list", Pgvo);
	}

	@Override
	public BoardVO getDetail(int bno) {
		log.info(">>>> detail check3");
		return sql.selectOne("boardMapper.detail", bno);
	}


	@Override
	public int readCountUpdate(int bno) {
		log.info(">>>> readcount update check3");
		int isOk = sql.update("boardMapper.read",bno);
		if(isOk>0) {sql.commit();}
		return isOk;
	}

	@Override
	public int update(BoardVO bvo) {
		log.info(">>>>  update check3");
		int isOk = sql.update("boardMapper.up",bvo);
		if(isOk>0) {sql.commit();}
		return isOk;
	}

	@Override
	public int delete(int bno) {
		log.info(">>>> delete check2");
		int isOk = sql.delete("boardMapper.del",bno);
		if(isOk>0) {sql.commit();}
		return isOk;
	}

	@Override
	public int total(PagingVO pgvo) {
		log.info(">>>> count check2");
		return sql.selectOne("boardMapper.count", pgvo);
	}
}
