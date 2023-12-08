package repository;

import java.util.List;

import domain.PagingVO;
import domain.boardVO;

public interface BoardDAO {
 int insert(boardVO bvo);

List<boardVO> selectList(PagingVO pgvo);

int readCountUpdate(int bno);

boardVO getDetail(int bno);

int update(boardVO bvo);

int delete(int bno);

int getTotal(PagingVO pgvo);

int deleteAll(int bno);
}
