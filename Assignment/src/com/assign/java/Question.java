package com.assign.java;



public class Question {
	private int question_id;
	private String ques_txt;
	private String link_ques_yes;
	private String link_ques_no;

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	
	public String getQues_txt() {
		return ques_txt;
	}

	public void setQues_txt(String ques_txt) {
		this.ques_txt = ques_txt;
	}

	public String getLink_ques_no() {
		return link_ques_no;
	}

	public void setLink_ques_no(String link_ques_no) {
		this.link_ques_no = link_ques_no;
	}

	public String getLink_ques_yes() {
		return link_ques_yes;
	}

	public void setLink_ques_yes(String link_ques_yes) {
		this.link_ques_yes = link_ques_yes;
	}
	

}
