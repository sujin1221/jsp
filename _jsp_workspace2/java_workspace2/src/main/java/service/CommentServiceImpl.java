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
		log.info("postcheck>>>>");
		return cdao.insert(cvo);
	}

	@Override
	public List<CommentVO> getList(int bno) {
		log.info("listcheck>>>>2");
		return cdao.selectAll(bno);
	}

	@Override
	public int remove(int cno) {
		log.info("removecheck>>>>2");
		return cdao.remove(cno);
	}

	@Override
	public int update(CommentVO cvo) {
		log.info("updatecheck>>>>2");
		return cdao.update(cvo);
		
	}

}
