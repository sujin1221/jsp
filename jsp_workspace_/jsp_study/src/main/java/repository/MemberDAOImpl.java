package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import orm.DatabaseBuilder;
import service.MemberServiceImpl;

public class MemberDAOImpl implements MemberDAO {

	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	//sql Session 객체
	private SqlSession sql;
	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}
	@Override
	public int insert(MemberVO mvo) {
		log.info(">>>join chekc3");
		int isOk=sql.insert("MemberMapper.reg",mvo);
		if(isOk>0) sql.commit();
		return isOk;
	}
	@Override
	public MemberVO login(MemberVO mvo) {
		log.info(">>>login check3");
		
		return sql.selectOne("MemberMapper.login",mvo);
	}
	@Override
	public int lastLogin(String id) {
		log.info(">>> lastLogin check 3");
		int isOk = sql.update("MemberMapper.last", id);
		if(isOk>0) sql.commit();
		return isOk;
	}
	@Override
	public List<MemberVO> getMember() {
		log.info(">>>> member check3");
		return sql.selectList("MemberMapper.list");
	}
	@Override
	public MemberVO getDetail(String id) {

		log.info(">>>> detail check3");
		return sql.selectOne("MemberMapper.detail",id);
	}
	@Override
	public int edit(MemberVO mvo) {
		log.info(">>>edit check3");
		int isOk=sql.update("MemberMapper.update",mvo);
		if(isOk>0) sql.commit();
		return isOk;
	}
	@Override
	public int delete(String id) {
		log.info(">>delete check3");
		int isOk = sql.delete("MemberMapper.delete", id);
		if(isOk>0)sql.commit();
		return isOk;
	}
	
	//메서드 처리
	
}
