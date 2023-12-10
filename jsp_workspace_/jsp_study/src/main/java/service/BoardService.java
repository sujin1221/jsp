package service;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardService {

	int register(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(int bno);


	int edit(BoardVO bvo);

	int remove(int bno);

	int getTotalcount(PagingVO pgvo);

}
