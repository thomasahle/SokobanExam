package uk.ac.ox.cs.sokobanexam.domainmodel;

import uk.ac.ox.cs.sokobanexam.util.Dir;

public interface Rules {
	boolean validateBoard(Board board);
	boolean isPlayable(Board board);
	boolean validateMove(Board board, Dir dir);
	void applyMove(Board board, Dir dir);
	boolean isGameWon(Board board);
}
