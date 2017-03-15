package hn.veryedu.jdbc.mysql;

import hn.veryedu.jdbc.common.db.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;



public class TestMySQLConnection {
	private static Integer counter = 0;

	public static void main(String[] args){
		
		for (int i = 1; i <= 2000; i++) {
			new Thread(new Runnable() {
				public void run() {
					Connection conn = null;
					PreparedStatement pstmt= null;
				    ResultSet resultSet= null;
					try {
						conn = DBUtils.getConnection();
						synchronized (counter) {
							System.out.print(Thread.currentThread().getName());
							System.out.print("    counter = " + counter++
									+ "  conn = " + conn);
							System.out.println();
							
							pstmt = conn.prepareStatement("select * from user_t");
							resultSet = pstmt.executeQuery(); 
							ResultSetMetaData metaData = resultSet.getMetaData();  
					        int cols_len = metaData.getColumnCount();  
					        while(resultSet.next()){  
					            for(int i=0; i<cols_len; i++){  
					                String cols_name = metaData.getColumnName(i+1);  
					                Object cols_value = resultSet.getObject(cols_name);  
					                //System.out.println(cols_name+"---"+cols_value); 
					            }  
					        }
					        DBUtils.close(resultSet, pstmt, conn);
							//conn.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}  
				}
			}).start();
		}
	}
}
