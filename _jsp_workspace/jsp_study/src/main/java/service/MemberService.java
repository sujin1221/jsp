package service;

import java.util.List;
import domain.MemberVO;

public interface MemberService {

	int register(MemberVO mvo);

	List<MemberVO> getList();

	int lastLogin(String id);

	int update(MemberVO mvo);

	MemberVO login(MemberVO mvo);

	int remove(String id);

}
