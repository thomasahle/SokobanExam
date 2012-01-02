package uk.ac.ox.cs.sokobanexam.model;

import uk.ac.ox.cs.sokobanexam.model.sprites.Sprite;

public interface Rules {
	boolean validateBoard(Board board);
	boolean validateMove(Board board, Point from, Point to);
	boolean validateInsertion(Board board, Point at, Sprite sprite);
}
