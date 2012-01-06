package uk.ac.ox.cs.sokobanexam.util;

/**
 * A useful class for working with positions in a grid.
 * Since point objects are used everywhere in the Sokoban app,
 * this class utilizes the fly weight pattern to keep memory and computational costs low. 
 */
public class Point {
	private final static int MAX_X = 50;
	private final static int MAX_Y = 50;
	private final static Point[][] cache = new Point[MAX_Y][MAX_X];
	static {
		for (int y = 0; y < MAX_Y; y++)
			for (int x = 0; x < MAX_X; x++)
				cache[y][x] = new Point(x, y);
	}
	private Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public final int x;
	public final int y;
	public static Point at(int x, int y) {
		if (x < 0 || x >= MAX_X || y < 0 || y >= MAX_Y)
			return new Point(x, y);
		return cache[y][x];
	}
	public Point plus(Dir direction) {
		switch (direction) {
		case NORTH: return Point.at(x, y-1);
		case EAST: return Point.at(x+1, y);
		case SOUTH: return Point.at(x, y+1);
		case WEST: return Point.at(x-1, y);
		}
		return null;
	}
	@Override
    public String toString() {
        return getClass().getName() + "[x=" + x + ",y=" + y + "]";
    }
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Point)) return false;
		return ((Point)o).x == x && ((Point)o).y == y;
	}
}
