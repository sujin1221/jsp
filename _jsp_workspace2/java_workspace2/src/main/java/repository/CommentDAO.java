package repository;

import java.util.List;

import domain.CommentVO;

public interface CommentDAO {

	int insert(CommentVO cvo);

	List<CommentVO> selectAll(int bno);

	int remove(int cno);

	int update(CommentVO cvo);

	int removeAll(int bno);

}
