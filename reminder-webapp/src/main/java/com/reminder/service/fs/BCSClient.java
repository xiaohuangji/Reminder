package com.reminder.service.fs;

import com.baidu.inf.iis.bcs.BaiduBCS;
import com.baidu.inf.iis.bcs.auth.BCSCredentials;
import com.baidu.inf.iis.bcs.model.BCSClientException;
import com.baidu.inf.iis.bcs.model.BCSServiceException;
import com.baidu.inf.iis.bcs.model.ObjectMetadata;
import com.baidu.inf.iis.bcs.model.X_BS_ACL;
import com.baidu.inf.iis.bcs.request.PutObjectRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.reminder.constant.BaiduConstant.*;

public class BCSClient {

	public static String putObject(InputStream ios,long fileLength,String suffix){
		BCSCredentials credentials = new BCSCredentials(BAIDUBCS_ACCESS_KEY, BAIDUBCS_SECRET_KEY);
		BaiduBCS baiduBCS = new BaiduBCS(credentials, BAIDUBCS_HOST);
		baiduBCS.setDefaultEncoding("UTF-8"); // Default UTF-8
		try {
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType("image/jpeg");
			objectMetadata.setContentLength(fileLength);
			
			String fileName=genFileName(suffix);
		
			PutObjectRequest request = new PutObjectRequest(BAIDUBCS_BUCKET, "/"+fileName, ios, objectMetadata);
			ObjectMetadata result = baiduBCS.putObject(request).getResult();
			System.out.println(result.toString());
			baiduBCS.putObjectPolicy(BAIDUBCS_BUCKET, "/"+fileName, X_BS_ACL.PublicRead);
			return fileName;
			
		} catch (BCSServiceException e) {
			e.printStackTrace();
		} catch (BCSClientException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static String genFileName(String suffix){
		long randomnum=Math.round(Math.random() * 100);
		return System.currentTimeMillis()+""+randomnum+"."+suffix;
	}
	
	public static String fileUrl(String fileName){
		return "http://"+BAIDUBCS_HOST+"/"+BAIDUBCS_BUCKET+"/"+fileName;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		File file=new File("/Users/renren/Downloads/20130909142945.jpg");
		System.out.println(BCSClient.putObject(new FileInputStream(file),file.length(), "jpg"));
	}
}


