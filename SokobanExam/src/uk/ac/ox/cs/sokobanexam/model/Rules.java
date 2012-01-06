package uk.ac.ox.cs.sokobanexam.model;

import uk.ac.ox.cs.sokobanexam.util.Dir;

/**
 * The rules decide how sprites of different types interact with each other.
 * Specifically the rules are responsible for deciding things like what happens
 * during moves, and which moves and mazes can be considered valid.
 * The validation methods return a {@link ValidationResult} object from which
 * one can learn the reason a validation failed (if it did).
 */
public interface Rules {
	/**
	 * Checks if any general invariants assumes by the rules are violated.
	 * @param maze		the maze to check
	 * @return			an object telling if the maze was legal
	 */
	ValidationResult validateMaze(Maze maze);
	/**
	 * Assuming that the maze is legal, this checks some extra parameters
	 * that are necessary for the game to be playable.
	 * An example could be that the maze contains a movable sprite.
	 * @param maze		the maze to check
	 * @return			an object telling if the maze is ready to start playing
	 */
	ValidationResult validateMazePlayable(Maze maze);
	/**
	 * Checks if the player should be considered to have won the game.
	 * @param maze		the maze to check
	 * @return			true if the rules judges the player to have won, false otherwise
	 */
	boolean isMazeWon(Maze maze);
	/**
	 * Assuming the maze is playable, checks if the move represented by dir
	 * is allowed by the rules is going to leave the maze in a valid state.
	 * @param maze		the maze to check
	 * @param dir		the move to check
	 * @return			an object telling if the move can be applied
	 */
	ValidationResult validateMove(Maze maze, Dir dir);
	/**
	 * Assuming the move represented by dir is legal, applies it to the maze.
	 * @param maze		the maze to apply the move to
	 * @param dir		the move to apply
	 */
	void applyMove(Maze maze, Dir dir);
}
