package com.yiht.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.yiht.util.DBUtils;


public class TestDB {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		saveExpress();
	}
	
	public static void saveExpress() throws Exception {
		Connection conn = DBUtils.createConn();
		String sql = "insert into ExpressInfo  values ('718358698808','111111','中通快递','718358698808.jpg','2014-02-11 15:52:34')" ;
		
		System.out.println(sql);
		//进行预编译
		PreparedStatement ps = DBUtils.getPs(conn, sql);
		ps.executeUpdate();
		DBUtils.close(ps);
		DBUtils.close(conn);
		
	}

}
