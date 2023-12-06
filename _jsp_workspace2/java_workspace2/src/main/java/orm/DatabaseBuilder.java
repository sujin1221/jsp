package orm;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DatabaseBuilder {
private static SqlSessionFactory factory;
private static final String CONFIG = "/orm/Mybatisconfig.xml";

static {
	try {
		factory = new SqlSessionFactoryBuilder()
				.build(Resources.getResourceAsReader(CONFIG));
	} catch (Exception e) {
		e.printStackTrace();
	}
}
public static SqlSessionFactory getFactory() {
	return factory;
}
}
