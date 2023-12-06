package repository;

import java.util.List;

import domain.BoardVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> list();

	BoardVO detail(int bno);

	int modify(BoardVO bvo);

	void readCount(int bno);

	int remove(int bno);

}
