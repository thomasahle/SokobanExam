package uk.ac.ox.cs.sokobanexam.model;

import java.util.Set;

import uk.ac.ox.cs.sokobanexam.model.sprites.Room;
import uk.ac.ox.cs.sokobanexam.model.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.util.Point;

/**
 * Represents the maze in which everything happens.
 * This is the only non persistent object in the domain model, but it can
 * be easily cloned.
 */
public interface Maze extends Cloneable {
	/**
	 * Gets the height of the maze.
	 * @return		the number of rows in the maze
	 */
	public int getHeight();
	/**
	 * Gets the width of the maze.
	 * @return		the columns of rows in the maze
	 */
	public int getWidth();
	/**
	 * Gets a set of points containing all points inside the maze.
	 * @return		the points contained by the maze
	 */
	public Set<Point> getPoints();
	
	/**
	 * Gets all the rooms kept in the maze.
	 * This is guaranteed to be equal to the number of points contained by the maze.
	 * @return		an iterable over the rooms contained by the maze
	 */
	public Iterable<Room> getRooms();
	/**
	 * Gets the room at a certain point.
	 * @param point	the position of the wanted room
	 * @return		a room with room.point() = point
	 */
	public Room getRoom(Point point);
	/**
	 * Mutably adds a room to the maze, overwriting the previous room at its position.
	 * @param room	a new room to be added
	 */
	public void putRoom(Room room);
	/**
	 * Gets all the rooms which inner spites are of a certain type.
	 * @param type	the type of the inner sprites
	 * @return		an iterable over the rooms
	 */
	public Iterable<Room> getRoomsContaining(Class<? extends Sprite> type);
	/**
	 * Gets all the rooms which are themselves of a certain type.
	 * @param type	the type of the wanted rooms
	 * @return		an iterable over the rooms
	 */
	public Iterable<Room> getRoomsOfType(Class<? extends Sprite> type);
	
	
	public Maze clone();
}