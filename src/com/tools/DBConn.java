package com.tools;

import java.sql.*;

public class DBConn {
	public Connection conn = null;
	public Statement stmt = null;
	public ResultSet rs = null;
	/* ���������ַ��� */
	private static final String DRIVER = LabProperties.getValue("DB_DRIVER");
	/* ����URL */
	private static final String URL = LabProperties.getValue("DB_URL");
	/* �����û������� */
	private static final String NAME = LabProperties.getValue("DB_USER");
	private static final String PADDWORD = LabProperties.getValue("DB_PWD");
	
	/*static{ ���� }���Ǿ�̬�飬���ڳ�������ʱ��ͻ��Զ�ִ��
	 * ����ס�Ǳ����ʱ���ִ�У��������н׶Σ���
	 * ����Ҫ���á������������ڵ�λ��û�й�ϵ��
	 * �������ڵ����Ƿ�ʵ����Ҳû�й�ϵ��
	 */
	/* �������� */
	static{
		try{
			Class.forName(DRIVER);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	/* �õ����ݿ����� */
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
		try {							//��׽�쳣
			if (rs != null) {				//��ResultSet�����ʵ��rs��Ϊ��ʱ
				rs.close();					//�ر�ResultSet����
			}
			if (stmt != null) {				//��Statement�����ʵ��stmt��Ϊ��ʱ
				stmt.close();				//�ر�Statement����
			}
			if (conn != null) {				//��Connection�����ʵ��conn��Ϊ��ʱ
				conn.close();				//�ر�Connection����
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);	//����쳣��Ϣ
		}
	}
	
	/* �ر�Connection */
	public static void closeCon(Connection con){
		try{
			if(con != null)
				con.close();
		}catch(SQLException e){
				e.printStackTrace();
			}
	}
	
	/* �ر�PreparedStatemen */
	public static void closePt(PreparedStatement pt){
		try{
			if(pt != null)
				pt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/* �ر�ResultSet */
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
