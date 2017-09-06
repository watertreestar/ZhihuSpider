package com.zhihu.spider;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

public class Spider {
	private static final String QUESTION_URL_RGE = "\\d+";
	
	public static String getContent(String questionId,String offset){
		if(questionId.matches(QUESTION_URL_RGE)){
			try {
				String result = null;
				HttpClient httpClient = HttpClients.createDefault();
				HttpGet get = new HttpGet("https://www.zhihu.com/api/v4/questions/"+questionId+"/answers?include=data%5B*%5D.is_normal%2Cadmin_closed_comment%2Creward_info%2Cis_collapsed%2Cannotation_action%2Cannotation_detail%2Ccollapse_reason%2Cis_sticky%2Ccollapsed_by%2Csuggest_edit%2Ccomment_count%2Ccan_comment%2Ccontent%2Ceditable_content%2Cvoteup_count%2Creshipment_settings%2Ccomment_permission%2Ccreated_time%2Cupdated_time%2Creview_info%2Cquestion%2Cexcerpt%2Crelationship.is_authorized%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%2Cupvoted_followees%3Bdata%5B*%5D.mark_infos%5B*%5D.url%3Bdata%5B*%5D.author.follower_count%2Cbadge%5B%3F(type%3Dbest_answerer)%5D.topics&offset="+offset+"&limit=20&sort_by=default");
				get.setHeader("authorization", "oauth c3cef7c66a1843f8b3a9e6a1e3160e20");
				HttpResponse response = httpClient.execute(get);
				if(response.getStatusLine().getStatusCode() == 200){
					//返回的是 json数据
				    result = EntityUtils.toString(response.getEntity());
//				    System.out.println(result);
				    get.releaseConnection();
				    
				    return result;
				}else{
					System.out.println("请求失败");
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}else{
			System.out.println("问题Id不合法");
			return null;
		}
	}
	
	
	public static String getIndexContent(String questionId){
		try {
			String result = null;
			HttpClient httpClient = HttpClients.createDefault();
			HttpGet get = new HttpGet("https://www.zhihu.com/question/"+questionId);
			HttpResponse response = httpClient.execute(get);
			if(response.getStatusLine().getStatusCode() == 200){
			    result = EntityUtils.toString(response.getEntity());
			    return result;
			}else{
				System.out.println("请求失败");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
