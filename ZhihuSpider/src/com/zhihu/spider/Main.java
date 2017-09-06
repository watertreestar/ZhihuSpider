package com.zhihu.spider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
	public static void main(String args[]){
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
//			String questionId = br.readLine();
			String questionId = "21844569";
			if(questionId.matches("^\\d+$")){
				Resolver resolver = new Resolver(questionId);
				QuestionBean bean = new QuestionBean();
				List<String> imgUrl = resolver.getImgUrl();
				
				bean.setAnswerCount(resolver.getAnswerCount());
				bean.setTitle(resolver.getTitle());
				bean.setImgUrl(imgUrl);
				bean.setImgCount(imgUrl.size());
				
				System.out.println("�������:"+bean.getTitle());
				System.out.println("�ش�����:"+bean.getAnswerCount());
				System.out.println("��ʼ����ͼƬ...................");
				
				DownLoader.download(bean, "C:\\Users\\WaterTree\\Desktop");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
