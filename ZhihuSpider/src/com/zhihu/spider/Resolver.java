package com.zhihu.spider;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//解析获取到的json 数据
public class Resolver {
	public Resolver(String questionId){
		this.questionId = questionId;
	}
	private String questionId;
	private static final String IMG_URL_REG = "<noscript><img.+?src=\"(.+?)\"";
	private static final String TITLE_REG = "<h1.+?class=\"QuestionHeader-title\">(.+?)</h1>";
	public  List<String> getImgUrl(){
		String content = Spider.getContent(questionId, "0");
		if(content!=null){
			List<String> urlList = new ArrayList<String>();
			String isEnd = "false";
			int i = 0;
			JSONObject json_obj;
			JSONObject paging;
			JSONArray data;
			Pattern p = Pattern.compile(IMG_URL_REG);
			Matcher m = null;
			while(isEnd == "false"){
				json_obj = JSONObject.fromObject(content);
				paging = (JSONObject) json_obj.get("paging");
				isEnd = paging.get("is_end").toString();
				data = JSONArray.fromObject(json_obj.get("data"));
				for (int j = 0; j < data.size(); j++) {
					JSONObject answer = JSONObject.fromObject(data.get(j));
					String answer_content = answer.getString("content");
					m = p.matcher(answer_content);
					while(m.find()){
						//System.out.println(m.group(1));
						urlList.add(m.group(1));
					}
				}
				i+=19;
				content = Spider.getContent(questionId, i+"");
				
				/*JSONObject answer = JSONObject.fromObject(data.get(0));
				String answer_content = answer.getString("content");
				m = p.matcher(answer_content);
				while(m.find()){
					System.out.println(m.group(1));
				}*/
				
			}
			return urlList;
		}
		return null;
	}
	
	public  int getAnswerCount(){
		String content = Spider.getContent(questionId, "0");
		if(content!=null){
			JSONObject json_obj = JSONObject.fromObject(content);
			JSONObject paging = (JSONObject) json_obj.get("paging");
			return (int)paging.get("totals");
		}
		return 0;
	}
	
	public  String getTitle(){
		String indexContent = Spider.getIndexContent(questionId);
		if(indexContent==null){
			return "问题标题未知";
		}
		Matcher m = Pattern.compile(TITLE_REG).matcher(indexContent);
		if(m.find()){
			return m.group(1);
		}
		return "问题标题未知";
	}
}

