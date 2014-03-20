package com.yiht.test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

public class TestColor {
	 private static Map<BufferedImage, String> trainMap = null;  
	    private static int index = 0;  
	  
	    public static int isBlack(int colorInt) {  
	        Color color = new Color(colorInt);  
	        if (color.getRed() + color.getGreen() + color.getBlue() <= 500) {  
	            return 1;  
	        } 
	      
	        return 0;  
	    }  
	  
	    public static int isWhite(int colorInt) {  
	        Color color = new Color(colorInt);  
	        if (color.getRed() + color.getGreen() + color.getBlue() > 600) {  
	            return 1;  
	        }  
	        return 0;  
	    }  
	  //去除黑色的部分
	        public static void removeBlank(BufferedImage img) throws Exception {  
	        int width = img.getWidth();  
	        int height = img.getHeight();  
	        System.out.println("白色："+Color.WHITE.getRGB());
	        System.out.println("黑色："+Color.BLACK.getRGB());
	        BufferedImage newImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB );
	        
	        
	    for (int y = 0; y < height; ++y) {  
	            for (int x = 0; x < width; ++x) {  
	              /*  if (isBlack(img.getRGB(x, y)) == 1) {  
	                   img.setRGB(x, y, rgb);
	                    break Label1;  
	                }  */
	            	
	            //	System.out.println(img.getRGB(x, y));
	            	img.setRGB(x, y, 255);
	            }  
	        }  
	    ImageIO.write(newImage, "jpg", new File("f:/dd.jpg"));
	/*       Label2: for (int y = height - 1; y >= 0; --y) {  
	            for (int x = 0; x < width; ++x) {  
	                if (isBlack(img.getRGB(x, y)) == 1) {  
	                	img.setRGB(x, y, rgb);
	                    break Label2;  
	            	img.setRGB(x, y, rgb);
	                }  
	            }*/
	        
	        
	        
	        }  
	        
	    
/*	    public static BufferedImage removeBlank(BufferedImage img) throws Exception {  
	        int width = img.getWidth();  
	        int height = img.getHeight();  
	        int start = 0;  
	        int end = 0;  
	        Label1: for (int y = 0; y < height; ++y) {  
	            for (int x = 0; x < width; ++x) {  
	                if (isBlack(img.getRGB(x, y)) == 1) {  
	                    start = y;  
	                    break Label1;  
	                }  
	            }  
	        }  
	        Label2: for (int y = height - 1; y >= 0; --y) {  
	            for (int x = 0; x < width; ++x) {  
	                if (isBlack(img.getRGB(x, y)) == 1) {  
	                    end = y;  
	                    break Label2;  
	                }  
	            }  
	        }  
	        return img.getSubimage(0, start, width, end - start + 1);  
	    }  */
	    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedImage bi=ImageIO.read(new File("D:\\test.jpg"));
		try {
			removeBlank(bi);
			System.out.println("去除黑色成功");
			//huiDuHua(bi);
			//System.out.println("灰度化成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//灰度化
	public static void huiDuHua(BufferedImage img) {
        int sWight = img.getWidth();
        int sHight = img.getHeight();
        BufferedImage newImage = new BufferedImage(sWight, sHight,
                BufferedImage.TYPE_BYTE_GRAY);
        for (int x = 0; x < sWight; x++) {
            for (int y = 0; y < sHight; y++) {
                int rgb= img.getRGB(x, y);
                newImage.setRGB(x, y, rgb);
            }
        }
         try {
            ImageIO.write(newImage, "jpg", new File("f:/aa.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }  
        
    }

}
