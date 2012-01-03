package uk.ac.ox.cs.sokobanexam.model;

import uk.ac.ox.cs.sokobanexam.model.sprites.Crate;
import uk.ac.ox.cs.sokobanexam.model.sprites.Sprite;

public class DefaultRules implements Rules {

	@Override
	public boolean validateBoard(IBoard board) {
		
		// Check 1 Human (This might not hold during edit)
		
		// Check no two solids on same field
		// Check no Human on wall or crate
		// Check only one target per field
		
		// Maybe: Check no walls or arrows on targets
		
		return false;
	}

	@Override
	public boolean validateMove(IBoard board, Point from, Point to) {
		// Check if from is a human
		// Check if board boundaries object
		// Check if a wall objects
		// Check if an arrow objects
		// Check if a crate is in the way, and if it may be moved
		return false;
	}

	@Override
	public boolean validateInsertion(IBoard board, Point at, Sprite sprite) {
		// May not be necessary, just run validateBoard
		return false;
	}

	@Override
	public void applyMove(IBoard board, Point from, Point to) {
		assert validateMove(board, from, to);
		if (board.getTopSpriteAt(to) instanceof Crate) {
			Point next = Point.at(to.x+(to.x-from.x), to.y+(to.y-from.y));
			board.insertSpriteAt(next, board.deleteTopSpriteAt(to));
		}
		board.insertSpriteAt(to, board.deleteTopSpriteAt(from));
	}

	@Override
	public boolean isGameWon(IBoard board) {
	    for (Point point : board.getTargetPoints())
	        if(!(board.getTopSpriteAt(point) instanceof Crate))
	            return false;
	    return true;
	}
}
