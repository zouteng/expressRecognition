package com.yiht.test;

import java.io.IOException;
import java.util.Calendar;
import org.junit.Test;

public class TestAll {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("时间：" + System.currentTimeMillis());
		Calendar instance = Calendar.getInstance();
		System.out.println("日期为：" + instance.getTime());
		String cmd = " shutdown -r -t 0 ";
	//Runtime.getRuntime().exec(cmd);
		System.out.println(cmd);
		System.out.println("Test.main()");
	}
	@Test
	public void test01(){
		System.out.println("Junit4 test");
	}			
}
