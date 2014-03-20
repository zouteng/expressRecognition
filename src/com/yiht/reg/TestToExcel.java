package com.yiht.reg;

import java.sql.SQLException;

import com.yiht.util.DataToExcel;

public class TestToExcel {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		DataToExcel etd = new DataToExcel();
		try {
			
		long startTime = System.currentTimeMillis(); // 获取开始时间
			etd.dataToexcel("e:/express2014031912.xls");
			System.out.println("数据导出成功!");
			
		long endTime = System.currentTimeMillis(); 
		System.out.println("导出表所需要的时间："+(endTime-startTime)+"ms");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据导出失败!");
		}
	}

}
