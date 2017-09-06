package com.zhihu.spider;

import java.util.List;

public class QuestionBean {
	private String title;
	private int answerCount;
	private int imgCount;
	private List<String> imgUrl;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getImgCount() {
		return imgCount;
	}
	public void setImgCount(int imgCount) {
		this.imgCount = imgCount;
	}
	public List<String> getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(List<String> imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getAnswerCount() {
		return answerCount;
	}
	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}
	
	
	
}
