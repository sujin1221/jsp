package service;

import java.util.List;

import domain.MemberVO;

public interface MemberService {

	int register(MemberVO mvo);

	MemberVO login(MemberVO mvo);

	int lastLogin(String id);

	List<MemberVO> getMember();

	MemberVO getDetail(String id);

	int edit(MemberVO mvo);

	int resign(String id);

}
