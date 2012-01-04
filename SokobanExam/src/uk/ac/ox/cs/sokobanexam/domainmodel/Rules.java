package uk.ac.ox.cs.sokobanexam.domainmodel;

import uk.ac.ox.cs.sokobanexam.util.Dir;
import uk.ac.ox.cs.sokobanexam.util.Point;

// TODO: Build in cheating

public interface Rules {
	boolean validateBoard(Board board);
	boolean isPlayable(Board board);
	boolean validateMove(Board board, Point from, Dir dir);
	void applyMove(Board board, Point from, Dir dir);
	boolean isGameWon(Board board);
}
