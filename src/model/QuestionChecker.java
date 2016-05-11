package model;

import java.util.ArrayList;


public class QuestionChecker {

	private final double similarityRate;

	private ArrayList<QuestionTuning> databaseQuestions;

	/**
	 * 
	 */
	public QuestionChecker() {
		this.similarityRate = 0.5;
	}

	public QuestionChecker(double similarityRate) {
		this.similarityRate = similarityRate;
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

			if (checkSimilarity(q, databaseQuestions.get(i))>= similarityRate){
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

	public void printSimilarity(Question q) {


		QuestionTuning QE = new QuestionTuning(q);
		QE.printPunctuationMarks();
		System.out.println("Values of similarity for "+ q.toString());
		System.out.println(QE.getMostImportantWords());

		for (int i = 0; i < databaseQuestions.size(); i++) {
			double sim =checkSimilarity(q, databaseQuestions.get(i));

			System.out.println((i+1)+". "+sim+"% "+databaseQuestions.get(i).getQuestion().getQuestion()+"");
			System.out.println("    "+databaseQuestions.get(i).getMostImportantWords());

		}
	}
}
