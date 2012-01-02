package uk.ax.ox.cs.sokobanexam.util;

import uk.ac.ox.cs.sokobanexam.model.Point;

public enum Dir {
	NORTH (-1, 0),
	EAST  ( 0, 1),
	SOUTH ( 1, 0),
	WEST  ( 0,-1);
	
	private int dy;
	private int dx;
	Dir (int dy, int dx) {
		this.dy = dy;
		this.dx = dx;
	}
	
	/**
	 * The method's name is very short, as methods on enums should be.
	 * @param p
	 * @return 
	 */
	public Point plus(Point p) {
		return Point.at(p.x+dx, p.y+dy);
	}
}
