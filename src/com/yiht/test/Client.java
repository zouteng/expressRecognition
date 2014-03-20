package com.yiht.test;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;



public class Client {  
    private Socket socket;  
    public Client(){  
          
    }  
    public void SendImage() throws IOException{  
        String imageFileName = "F://hello//1.jpg";  
        socket = new Socket("127.0.0.1", 8088);  
        try {  
            BufferedInputStream bis =new BufferedInputStream(new FileInputStream(imageFileName));  
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());  
            byte buffer[] = new byte[1024];//一次读1K，循环读写图片字节流  
            int eof = 0;  
            while((eof = bis.read(buffer, 0 ,1024)) != -1){  
                dos.write(buffer, 0, eof);  
            }  
            dos.close();  
            bis.close();  
            socket.close();  
        } catch (FileNotFoundException e) {  
        	e.printStackTrace();  
        }  
    }  
  
    public static void main(String s[]) throws IOException {  
        Client client = new Client();  
        client.SendImage();  
    }  
}  
