package orm;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DatabaseBuilder {
	
	private static SqlSessionFactory factory;
	private static final String CONFIG = "orm/MybatisConfig.xml";
	
	//초기화 블럭을 사용하여 객체 생성후 초기화
	static {
		try {
			factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(CONFIG));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getFactory() {
		return factory;
	}
}
