package uk.ac.ox.cs.sokobanexam.domainmodel;

import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Arrow;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Crate;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Floor;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Human;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Nothing;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Room;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Target;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Wall;
import uk.ac.ox.cs.sokobanexam.util.Dir;
import uk.ac.ox.cs.sokobanexam.util.Point;

public class DefaultRules implements Rules {
	
	@Override
	public boolean validateBoard(Board board) {
		int humen = 0;
		for (Room room : board.getRooms()) {
			if (room.inner() instanceof Human)
				humen++;
			// Arrows can't contain crates
			if (room instanceof Arrow && room.inner() instanceof Crate)
				return false;
			// Walls can't contain anything
			if (room instanceof Wall && !(room.inner() instanceof Nothing))
				return false;
		}
		// We can't have more than two humen
		if (humen > 1)
			return false;
		// Otherwise everything is good!
		return true;
	}
	
	@Override
	public boolean isPlayable(Board board) {
		return validateBoard(board)
				&& board.getRoomsContaining(Human.class).iterator().hasNext();
	}
	
	@Override
	public boolean validateMove(Board board, Point from, Point to) {
		assert validateBoard(board);
		
		// Check if we are within board boundaries
		// Check if a human is at from
		// Check if a wall objects
		if (   !board.getPoints().contains(from)
			|| !board.getPoints().contains(to)
			|| !(board.getRoom(from).inner() instanceof Human)
			|| board.getRoom(to).inner() instanceof Wall)
			return false;
		
		// Check if an arrow objects (currently on arrow)
		if (board.getRoom(from) instanceof Arrow) {
			Arrow arrow = (Arrow)board.getRoom(from);
			if (from.plus(arrow.getDirection()) != to)
				return false;
		}
		// Check if an arrow objects (currently next to arrow)
		if (board.getRoom(to) instanceof Arrow) {
			Arrow arrow = (Arrow)board.getRoom(to);
			if (to.plus(arrow.getDirection()) == from)
				return false;
		}
		
		// Check if a crate is in the way, and it can't be moved
		Point next = from.follow(to);
		if (board.getRoom(to).inner() instanceof Crate
				&& (board.getRoom(next) instanceof Wall
					|| board.getRoom(next) instanceof Arrow
					|| !(board.getRoom(next).inner() instanceof Nothing)))
			return false;
		
		return true;
	}
	
	@Override
	public void applyMove(Board board, Point from, Point to) {
		assert validateMove(board, from, to);
		if (board.getRoom(to) instanceof Crate) {
			Point next = from.follow(to);
			// Move crate
			board.setRoom(next, board.getRoom(next).setInner(board.getRoom(to).inner()));
		}
		// Move Person
		board.setRoom(to, board.getRoom(to).setInner(board.getRoom(from).inner()));
		// Clear space behind person
		board.setRoom(from, board.getRoom(from).setInner(new Nothing(from)));
	}
	
	@Override
	public boolean isGameWon(Board board) {
		for (Room room : board.getRoomsContaining(Crate.class))
			if (!(room instanceof Target))
				return false;
		return true;
	}

}
