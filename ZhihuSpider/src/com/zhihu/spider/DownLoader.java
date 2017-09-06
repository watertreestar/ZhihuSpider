package com.zhihu.spider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class DownLoader {
	public static void download(QuestionBean bean,String filepath){
		List<String> urls = bean.getImgUrl();
		String title = bean.getTitle();
		HttpClient httpClient = HttpClients.createDefault();
		HttpGet get = null;
		HttpResponse response = null;
		HttpEntity entity = null;
		InputStream is = null;
		byte[] buffer = new byte[1024];
		int length = 0;
		int index = 0;
		File file = null;
		FileOutputStream fos = null;
		String pathname;
		for(String url : urls){
			try {
				System.out.println("正在下载第"+index+"张图片");
				get = new HttpGet(url);
				pathname = filepath+"\\"+title;
				file = new File(pathname);
				if(!file.exists()){
					file.mkdir();
				}
				fos = new FileOutputStream(pathname+"\\"+index+".jpg");
				response = httpClient.execute(get);
				entity = response.getEntity();
				is = entity.getContent();
				while((length = is.read(buffer, 0, 1024))!=-1){
					fos.write(buffer, 0, length);
				}
				if(fos!=null){
					fos.close();
				}
				if(is!=null){
					is.close();
				}
				get.releaseConnection();
				index++;
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("下载失败");
			}
			
			
		}
		
		System.out.println("下载完成");
	}
}
