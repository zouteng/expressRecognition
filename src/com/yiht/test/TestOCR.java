package com.yiht.test;

import java.io.File;
import java.io.IOException;

import com.yiht.reg.OCR;

public class TestOCR {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "d:\\test\\demo.jpg";   
        try {   
            String valCode = new OCR().recognizeText(new File(path), "jpg");   
            System.out.println(valCode);   
        } catch (IOException e) {   
            e.printStackTrace();   
        } catch (Exception e) {
			e.printStackTrace();
		}    
	}

}

