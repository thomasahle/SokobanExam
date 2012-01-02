package uk.ac.ox.cs.sokobanexam.model;

import uk.ac.ox.cs.sokobanexam.util.Dir;


public class Point {
	private final static Point[][] cache = new Point[Board.MAX_HEIGHT][Board.MAX_WIDTH];
	static {
		for (int y = 0; y < Board.MAX_HEIGHT; y++)
			for (int x = 0; x < Board.MAX_WIDTH; x++)
				cache[y][x] = new Point(x, y);
	}
	public static Point at(int x, int y) {
		assert 0 < x && 0 < y;
		if (x >= Board.MAX_WIDTH || y >= Board.MAX_HEIGHT)
			return new Point(x, y);
		return cache[y][x];
	}
	public final int x;
	public final int y;
	private Point(int x, int y) {
		this.x = x;
		this.y = y;
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
}
