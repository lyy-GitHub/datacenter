package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.PreparedStatement;

public class DBUtil {
 public static Connection getCon()
 {
	 Connection conn=null;
	 try {
		 
		String url="jdbc:mysql://localhost:3306/";
		String user="root";
		String pwd="123456";
		Class.forName("com.mysql.jdbc.driver");
		conn=DriverManager.getConnection(url,user,pwd);
	
	 
	 } catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return conn;
 }
public static PreparedStatement getPstmt(Connection conn,String sql)
{
	PreparedStatement pstmt=null;
	try {
		
		pstmt=(PreparedStatement) conn.prepareStatement(sql);
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return pstmt;
	
}
public static void close(Connection conn){
	if(conn!=null)
	{
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
public static void close(PreparedStatement pstmt)
{
	if(pstmt!=null)
	{
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
}
