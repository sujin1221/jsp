package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import orm.DatabaseBuilder;
import service.MemberServiceImpl;

public class CommentDAOImpl implements CommentDAO {
	private static final Logger log = LoggerFactory.getLogger(CommentDAOImpl.class);
	
	private SqlSession sql;
	private int isOk;
	
	
	public CommentDAOImpl() {
		new DatabaseBuilder();
	sql = DatabaseBuilder.getFactory().openSession();
	}


	@Override
	public int insert(CommentVO cvo) {
		log.info(">>>>post check 3");
		isOk = sql.insert("commentMapper.post",cvo);
		if(isOk>0) sql.commit();
		return isOk;
	}


	@Override
	public List<CommentVO> selectAll(int bno) {
		log.info(">>>>list check 3");
		return sql.selectList("commentMapper.list",bno);
	}


	@Override
	public int remove(int cno) {
		log.info(">>>>remove check 3");
		int isOk = sql.delete("commentMapper.delete",cno);
		if(isOk>0) sql.commit();
		return isOk;
	}


	@Override
	public int update(CommentVO cvo) {
		log.info(">>>>update check 3");
		int isOk = sql.update("commentMapper.update",cvo);
		if(isOk>0) sql.commit();
		return isOk;
	}


	@Override
	public int removeAll(int bno) {
		log.info(">>>>update check 3");
		int isOk = sql.delete("commentMapper.deleteAll",bno);
		if(isOk>0) sql.commit();
		return isOk;
	}
	
}
