package service;

import java.util.List;

import domain.PagingVO;
import domain.boardVO;

public interface BoardService {

	int register(boardVO bvo);

	List<boardVO> getList(PagingVO pgvo);

	boardVO getDetail(int bno);

	int modify(boardVO bvo);

	int remove(int bno);

	int getTotal(PagingVO pgvo);

}
