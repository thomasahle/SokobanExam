package uk.ac.ox.cs.sokobanexam.model;

import uk.ac.ox.cs.sokobanexam.model.sprites.Sprite;

public interface Rules {
	boolean validateBoard(IBoard board);
	boolean validateMove(IBoard board, Point from, Point to);
	boolean validateInsertion(IBoard board, Point at, Sprite sprite);
	void applyMove(IBoard board, Point from, Point to);
	boolean isGameWon(IBoard board);
}
