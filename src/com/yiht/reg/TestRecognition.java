package com.yiht.reg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import magick.MagickException;

import com.yiht.util.DBUtils;

public class TestRecognition {
	public static void main(String[] args) throws MagickException {
		long startTime = System.currentTimeMillis(); // 获取开始时间

		System.out.println("开始剪切识别并插入数据...");
		// System.out.println("开始时间 "+new Date().toLocaleString());
		// MedianSound as = new MedianSound();
		// AvgSound avg=new AvgSound();
		// SnnSound snn = new SnnSound();
		File file = new File("D:\\pic\\print\\express\\2.10\\");
		File[] files = file.listFiles();
		int len = files.length;
		for (int i = 0; i < len; i++) {
			if (!files[i].isDirectory()) {
				String expressName = files[i].getName();

				// 剪切
				//裁切寄件人号码图片
				JmagickCutPic.cutImg("D:\\pic\\print\\express\\2.10\\"
						+ expressName, "d:\\pic\\cutpic\\printcut\\sendercut\\"
								+ expressName,220,48,148,315);
				
				//裁切收件人号码图片
				JmagickCutPic.cutImg("D:\\pic\\print\\express\\2.10\\"
						+ expressName, "d:\\pic\\cutpic\\printcut\\receivercut\\"
						+ expressName, 220, 55, 675, 315);
				
				/*  //去噪算法
				 * try { as.medianFiltering("d:\\pic\\cutpic\\printcut\\" +
				 * expressName, "d:\\pic\\cutpic\\printcut2\\" + expressName,
				 * "jpg"); snn.snnFiltering("d:\\pic\\cutpic\\printcut2\\" +
				 * expressName, "d:\\pic\\cutpic\\printcut3\\" + expressName,
				 * "jpg"); avg.avrFiltering("d:\\pic\\cutpic\\printcut3\\" +
				 * expressName, "d:\\pic\\cutpic\\printcut4\\" + expressName,
				 * "jpg");
				 * 
				 * 
				 * } catch (IOException e1) { // TODO Auto-generated catch block
				 * e1.printStackTrace(); }
				 */
				
				//寄件人号码识别和处理
				String sendertel = replaceBlank((getRegtel("d:\\pic\\cutpic\\printcut\\sendercut\\"
						+ expressName)).replace(".", "").replace("-", ""));

				if (sendertel.length() > 11) {
					sendertel = sendertel.substring(0, 11);
				}
				//收件人号码识别和处理
				String receivertel = replaceBlank((getRegtel("d:\\pic\\cutpic\\printcut\\receivercut\\"
						+ expressName)).replace(".", "").replace("-", ""));
				
				if (receivertel.length() > 11) {
					receivertel = receivertel.substring(0, 11);
				}
				
	
				// System.out.println("判断为空前 快递单" + expressName + " 的识别结果:" +
				// tel); 挑选出识别为空值的图片
				/*if (tel.trim().equals("") || tel.length() == 0 || tel == null) {

					try {
						File afile = new File("D:\\pic\\print\\express\\2.10\\"
								+ expressName);
						File bfile = new File("d:\\pic\\badpic\\" + expressName);

						InputStream inStream = new FileInputStream(afile);
						OutputStream outStream = new FileOutputStream(bfile);

						byte[] buffer = new byte[1024];

						int length;
						// copy the file content in bytes
						while ((length = inStream.read(buffer)) > 0) {
							outStream.write(buffer, 0, length);
						}

						if (inStream != null) {
							inStream.close();
						}
						if (outStream != null) {
							outStream.close();
						}
                         System.out.println("afile是否为文件："+afile.isFile());
                         if(afile.delete()){
                        	 System.out.println("删除成功!");
                         }
						// delete the original file
						System.out.println("File is copied successful!");
						continue;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}*/
				
				
				
				System.out.println("快递单" + expressName + "发件人 的识别结果:" + sendertel);
				System.out.println("快递单" + expressName + " 收件人的识别结果:" + receivertel);

				String expressId = expressName.substring(0,
						expressName.lastIndexOf("."));
				String filetime="2014-02-10";
				String expresstype = "中通快递";
				String attribution="张家港市";
				String nowdate = new Date().toLocaleString();

				try {
					// 插入
					save(expressId, sendertel, receivertel,filetime,expresstype,attribution,nowdate);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		long endTime = System.currentTimeMillis(); // 获取结束时间

		// System.out.println("结束时间为："+new Date().toLocaleString());
		System.out.println("程序总共运行时间： " + (endTime - startTime) + "ms");

	}

	public static String getRegtel(String path) {
		try {
			String valCode = new OCR().recognizeText(new File(path), "jpg");
			// System.out.println(valCode);
			return valCode;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void save(String expressId, String sendertel,String receivertel, String filetime,
			String etype,String attribution, String createtime) throws SQLException {
		Connection conn = DBUtils.createConn();
		String sql = "insert into Express  values (?,?,?,?,?,?,?)";

		// 进行预编译
		PreparedStatement ps = DBUtils.getPs(conn, sql);
		ps.setString(1, expressId);
		ps.setString(2, sendertel);
		ps.setString(3, receivertel);
		ps.setString(4, filetime);
		ps.setString(5, etype);
		ps.setString(6, attribution);
		ps.setString(7, createtime);
		// System.out.println(sql);
		ps.executeUpdate();
		DBUtils.close(ps);
		DBUtils.close(conn);
	}

	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

}
