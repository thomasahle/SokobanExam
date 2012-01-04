package uk.ac.ox.cs.sokobanexam.domainmodel;

import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite.SemanticType;
import uk.ac.ox.cs.sokobanexam.util.Dir;
import uk.ac.ox.cs.sokobanexam.util.Point;

public class DefaultRules implements Rules {

	@Override
	public boolean validateBoard(Board board) {
		int humen = 0;
		for (Point point : board.getContainedPoints()) {
			int targets = 0;
			int solids = 0;
			int humenAndBlocks = 0;
			boolean expectingSolid = false;
			for (Sprite sprite : board.getSpritesAt(point)) {
				if (sprite.type() == SemanticType.HUMAN)
					humen++;
				if (sprite.type() == SemanticType.TARGET)
					targets++;
				if (sprite.type().solid && sprite.type() != SemanticType.HUMAN)
					solids++;
				if (sprite.type().solid && sprite.type() != SemanticType.ARROW_N
						 && sprite.type() != SemanticType.ARROW_E
						 && sprite.type() != SemanticType.ARROW_S
						 && sprite.type() != SemanticType.ARROW_W)
					humenAndBlocks++;
				
				// Check solids are above flats
				if (sprite.type().solid && !expectingSolid)
					expectingSolid = true;
				if (!sprite.type().solid && expectingSolid)
					return false;
			}
			
			// Check only one target per field
			if (targets > 1)
				return false;
			
			// Check no two solid, nonhumen are on the same field
			if (solids > 1)
				return false;
			
			// Check that humen are not on non-arrow solids
			if (humenAndBlocks > 1)
				return false;
		}
		
		// Check no more than one Human
		if (humen > 1)
			return false;
		
		return true;
	}
	
	@Override
	public boolean isPlayable(Board board) {
		return validateBoard(board)
				&& board.getOccupiedPoints(SemanticType.HUMAN).size() == 1;
	}
	
	@Override
	public boolean validateMove(Board board, Point from, Point to) {
		// Check if a human is at from
		// Check if we are within board boundaries
		// Check if a wall objects
		if (!board.getOccupiedPoints(SemanticType.HUMAN).contains(from)
			|| !board.getContainedPoints().contains(from)
			|| !board.getContainedPoints().contains(to)
			|| board.getOccupiedPoints(SemanticType.WALL).contains(to))
			return false;
		
		// Check if an arrow objects (currently on arrow)
		if (board.getOccupiedPoints(SemanticType.ARROW_N).contains(from) && from.plus(Dir.NORTH) != to
				|| board.getOccupiedPoints(SemanticType.ARROW_E).contains(from) && from.plus(Dir.EAST) != to
				|| board.getOccupiedPoints(SemanticType.ARROW_S).contains(from) && from.plus(Dir.SOUTH) != to
				|| board.getOccupiedPoints(SemanticType.ARROW_W).contains(from) && from.plus(Dir.WEST) != to)
			return false;
		
		// Check if an arrow objects (currently next to arrow)
		if (board.getOccupiedPoints(SemanticType.ARROW_N).contains(to) && from.plus(Dir.SOUTH) == to
				|| board.getOccupiedPoints(SemanticType.ARROW_E).contains(to) && from.plus(Dir.WEST) == to
				|| board.getOccupiedPoints(SemanticType.ARROW_S).contains(to) && from.plus(Dir.NORTH) == to
				|| board.getOccupiedPoints(SemanticType.ARROW_W).contains(to) && from.plus(Dir.EAST) == to)
			return false;
		
		// Check if a crate is in the way, and it can't be moved
		Point next = from.follow(to);
		if (board.getOccupiedPoints(SemanticType.CRATE).contains(to)
				&& board.getTopSpriteAt(next).type().solid)
			return false;
		
		return true;
	}
	
	@Override
	public void applyMove(Board board, Point from, Point to) {
		assert validateMove(board, from, to);
		if (board.getTopSpriteAt(to).type() == SemanticType.CRATE)
			board.insertSpriteAt(from.follow(to), board.deleteTopSpriteAt(to));
		board.insertSpriteAt(to, board.deleteTopSpriteAt(from));
	}

	@Override
	public boolean isGameWon(Board board) {
		return board.getOccupiedPoints(SemanticType.TARGET).containsAll(
				board.getOccupiedPoints(SemanticType.CRATE));
	}

	
}
