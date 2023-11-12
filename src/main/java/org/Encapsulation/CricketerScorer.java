package org.Encapsulation;

public class CricketerScorer {

	private int score;
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public static void main(String[] args) {
		CricketerScorer scorer = new CricketerScorer();
		scorer.score = scorer.score + 4;
		System.out.println(scorer.score);
		int score = scorer.getScore();
		scorer.setScore(8);
		System.out.println(score);
		
		System.out.println(scorer.getScore());
	}

}
