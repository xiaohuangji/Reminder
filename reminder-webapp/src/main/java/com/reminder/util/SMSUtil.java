package com.reminder.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SMSUtil {
	
	
	private static Configuration configuration=Configuration.getInstance();
	
	private static String sendMessage(String mobileNO,String msg){
		String urlstr=configuration.getString("request_url");
		String name=configuration.getString("enterprise_user_name");
		String pwd=configuration.getString("enterprise_user_pwd");
		String sender=configuration.getString("sender_phoneNO");
		
		String sendstr="name="+name+"&pwd="+pwd+"&dst="+mobileNO+"&msg="+msg+"&sender="+sender;
		
		String retstr="";
		
		OutputStream os=null;
		
		BufferedReader br=null;
		
		try {
			URL url=new URL(urlstr);
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			
			os=conn.getOutputStream();
			os.write(sendstr.getBytes("GBK"));
			
			br=new BufferedReader(new InputStreamReader(conn.getInputStream(),"GBK"));
			
			String tempstr="";
			while((tempstr=br.readLine())!=null){
				retstr+=tempstr;
			}
			
			return retstr;
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			try {
				os.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return null;
		
	}

    public static boolean sendSM(String mobileNO, String sendStr) {
    	
    	if(mobileNO==null||mobileNO=="")
    		return false;
        
		String retStr = SMSUtil.sendMessage(mobileNO, sendStr);

		String[] strs = retStr.split("&");
        
		String numstr = strs[0].replace("num=", "");
        
		if (numstr != null && Integer.parseInt(numstr) > 0) {
			return true;
		}
		return false;
	}

    public static void main(String[] args){
        System.out.println(sendSM("18510407654","testtest"));
    }

}
