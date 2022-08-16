package dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DaoFactory {
	//Daoの生成
	public static UserDao createUserDao() {
		return new UserDaoImpl(getDataSource());
	}
	public static DailyRecordDao createDailyRecordDao() {
		return new DailyRecordDaoImpl(getDataSource());
	}
	// dsの生成
	private static DataSource getDataSource() {
		InitialContext ctx = null;
		DataSource ds = null;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/sleeplog_db");
		} catch (NamingException e) {
			if (ctx != null) {
				try {
					ctx.close();
				} catch (NamingException e1) {
					throw new RuntimeException(e1);
				}
			}
			throw new RuntimeException(e);
		}
		return ds;
	}

}
