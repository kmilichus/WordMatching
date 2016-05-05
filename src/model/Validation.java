package model;

import java.util.ArrayList;

public class Validation {

	private QuestionChecker checker;




	public Validation(){
		checker = new QuestionChecker();

	}


	public void checkUnboundQuestions(int categoryID){

		//get dbQuestions With the CategoryID

		//call the method that initializes the DBQuestion
		//checker.initializeQuestions(dbQuestions);

		//Load the new Questions from Excel

		//loop over the new questions from excel
		//ArrayList<Question> duplicates =	checker.buildSimilarWords(question);
		//if arraylist size == 0, There is no duplicate questions you can add it to the DB
		//else display on the interface for human check

		// Every time a question is accepted you must invoke this method
		//		checker.addQuestion(question);
		//And also add it to the database
	}





}
