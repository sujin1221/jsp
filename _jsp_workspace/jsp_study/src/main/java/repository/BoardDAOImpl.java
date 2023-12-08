package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.PagingVO;
import domain.boardVO;
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
	
	public int insert(boardVO bvo) {
		log.info(">>>>> insert check 3");
		//실제 db에 저장 => mybatis / mapper
		//sql.insert(mapperNamespace.id로 인식)
		int isOk = sql.insert("BoardMapper.add", bvo);
		//insert, update, delete시 db가 변경되는 구문은 commit 필요!
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public List<boardVO> selectList(PagingVO pgvo) {
		log.info(">>>>> list check 3");
		return sql.selectList("BoardMapper.list",pgvo);
	}

	@Override
	public int readCountUpdate(int bno) {
		log.info(">>>>> detail readcount update check");
		int isOk = sql.update("BoardMapper.read",bno);
		if(isOk>0) {sql.commit();}
		return isOk;
	}

	@Override
	public boardVO getDetail(int bno) {
		log.info(">>>>> detail check 3");
		return sql.selectOne("BoardMapper.detail",bno);
	}

	@Override
	public int update(boardVO bvo) {
		log.info(">>>>> modify check 3");
		int isOk = sql.update("BoardMapper.up", bvo);
		if(isOk>0) {sql.commit();}
		return isOk;
	}

	@Override
	public int delete(int bno) {
		log.info(">>>>> remove check 3");
		int isOk = sql.delete("BoardMapper.del", bno);
		if(isOk > 0) {sql.commit();}
		return isOk;
	}

	@Override
	public int getTotal(PagingVO pgvo) {
		log.info(">>>>> total check 3");
		return sql.selectOne("BoardMapper.tot", pgvo);
	}

	@Override
	public int deleteAll(int bno) {
		log.info(">>>>> deleteAll check 3");
		int isOk = sql.delete("BoardMapper.delAll", bno);
		if(isOk > 0) {sql.commit();}
		return isOk;
	}

}
