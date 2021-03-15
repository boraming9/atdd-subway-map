package com.shinhancard.smalltalk.dto;

public class SmalltalkResponse {
	
	String requestUserId;
	String userId;
	String message;
	String chatbot_url;
	
	public String getUserId() {
		return userId;
	}
	public String getMessage() {
		return message;
	}
	public String getChatbot_url() {
		return chatbot_url;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setChatbot_url(String chatbot_url) {
		this.chatbot_url = chatbot_url;
	}
	public void setRequestUserId(String requestUserId) {
		this.requestUserId = requestUserId;
	}
	
}
