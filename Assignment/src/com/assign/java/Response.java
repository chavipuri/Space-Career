package com.assign.java;

public class Response {
	private int question_id;
	private int user_id;
	private String response_txt;
	
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getResponse_txt() {
		return response_txt;
	}
	public void setResponse_txt(String response_txt) {
		this.response_txt = response_txt;
	}
}
