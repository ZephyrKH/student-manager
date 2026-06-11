package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.InputStream;
import java.util.Properties;

public class Dbconn {
	
	private static String DB_URL = "jdbc:mysql://localhost:3306/students?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8";
	private static String DB_USER = "root";
	private static String DB_PASS = "200412";
	
	static {
		// 尝试从配置文件加载数据库连接信息
		try (InputStream input = Dbconn.class.getClassLoader().getResourceAsStream("dbconfig.properties")) {
			if (input != null) {
				Properties prop = new Properties();
				prop.load(input);
				if (prop.getProperty("db.url") != null) DB_URL = prop.getProperty("db.url");
				if (prop.getProperty("db.user") != null) DB_USER = prop.getProperty("db.user");
				if (prop.getProperty("db.password") != null) DB_PASS = prop.getProperty("db.password");
				System.out.println("已从配置文件加载数据库连接信息");
			}
		} catch (Exception e) {
			System.out.println("未找到配置文件，使用默认数据库连接信息");
		}
	}

	private Connection conn;
	public  Connection getConnection() throws SQLException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		} catch (ClassNotFoundException e) {
			System.out.println("找不到JDBC驱动类!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("数据库连接失败!");
			e.printStackTrace();
			throw e;
		}
		return conn;
	}
	
	public void closeAll(Connection conn,Statement stat,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				if(stat!=null){
					try {
						stat.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}finally{
						if(conn!=null){
							try {
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
}
