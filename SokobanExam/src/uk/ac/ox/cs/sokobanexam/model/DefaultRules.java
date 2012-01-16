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

/**
 * The default implementation of Sokoban Rules.
 * I chose to do them slightly different from the assignment,
 * specifically I wanted to be able to support chains and loops of arrows.
 */

public class DefaultRules implements Rules {
	
	@Override
	public ValidationResult validateMaze(Maze maze) {
		int humen = 0;
		for (Room room : maze.getRooms()) {
			if (room.inner() instanceof Human)
				humen++;
			// Arrows can't contain crates
			if (room instanceof Arrow && room.inner() instanceof Crate)
				return new ValidationResult("There can't be crates on arrows, or arrows under crates");
			// Walls can't contain anything
			if (room instanceof Wall && !(room.inner() instanceof Nothing))
				return new ValidationResult("There can't be anything on walls, or walls under anything");
		}
		// We can't have more than two humen
		if (humen > 1)
			return new ValidationResult("There can't be more than one player in the maze");
		// Otherwise everything is good!
		return new ValidationResult();
	}
	
	@Override
	public ValidationResult validateMazePlayable(Maze maze) {
		ValidationResult result = validateMaze(maze);
		if (result.isLegal() && !maze.getRoomsContaining(Human.class).iterator().hasNext())
			return new ValidationResult("The maze must contain a player to be playable");
		return result;
	}
	
	@Override
	public ValidationResult validateMove(Maze maze, Dir dir) {
		assert validateMaze(maze).isLegal();
		Point from = maze.getRoomsContaining(Human.class).iterator().next().point();
		Point to = from.plus(dir);
		
		// Check if we are within maze boundaries
		// Check if a human is at from
		// Check if a wall objects
		if (!maze.getPoints().contains(from))
			return new ValidationResult("You can't move from outside the maze");
		if (!maze.getPoints().contains(to))
			return new ValidationResult("You can't move out of the maze");
		if (!(maze.getRoom(from).inner() instanceof Human))
			return new ValidationResult("You can only move the player");
		if (maze.getRoom(to) instanceof Wall)
			return new ValidationResult("You can't move into a wall");
		
		// Check if an arrow objects (currently on arrow)
		if (maze.getRoom(from) instanceof Arrow) {
			Arrow arrow = (Arrow)maze.getRoom(from);
			if (from.plus(arrow.direction()) != to)
				return new ValidationResult("You must move out of the arrow in the specified direction");
		}
		// Check if an arrow objects (currently next to arrow)
		if (maze.getRoom(to) instanceof Arrow) {
			Arrow arrow = (Arrow)maze.getRoom(to);
			if (to.plus(arrow.direction()) == from)
				return new ValidationResult("You can't move backwards onto an arrow");
		}
		
		// Check if a crate is in the way, and it can't be moved
		Point next = to.plus(dir);
		if (maze.getRoom(to).inner() instanceof Crate
				&& (!maze.getPoints().contains(next)
					|| maze.getRoom(next) instanceof Wall
					|| maze.getRoom(next) instanceof Arrow
					|| !(maze.getRoom(next).inner() instanceof Nothing)))
			return new ValidationResult("You can't move that crate");
		
		return new ValidationResult();
	}
	
	@Override
	public void applyMove(Maze maze, Dir dir) {
		assert validateMove(maze, dir).isLegal();
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
