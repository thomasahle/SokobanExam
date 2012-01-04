package uk.ac.ox.cs.sokobanexam.util;




public class Point {
	private final static int MAX_X = 50;
	private final static int MAX_Y = 50;
	private final static Point[][] cache = new Point[MAX_Y][MAX_X];
	static {
		for (int y = 0; y < MAX_Y; y++)
			for (int x = 0; x < MAX_X; x++)
				cache[y][x] = new Point(x, y);
	}
	public static Point at(int x, int y) {
		assert 0 < x && 0 < y;
		if (x >= MAX_X || y >= MAX_Y)
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
	/**
	 * Interpolates
	 * @param next
	 * @return
	 */
	public Point follow(Point next) {
		return Point.at(next.x+(next.x-x), next.y+(next.y-y));
	}
	@Override
	public int hashCode() {
		return x+y*MAX_X;
	}
	@Override
	public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point point = (Point)obj;
            return (x == point.x) && (y == point.y);
        }
        return super.equals(obj);
    }
	@Override
    public String toString() {
        return getClass().getName() + "[x=" + x + ",y=" + y + "]";
    }
}
