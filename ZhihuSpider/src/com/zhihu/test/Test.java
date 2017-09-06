package com.zhihu.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.zhihu.spider.Resolver;
import com.zhihu.spider.Spider;

public class Test {
	@org.junit.Test
	public void testGetContent(){
		Spider.getContent("30502941","0");
	}
	
	@org.junit.Test
	public void testString2Json(){
		List<String> list = new Resolver("30502941").getImgUrl();
		System.out.println(list.size());
	}
	
	@org.junit.Test
	public void testGetTitle(){
		String title = new Resolver("30502941").getTitle();
		System.out.println(title);
	}
	
	@org.junit.Test
	public void testCreateFile(){
		String pathname = "C:\\Users\\WaterTree\\Desktop\\"+"²âÊÔÎÄ¼þ¼Ð";
		File file = new File(pathname);
		if(!file.exists()){
			
		
		}
		
	}
}
