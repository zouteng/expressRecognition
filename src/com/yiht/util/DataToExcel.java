package com.yiht.util;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class DataToExcel {

	public void dataToexcel(String pathname) throws SQLException{
		Connection conn = DBUtils.createConn();
		String sql = "select * from express ";
		PreparedStatement ps = DBUtils.getPs(conn, sql);	
	    ResultSet rs=ps.executeQuery();
	    
	    try {
            // 获取总列数
            int CountColumnNum = rs.getMetaData().getColumnCount();
            int i = 1;
            // 创建Excel文档
            HSSFWorkbook wb = new HSSFWorkbook();
            // sheet 对应一个工作页
            HSSFSheet sheet = wb.createSheet("快递单");
            
          //  HSSFCellStyle style = wb.createCellStyle();
    	//	style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
    		
    		// 设置单元格宽度
			sheet.setColumnWidth(0, 5000);
			sheet.setColumnWidth(1, 4500);
			sheet.setColumnWidth(2, 4500);
			sheet.setColumnWidth(3, 4500);
			sheet.setColumnWidth(4, 3500);
			sheet.setColumnWidth(5, 3500);
			sheet.setColumnWidth(5, 6000);
            
            HSSFRow firstrow = sheet.createRow(0); // 下标为0的行开始
            HSSFCell[] firstcell = new HSSFCell[CountColumnNum];
            String[] names = new String[CountColumnNum];
            names[0] = "运单号";
            names[1] = "寄件人号码";
            names[2] = "收件人号码";
            names[3] = "文件日期";
            names[4] = "所属快递公司";
            names[5] = "归属地";
            names[6] = "处理时间";
            for (int j = 0; j < CountColumnNum; j++) {
                firstcell[j] = firstrow.createCell((short) j);
                firstcell[j].setCellValue(new HSSFRichTextString(names[j]));
            }
            while (rs.next()) {
                // 创建电子表格的一行
                HSSFRow row = sheet.createRow(i); // 下标为1的行开始
                for (int j = 0; j < CountColumnNum; j++) {
                    // 在一行内循环
                    HSSFCell cell = row.createCell((short) j);
                    // 设置表格的编码集，使支持中文
                    // // 先判断数据库中的数据类型
                    // 将结果集里的值放入电子表格中
                    cell.setCellValue(new HSSFRichTextString(rs
                            .getString(j + 1)));
                }
                i++;
            }
            try
    		{
    			FileOutputStream fout = new FileOutputStream(pathname);
    			wb.write(fout);
    			fout.close();
    		}
    		catch (Exception e)
    		{
    			e.printStackTrace();
    		}

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
		
		
	}
	
	
}
