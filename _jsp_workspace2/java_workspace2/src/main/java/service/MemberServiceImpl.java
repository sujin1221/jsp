package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOImpl;

public class MemberServiceImpl implements MemberService {
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	private MemberDAO mdao; //repository => interface 생성
	public MemberServiceImpl() {
		mdao = new MemberDAOImpl();
	}
	@Override
	public int register(MemberVO mvo) {
		log.info("join check 2");
		return mdao.insert(mvo);
	}
	@Override
	public List<MemberVO> getList() {
		log.info("list check 2");
		return mdao.list();
	}
	@Override
	public int lastLogin(String id) {
		log.info("list check 2");
		return mdao.logout(id);
	}
	@Override
	public int update(MemberVO mvo) {
		log.info("update check 2");
		return mdao.update(mvo);
	}
	@Override
	public MemberVO login(MemberVO mvo) {
		log.info("login check 2");
		return mdao.login(mvo);
	}
	@Override
	public int remove(String id) {
		log.info("remove check 2");
		return mdao.remove(id);
	}
	@Override
	public List<BoardVO> getList(String id) {
		log.info("mylist check 2");
		return mdao.mylist(id);
	}
}
