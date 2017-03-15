package hn.veryedu.jdbc.mysql;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import hn.veryedu.jdbc.common.db.JdbcUtils;

public class TestJdcb {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		for (int i = 1; i <= 20; i++){
			JdbcUtils jdbc = new JdbcUtils();
			String sql = "select * from user_t";
			List<Map<String, Object>> list = jdbc.findModeResult(sql, null);
			for (int j = 0; j < list.size(); j++) {
				System.out.println(list.get(j));
			}
		}
	}

}
