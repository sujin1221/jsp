package repository;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> selectList(PagingVO pgvo);

	BoardVO getDetail(int bno);

	int readCountUpdate(int bno);

	int update(BoardVO bvo);

	int delete(int bno);

	int total(PagingVO pgvo);

}
