package com.yiht.reg;

import java.awt.Rectangle;
import java.io.File;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;


public class JmagickCutPic {

	static {
		// 不能漏掉这个，不然jmagick.jar的路径找不到
		System.setProperty("jmagick.systemclassloader", "no");
	}

	/**
	 * 切图
	 * 
	 * @param imgPath
	 *            源图路径
	 * @param toPath
	 *            修改图路径
	 * @param w
	 * @param h
	 * @param x
	 * @param y
	 * @throws MagickException
	 */
	public static void cutImg(String imgPath, String toPath, int w, int h,
			int x, int y) throws MagickException {
		ImageInfo infoS = null;
		MagickImage image = null;
		MagickImage cropped = null;
		Rectangle rect = null;
		try {
			infoS = new ImageInfo(imgPath);
			image = new MagickImage(infoS);
			rect = new Rectangle(x, y, w, h);
			cropped = image.cropImage(rect);
			cropped.setFileName(toPath);
			
			//cropped.thresholdImage(220);//阈值
			
			cropped.writeImage(infoS);

		} finally {
			if (cropped != null) {
				cropped.destroyImages();
			}
		}
	}
	
	/**
	 * @param args
	 * @throws MagickException
	 */
	public static void main(String []args) throws MagickException{
		long startTime=System.currentTimeMillis();   //获取开始时间
		System.out.println("开始裁切图片..");
		
		 File file = new File("d:\\pic\\testcut\\");
		 File[] files = file.listFiles();
		 for (int i = 0; i < files.length; i++) {
		   if(!files[i].isDirectory()){
		 //     System.out.println(  files[i].getName());
			   String fname=files[i].getName();
			   cutImg("d:\\pic\\testcut\\"+fname,"d:\\pic\\testcut\\receivercut\\"+fname,220, 48, 688, 315);
			   cutImg("d:\\pic\\testcut\\"+fname,"d:\\pic\\testcut\\sendercut\\"+fname,220,48,148,315);
			   
		   }
		 }

		//cutImg("E:\\before\\718358691392.jpg","e:\\after\\718358691392.jpg",200,60,670,300);
		System.out.println("裁剪done!");
		
		long endTime=System.currentTimeMillis(); //获取结束时间

		 System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
		
		
	}
	

}
