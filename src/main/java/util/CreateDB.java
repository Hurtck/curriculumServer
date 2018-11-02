package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateDB {
    public Connection con=null;
    private static final String username="root";
    private static final String password="Lgs950921!";
    private static final String driver="com.mysql.cj.jdbc.Driver";
    private static final String url="jdbc:mysql://localhost:3306/kebiao?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
    private PreparedStatement ps=null;
    public CreateDB() {
    	try {
			Class.forName(driver);
			try {
				con=DriverManager.getConnection(url,username, password);
	            //ps=con.prepareStatement(sql);		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("连接失败");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    public void close() {
    	try {
			this.ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
