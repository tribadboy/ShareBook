package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import com.model.Emp;
import com.tools.DBConn;

public class EmpDaoImpl implements EmpDao {

	  private Connection conn = DBConn.getConnection();
	  private static PreparedStatement pstmt = null;
	  
	  public int insert(Emp e){
			int ret = -1;
			String sql = "insert into Blog values(?,?)";
			try{
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, e.getName());
				pstmt.setInt(2, e.getNo());
				ret = pstmt.executeUpdate();
			}catch(SQLException ex){
				ex.printStackTrace();
				ret = 0;
			}finally{
				DBConn.closePt(pstmt);
				DBConn.closeCon(conn);
			}
			return ret;
		}
}
