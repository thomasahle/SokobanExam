package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;

/**
 * Sprites are physical elements in the maze.
 * Their types fundamentally decide how they interact with each other,
 * through the {@link uk.ac.ox.cs.sokobanexam.domainmodel.Rules} system.
 * In adition to their type, sprites can also contain extra information
 * to decide how they behave and may be displayed.
 */

public interface Sprite {
	public Point point();
	public Sprite move(Point point);
	public void accept(SpriteVisitor visitor);
}
