package uk.ac.ox.cs.sokobanexam.domainmodel;

import uk.ac.ox.cs.sokobanexam.util.Point;


public interface Rules {
	boolean validateBoard(Board board);
	boolean isPlayable(Board board);
	boolean validateMove(Board board, Point from, Point to);
	void applyMove(Board board, Point from, Point to);
	boolean isGameWon(Board board);
}
