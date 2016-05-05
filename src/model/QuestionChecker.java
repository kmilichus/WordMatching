package model;

import java.util.ArrayList;


public class QuestionChecker {

	public static final double SIMILARITY_LOWER_RATE = 0.5;

	private ArrayList<QuestionTuning> databaseQuestions;

	/**
	 * 
	 */
	public QuestionChecker() {

		
	}

	/**
	 * 
	 * @param dbQuestions
	 */
	public void initializeQuestions(ArrayList<Question> dbQuestions){
		databaseQuestions = new ArrayList<QuestionTuning>();
		for (int i = 0; i < dbQuestions.size(); i++) {
			databaseQuestions.add(new QuestionTuning(dbQuestions.get(i)));
		}

	}

	/**
	 * 
	 * @param q
	 * @return
	 */
	public ArrayList<Question> buildSimilarWords(Question q){

		ArrayList<Question> listOfSimilarQuestions = new ArrayList<Question>();
		
		for (int i = 0; i < databaseQuestions.size(); i++) {
			
			if (checkSimilarity(q, databaseQuestions.get(i))>= SIMILARITY_LOWER_RATE){
				listOfSimilarQuestions.add(databaseQuestions.get(i).getQuestion());
			}
		}
		return listOfSimilarQuestions;
	}

	/**
	 * 
	 * @param q
	 * @param toBeChecked
	 * @return
	 */
	public double checkSimilarity(Question q, QuestionTuning toBeChecked){
		QuestionTuning actualQuestion = new QuestionTuning(q);
		double size = actualQuestion.getMostImportantWords().size();
		double numberOfAppereances = 0;


		for (String word : actualQuestion.getMostImportantWords()) {

			if (toBeChecked.getMostImportantWords().contains(word)) 
				numberOfAppereances++;	
		}

		return numberOfAppereances/size;

	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<QuestionTuning> getDatabaseQuestions() {
		return databaseQuestions;
	}

	/**
	 * 
	 * @param databaseQuestions
	 */
	public void setDatabaseQuestions(ArrayList<QuestionTuning> databaseQuestions) {
		this.databaseQuestions = databaseQuestions;
	}
	
	
	public boolean addQuestion(Question q){
		QuestionTuning qe = new QuestionTuning(q);
		return databaseQuestions.add(qe);
	}







}
