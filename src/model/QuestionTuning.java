package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class QuestionTuning {
	
	
	public final static int[] ASCII_CODES_PUNCTUATION_MARKS={46, 44, 59, 63, 33, 191, 161, 39, 47, 92, 34, 41, 40, 95, 45, 32}; 
	
	public final static char[] PUNCTUATION_MARKS = new char[ASCII_CODES_PUNCTUATION_MARKS.length];

	private Question question;
	
	private HashSet<String> mostImportantWords;



	public QuestionTuning(Question q) {
		
		question = q;
		initializePunctuationMarks();
		processQuestion();
	}

	private void initializePunctuationMarks() {
		// TODO Auto-generated method stub
		for (int i = 0; i < ASCII_CODES_PUNCTUATION_MARKS.length; i++) {
			PUNCTUATION_MARKS[i]=(char) ASCII_CODES_PUNCTUATION_MARKS[i];
		}
		
	}

	private void processQuestion() {

		mostImportantWords = new HashSet<String>();


		String sentences = question.getQuestion() +" "+ question.getCorrectAnswer();	
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
				
				token = replaceAccents(token);
				words.add(token);
			}

		}



		return words;
	}

	private String replaceAccents(String token) {
	
		token =token.replace('á', 'a');
		token =token.replace('é', 'e');
		token =token.replace('í', 'i');
		token =token.replace('ó', 'o');
		token =token.replace('ö', 'o');
		token =token.replace('ú', 'u');
		token =token.replace('ü', 'u');
		
		return token;
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
				char letter = singleWord[j];
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



	public Question getQuestion() {
		return question;
	}

	public HashSet<String> getMostImportantWords() {
		return (HashSet<String>) mostImportantWords;
	}
	
	public void printPunctuationMarks(){
		System.out.println(PUNCTUATION_MARKS);
	}



}
