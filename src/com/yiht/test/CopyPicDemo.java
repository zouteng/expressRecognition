package com.yiht.test;

import java.io.*;
public class CopyPicDemo {

        /**
         * 使用字节流复制一张图片
         * 操作步骤：
         * 1.创建图片写入关联、图片读取关联流对象
         * 2.创建缓冲区存储
         * 3.循环读取
         * 4.读取完成，关闭资源
         */
    /*    public static void main(String[] args) {
                //创建读写流对象
                FileInputStream fis = null;
                FileOutputStream fos = null;
                try {
                        //确定操作文件位置
                	//源文件
                        fis = new FileInputStream("D:\\1.jpg");
                        
                        //目标文件
                        fos = new FileOutputStream("E:\\QQ.jpg");
                        
                        int len = 0;
                        //创建存储数组
                        byte []buf = new byte[1024];
                        //循环读取数据，将读到的数据写入到制定文件中。
                        while((len=fis.read(buf))!=-1){
                                fos.write(buf,0,len);
                        }
                } catch (IOException e) {
                        throw new RuntimeException("操作失败");
                }
                finally{
                        //关闭资源
                        try {
                                if(fis!=null)
                                        fis.close();
                        } catch (IOException e2) {
                                throw new RuntimeException("读入资源关闭失败");
                        }
                        try {
                                if(fos!=null)
                                        fos.close();
                        } catch (IOException e3) {
                                throw new RuntimeException("写入资源关闭");
                        }
                }
        }*/
       
      //重命名，相当于移动文件renameTo  
        public static void reaName(){  
            File f1 = new File("D:\\2.jpg");  
            File f2 = new File("E:\\2.jpg");  
            f1.renameTo(f2);  
        }  
        public static void main(String[] args) {
			reaName();
		System.out.println("done");
        }
        
        

}