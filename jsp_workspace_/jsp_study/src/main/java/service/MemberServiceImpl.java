package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOImpl;

public class MemberServiceImpl implements MemberService {
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	private MemberDAO mdao; //repository => interface로 생성
	
	public MemberServiceImpl() {
		mdao = new MemberDAOImpl();
		
	}

	@Override
	public int register(MemberVO mvo) {
		log.info(">>>join check2");
		return mdao.insert(mvo);
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		log.info(">>>login check2");
		return mdao.login(mvo);
	}

	@Override
	public int lastLogin(String id) {
		log.info(">>> last Login check2");
		
		return mdao.lastLogin(id);
	}

	@Override
	public List<MemberVO> getMember() {
		log.info(">>>member check2");
		return mdao.getMember();
	}

	@Override
	public MemberVO getDetail(String id) {
		log.info("detail check2");
		return mdao.getDetail(id);
	}

	@Override
	public int edit(MemberVO mvo) {
		log.info("edit check2");
		return mdao.edit(mvo);
	}

	@Override
	public int resign(String id) {
		log.info("delete check3");
		return mdao.delete(id);
	}
	

}
