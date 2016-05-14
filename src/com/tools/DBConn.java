package com.tools;

import java.sql.*;

public class DBConn {
	public Connection conn = null;
	public Statement stmt = null;
	public ResultSet rs = null;
	/* 定义驱动字符串 */
	private static final String DRIVER = LabProperties.getValue("DB_DRIVER");
	/* 定义URL */
	private static final String URL = LabProperties.getValue("DB_URL");
	/* 定义用户名密码 */
	private static final String NAME = LabProperties.getValue("DB_USER");
	private static final String PADDWORD = LabProperties.getValue("DB_PWD");
	
	/*static{ …… }就是静态块，他在程序编译的时候就会自动执行
	 * （记住是编译的时候就执行，不是运行阶段），
	 * 不需要调用。而且与他存在的位置没有关系，
	 * 与他所在的类是否被实例化也没有关系。
	 */
	/* 加载驱动 */
	static{
		try{
			Class.forName(DRIVER);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	/* 得到数据库链接 */
	public static Connection getConnection(){
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(URL,NAME,PADDWORD);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
	
	public int executeUpdate(String sql) {
	    int result = 0;
		try {
		    conn = getConnection();
		    stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		                                  ResultSet.CONCUR_READ_ONLY);
		    result = stmt.executeUpdate(sql);
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	 }
	
	 public ResultSet executeQuery(String sql) {
		 try {
		    conn = getConnection();
		    stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		                                  ResultSet.CONCUR_READ_ONLY);
		    rs = stmt.executeQuery(sql);
		  }
		  catch (SQLException ex) {
		    System.err.println(ex.getMessage());
		  }
		 try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	 }
	 
	public void closeAll(){
		try {							//捕捉异常
			if (rs != null) {				//当ResultSet对象的实例rs不为空时
				rs.close();					//关闭ResultSet对象
			}
			if (stmt != null) {				//当Statement对象的实例stmt不为空时
				stmt.close();				//关闭Statement对象
			}
			if (conn != null) {				//当Connection对象的实例conn不为空时
				conn.close();				//关闭Connection对象
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);	//输出异常信息
		}
	}
	
	/* 关闭Connection */
	public static void closeCon(Connection con){
		try{
			if(con != null)
				con.close();
		}catch(SQLException e){
				e.printStackTrace();
			}
	}
	
	/* 关闭PreparedStatemen */
	public static void closePt(PreparedStatement pt){
		try{
			if(pt != null)
				pt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/* 关闭ResultSet */
	public static void closeRs(ResultSet rs){
		try{
			if(rs != null){
				rs.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
