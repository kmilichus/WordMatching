package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class QuestionTuning {

	public final static char[] PUNCTUATION_MARKS ={'.', ',',';','?','!','¿','¡','\'','/','\\','"',')','(','—','_','-',' '};

	private Question question;
	
	private HashSet<String> mostImportantWords;



	public QuestionTuning() {
		
		question = null;
		initializeWords();
	}

	private void initializeWords() {

		mostImportantWords = new HashSet<String>();


		String sentences = question + correctAnswer;	
		sentences = sentences.toLowerCase();


		// Removing words with less than 3 chars, spaces and spanish plurals
		ArrayList<String> filteredWords= removeShortWords(sentences);

		//removing special characeters 
		mostImportantWords.addAll(removeSpacesAndChars(filteredWords));

	}

	/**
	 * 
	 * @param sentences
	 * @return
	 */
	private ArrayList<String> removeShortWords(String sentences) {
		ArrayList<String> words = new ArrayList<String>();

		StringTokenizer stk = new StringTokenizer(sentences);

		while (stk.hasMoreElements()) {

			String token = stk.nextToken();

			// removing short words
			if (token.length()>3) {

				// Looking for spanish plurals
				if (token.charAt(token.length()-1)=='s') {

					if (token.charAt(token.length()-2)=='e') {

						token = token.substring(0,token.length()-2);
					} else {
						token = token.substring(0,token.length()-1);
					}

				}
				words.add(token);
			}

		}



		return words;
	}

	/**
	 * 
	 * @param words
	 * @return
	 */
	public HashSet<String> removeSpacesAndChars(ArrayList<String> words){

		HashSet<String> finalWords = new HashSet<String>();

		for (int i = 0; i < words.size(); i++) {	

			StringBuilder sb  = new StringBuilder();
			char[] singleWord = words.get(i).toCharArray();

			for (int j = 0; j < singleWord.length; j++) {				
				char letter = question.charAt(j);	
				if (!isPunctuation(letter)) {
					sb.append(letter);
				}

			}

			finalWords.add(sb.toString());

		}

		return finalWords;
	}

	/**
	 * 
	 * @param letter
	 * @return
	 */
	public boolean isPunctuation(char letter){

		boolean is = false;

		for (int j = 0; j < PUNCTUATION_MARKS.length && !is; j++) {

			if (letter==PUNCTUATION_MARKS[j]) 
				is= true;

		}
		return is;
	}

	/**
	 * 
	 * @param query
	 * @return
	 */
	public double checkSimilarity(QuestionTuning query){

		double size = mostImportantWords.size();
		double numberOfAppereances = 0;


		for (String word : query.getMostImportantWords()) {

			if (mostImportantWords.contains(word)) 
				numberOfAppereances++;	
		}

		return numberOfAppereances/size;

	}

	public Question getQuestion() {
		return question;
	}

	public HashSet<String> getMostImportantWords() {
		return (HashSet<String>) mostImportantWords;
	}



}
