package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import repository.CommentDAO;
import repository.CommentDAOImpl;

public class CommentServiceImpl implements CommentService {
private static Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
private CommentDAO cdao;

public CommentServiceImpl() {
	cdao = new CommentDAOImpl();
}

@Override
public int post(CommentVO cvo) {
	log.info("comment post check 2");
	return cdao.insert(cvo);
}

@Override
public List<CommentVO> getList(int bno) {
	log.info("comment list check 2");
	return cdao.list(bno);
}

@Override
public int remove(int cno) {
	log.info("comment remove check 2");
	return cdao.remove(cno);
}

@Override
public int modify(CommentVO cvo) {
	log.info("comment modify check 2");
	return cdao.update(cvo);
}
}
