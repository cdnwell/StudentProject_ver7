package config;

public interface DBConfig {
	public final static String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	public final static String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	public final static String DB_USER = "scott";
	public final static String DB_PASSWD = "tiger";
}
