package model;

public class Question {

	private static final int[] LANGUAGE_CONSTRAINS = {10000, 20000};
	private static final int[] CATEGORY_CONSTRAINS = {100, 900};

	private String question, correctAnswer, wrongAnswer, author;
	private int language, region, category, creationType;

	public Question() {
	}

	public Question(String question, String correctAnswer, String wrongAnswer, int language, int region, int category) throws FormatException {
		validateAll(question, correctAnswer, wrongAnswer, language, region, category);
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.wrongAnswer = wrongAnswer;
		this.language = language;
		this.region = region;
		this.category = category;
		this.author = "root";
	}

	private void validateAll(String question, String correctAnswer, String wrongAnswer, int language, int region, int category) throws FormatException {
		if(question == null || correctAnswer == null || wrongAnswer == null)
			throw new FormatException("Question and answers must not be null");
		if(language < LANGUAGE_CONSTRAINS[0] || language > LANGUAGE_CONSTRAINS[1] || language % LANGUAGE_CONSTRAINS[0] != 0)
			throw new FormatException("Invalid language code");
		int regionCode = region %= 1000000;
		if(region != 0 && (regionCode < 1 || regionCode >= 10000))
			throw new FormatException("Invalid region code");
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getWrongAnswer() {
		return wrongAnswer;
	}

	public void setWrongAnswer(String wrongAnswer) {
		this.wrongAnswer = wrongAnswer;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getLanguage() {
		return language;
	}

	public void setLanguage(int language) {
		this.language = language;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getCreationType() {
		return creationType;
	}

	public void setCreationType(int creationType) {
		this.creationType = creationType;
	}

	@Override
	public String toString() {
		return "Question [question=" + question + ", correct=" + correctAnswer + ", wrong=" + wrongAnswer + "]";
	}

}
