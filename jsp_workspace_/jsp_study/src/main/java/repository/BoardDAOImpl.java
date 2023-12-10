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
		//실제 DB에 저장 => mybatis / mapper
		//sql.insert(mapperNamespace)
		 int isOk = sql.insert("BoardMapper.add", bvo);
		//insert, update, delete 시 DB가 변경되는 구문은 commit 필요
		 if(isOk > 0) {
			 sql.commit();
		 }
		return isOk;
	}


	@Override
	public List<BoardVO> selectList(PagingVO Pgvo) {
		log.info(">>>> list check3");
		return sql.selectList("BoardMapper.list", Pgvo);
	}


	@Override
	public BoardVO getDetail(int bno) {
		log.info(">>>> detail check3");
		return sql.selectOne("BoardMapper.detail", bno);
	}


	@Override
	public int readCountUpdate(int bno) {
		log.info(">>>> readcount update check3");
		int isOk = sql.update("BoardMapper.read",bno);
		if(isOk>0) {sql.commit();}
		return isOk;
	}


	@Override
	public int update(BoardVO bvo) {
		log.info(">>>>  update check3");
		int isOk = sql.update("BoardMapper.up",bvo);
		if(isOk>0) {sql.commit();}
		return isOk;
	}


	@Override
	public int delete(int bno) {
		log.info(">>>> delete check2");
		int isOk = sql.delete("BoardMapper.del",bno);
		if(isOk>0) {sql.commit();}
		return isOk;
	}


	@Override
	public int total(PagingVO pgvo) {
		log.info(">>>> count check2");
		return sql.selectOne("BoardMapper.count", pgvo);
	}
}
