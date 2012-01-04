package uk.ac.ox.cs.sokobanexam.domainmodel;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Floor;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Nothing;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Room;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.util.FilterIterator;
import uk.ac.ox.cs.sokobanexam.util.IterableAdapter;
import uk.ac.ox.cs.sokobanexam.util.MapIterator;
import uk.ac.ox.cs.sokobanexam.util.Point;
import uk.ac.ox.cs.sokobanexam.util.PointRangeSet;

// Alternative representation:
//     A set of sprites all containing their own coordinates (passer bedre med beskrivelsen), z-index?

public class DefaultBoard implements Board {
	
	// Class Invariant:
	//		mMap.length >= 1
	//		mMap[y].length >= 1
	private Room[][] mMap;
	
	public DefaultBoard(int width, int height) {
		if (width <= 0 || height <= 0)
			throw new IllegalArgumentException("Board size: "+width+"x"+height);
		
		// Initialize class invariant:
		mMap = new Room[height][width];
		for (Point point : getPoints())
			putRoom(new Floor(new Nothing(point)));
	}
	
	@Override
	public int getHeight() {
		return mMap.length;
	}

	@Override
	public int getWidth() {
		return mMap[0].length;
	}
	
	@Override
	public Set<Point> getPoints() {
		return new PointRangeSet(getWidth(), getHeight());
	}
	
	@Override
	public Iterable<Room> getRooms() {
		return new IterableAdapter<Room>(new MapIterator<Point,Room>(getPoints().iterator()) {
			@Override public Room applyMap(Point point) {
				return getRoom(point);
			}
		});
	}

	@Override
	public Room getRoom(Point point) {
		return mMap[point.y][point.x];
	}

	@Override
	public void putRoom(Room room) {
		Point point = room.point();
		mMap[point.y][point.x] = room;
	}

	@Override
	public Iterable<Room> getRoomsContaining(final Class<? extends Sprite> type) {
		return new IterableAdapter<Room>(new FilterIterator<Room>(getRooms().iterator()) {
			@Override public boolean isGood(Room room) {
				return type.isInstance(room.inner()); 
			}
		});
	}

	@Override
	public Iterable<Room> getRoomsOfType(final Class<? extends Sprite> type) {
		return new IterableAdapter<Room>(new FilterIterator<Room>(getRooms().iterator()) {
			@Override public boolean isGood(Room room) {
				return type.isInstance(room); 
			}
		});
	}
	
	@Override
	public DefaultBoard clone() {
		DefaultBoard clone = new DefaultBoard(getWidth(), getHeight());
		for (Room room : getRooms())
			clone.putRoom(room);
		return clone;
	}
}
