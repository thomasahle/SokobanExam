package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;

/**
 * Sprites are physical elements in the maze.
 * Their types fundamentally decide how they interact with each other
 * through the {@link uk.ac.ox.cs.sokobanexam.domainmodel.Rules} system.
 * In addition to their type, sprites can also contain extra information
 * to decide how they behave and may be displayed.
 * All sprites must have a constructor taking only a {@link uk.ac.ox.cs.sokobanexam.util.Point}.
 */

public interface Sprite {
	/**
	 * Returns the position associated with this sprite.
	 * @return          the position of the sprite, never null
	 */
	public Point point();
	/**
	 * Creates a new sprite that is equal to this in every way, but it's position.
	 * Since sprites are immutable, this is the only way to move a sprite of an unknown type.
	 * @param point		the created sprite
	 */
	public Sprite move(Point point);
	/**
	 * The entry point for {@link SpriteVisitor}'s.
	 * @param visitor   the visitor wanting to get the sprite dispatched
	 */
	public void accept(SpriteVisitor visitor);
}
