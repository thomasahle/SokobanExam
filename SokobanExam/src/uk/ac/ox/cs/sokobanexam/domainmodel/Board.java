package uk.ac.ox.cs.sokobanexam.domainmodel;

import java.util.Set;

import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Room;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.util.Point;

//TODO: Change name to Maze?

public interface Board {
	
	public int getHeight();

	public int getWidth();
	
	
	public Iterable<Room> getRooms();
	
	public Room getRoom(Point point);
	
	public void setRoom(Point point, Room room);
	
	public Iterable<Room> getRoomsContaining(Class<? extends Sprite> type);
	
	public Iterable<Room> getRoomsOfType(Class<? extends Sprite> type);
	
	
	public Set<Point> getPoints();
}