package uk.ac.ox.cs.sokobanexam.util;

/**
 * An enum representing the four most important directions, and their angles.
 */
public enum Dir {
	NORTH 	(Math.PI/2),
	EAST  	(0),
	SOUTH 	(Math.PI*3/2),
	WEST  	(Math.PI);
	
	public final double angle;
	Dir(double angle) {
		this.angle = angle;
	}
}
