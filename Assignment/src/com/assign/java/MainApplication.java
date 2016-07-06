package com.assign.java;

import java.sql.SQLException;
import java.util.Scanner;

public class MainApplication {

	static Scanner scanner = new Scanner(System.in);
	private static String prev_id;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			System.out.println("Lets play a game");
			System.out.println("What is the ideal space career for you?");
			System.out.println("We will ask you some simple questions. Please answer with yes/no");
			System.out.println("Lets Begin!!!");
			User u1 = new User();
			System.out.println("Please Enter your Name");
			String answer = scanner.nextLine();
			
			u1.setName(answer);
			//Insert user details in database
			try {
				DbManager.Insert(u1);
				//System.out.println("Inserted");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//start the quiz with the first question in database	
		   startQuiz(u1,"1");	
		   
		
	}
	
	//recursive function which displays questions to the subject on the basis of their response
	//Also leads to determining the career
	public static void startQuiz(User u1, String id){
		
		Question q = new Question();
		Career c =new Career();
		if (id != null){
			
			try {
				q = DbManager.SelectQues(id);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			   
			System.out.println(q.getQues_txt());
			String answer = scanner.nextLine();
			Response r = new Response();
			r.setResponse_txt(answer);
			r.setQuestion_id(q.getQuestion_id());
			try {
				DbManager.insertResponse(u1,r);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(answer.equalsIgnoreCase("yes")){
				prev_id=id;
				startQuiz(u1,q.getLink_ques_yes());
			}
			else if(answer.equalsIgnoreCase("no")){
				prev_id=id;
				startQuiz(u1,q.getLink_ques_no());
			 }
			 else{
				System.out.println("Please answer with yes/no");
				startQuiz(u1,id);
			}
		   
		}
		else{
			try {
				c = DbManager.getCareer(u1,prev_id);
				System.out.println("Your ideal Space Career is --------->>>   " + c.getCareer() );
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
