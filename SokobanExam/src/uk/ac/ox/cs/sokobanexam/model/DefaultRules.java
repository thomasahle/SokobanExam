package uk.ac.ox.cs.sokobanexam.model;

import uk.ac.ox.cs.sokobanexam.model.sprites.Arrow;
import uk.ac.ox.cs.sokobanexam.model.sprites.Crate;
import uk.ac.ox.cs.sokobanexam.model.sprites.Human;
import uk.ac.ox.cs.sokobanexam.model.sprites.Nothing;
import uk.ac.ox.cs.sokobanexam.model.sprites.Room;
import uk.ac.ox.cs.sokobanexam.model.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.model.sprites.Target;
import uk.ac.ox.cs.sokobanexam.model.sprites.Wall;
import uk.ac.ox.cs.sokobanexam.util.Dir;
import uk.ac.ox.cs.sokobanexam.util.Point;

public class DefaultRules implements Rules {
	
	@Override
	public boolean isMazeLegal(Maze maze) {
		int humen = 0;
		for (Room room : maze.getRooms()) {
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
	public boolean isMazePlayable(Maze maze) {
		return isMazeLegal(maze)
				&& maze.getRoomsContaining(Human.class).iterator().hasNext();
	}
	
	@Override
	public boolean isMoveLegal(Maze maze, Dir dir) {
		assert isMazeLegal(maze);
		Point from = maze.getRoomsContaining(Human.class).iterator().next().point();
		Point to = from.plus(dir);
		
		// Check if we are within maze boundaries
		// Check if a human is at from
		// Check if a wall objects
		if (   !maze.getPoints().contains(from)
			|| !maze.getPoints().contains(to)
			|| !(maze.getRoom(from).inner() instanceof Human)
			|| maze.getRoom(to) instanceof Wall)
			return false;
		
		// Check if an arrow objects (currently on arrow)
		if (maze.getRoom(from) instanceof Arrow) {
			Arrow arrow = (Arrow)maze.getRoom(from);
			if (from.plus(arrow.direction()) != to)
				return false;
		}
		// Check if an arrow objects (currently next to arrow)
		if (maze.getRoom(to) instanceof Arrow) {
			Arrow arrow = (Arrow)maze.getRoom(to);
			if (to.plus(arrow.direction()) == from)
				return false;
		}
		
		// Check if a crate is in the way, and it can't be moved
		Point next = to.plus(dir);
		if (maze.getRoom(to).inner() instanceof Crate
				&& (!maze.getPoints().contains(next)
					|| maze.getRoom(next) instanceof Wall
					|| maze.getRoom(next) instanceof Arrow
					|| !(maze.getRoom(next).inner() instanceof Nothing)))
			return false;
		
		return true;
	}
	
	@Override
	public void applyMove(Maze maze, Dir dir) {
		assert isMoveLegal(maze, dir);
		Point from = maze.getRoomsContaining(Human.class).iterator().next().point();
		Point to = from.plus(dir);
		
		// Move crate
		if (maze.getRoom(to).inner() instanceof Crate) {
			Point next = to.plus(dir);
			Sprite newCrate = maze.getRoom(to).inner().move(next);
			Room newRoom = maze.getRoom(next).withInner(newCrate);
			maze.putRoom(newRoom);
		}
		
		// Move Person
		maze.putRoom(maze.getRoom(to).withInner(new Human(to, dir)));
		
		// Clear space behind person
		maze.putRoom(maze.getRoom(from).withInner(new Nothing(from)));
	}
	
	@Override
	public boolean isMazeWon(Maze maze) {
		for (Room room : maze.getRoomsContaining(Crate.class)) {
			if (!(room instanceof Target)) {
				return false;
			}
		}
		return true;
	}

}
