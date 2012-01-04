package uk.ac.ox.cs.sokobanexam.domainmodel;


public interface Rules {
	boolean validateBoard(Board board);
	boolean isPlayable(Board board);
	boolean validateMove(Board board, Point from, Point to);
	void applyMove(Board board, Point from, Point to);
	boolean isGameWon(Board board);
}
