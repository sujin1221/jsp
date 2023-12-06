package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.cj.log.Log;

import domain.MemberVO;
import orm.DatabaseBuilder;

public class MemberDAOImpl implements MemberDAO {
	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	//sql session 객체
	private SqlSession sql;
	
	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}
	//메서드 처리

	@Override
	public int insert(MemberVO mvo) {
		log.info(">>> join check 3");
		int isOk = sql.insert("MemberMapper.reg", mvo);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public List<MemberVO> list() {
		log.info(">>> list check 3");
		return sql.selectList("MemberMapper.list");
	}

	@Override
	public int logout(String id) {
		log.info(">>> join check 3");
		int isOk = sql.update("MemberMapper.last", id);
		if(isOk>0) {sql.commit();}
		return isOk;
	}

	@Override
	public int update(MemberVO mvo) {
		log.info("update check 3");
		int isOk = sql.update("MemberMapper.update", mvo);
		if(isOk>0) {sql.commit();} //update, delete, insert는 반드시 commit이 필요함!
		return isOk;
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		log.info("login check 3");
		return sql.selectOne("MemberMapper.login", mvo);
	}

	@Override
	public int remove(String id) {
		log.info("remove check 3");
		int isOk = sql.delete("MemberMapper.del",id);
		if(isOk>0) {sql.commit();}	
		return isOk;
	}
}
