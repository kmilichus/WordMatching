package model;

import java.util.ArrayList;
import java.util.TreeMap;

public class QuestionChecker {
	
	public final static int[] PUNCTUATION_MARKS ={'.', ',',';','?','!','¿','¡','\'','/','\\','"',')','(','—','_','-',' '};

	
	private ArrayList<QuestionTuning> databaseQuestions;

	private TreeMap<QuestionTuning, ArrayList<QuestionTuning>> possibleDuplicates;
	
	
	
	public QuestionChecker() {
		
		
	}
	
	public void downloadDbQuestions (int category){
		
	}
	
	
	
	
	public void checkNewQuestions(){
		
		
	}
	
	
	

	public ArrayList<QuestionTuning> getDatabaseQuestions() {
		return databaseQuestions;
	}

	public void setDatabaseQuestions(ArrayList<QuestionTuning> databaseQuestions) {
		this.databaseQuestions = databaseQuestions;
	}
	
	
	
	
	
	

}
