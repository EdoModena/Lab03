package it.polito.tdp.spellchecker.model;

public class Richword {
	private String word;
	private boolean correct;
	public Richword(String word, boolean correct) {
		this.word = word;
		this.correct = correct;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
}
