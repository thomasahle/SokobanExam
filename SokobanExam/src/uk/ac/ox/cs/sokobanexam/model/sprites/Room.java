package uk.ac.ox.cs.sokobanexam.model.sprites;

/**
 * Rooms are {@link Sprite}s that contain another sprite.
 * Sokoban generally disallows rooms within rooms per the rules, which
 * also removes the possibility of cycles.
 * The position of a room is uniquely determined by its inner sprite.
 * 
 * If the game was to be generalized to deeper levels of sprites, 
 * there are two directions it could take:
 * 
 *     * The simplest would be to make every Sprite a "Room".
 *       The Rules would ensure which types could be inside which,
 *       and the Controller would only have to put or change "top" elements.
 *     
 *     * Alternatively each type would define its "level" which would be
 *       similar to z-index'es in css in that stacking elements of different
 *       levels would order the stack with the lowest level on top.
 *       This would have the advantage over the first approach in that the
 *       UI would be more easy to manipulate, allowing inserting elements
 *       "under" already placed ones, like it is possible in the current code.
 */

public interface Room extends Sprite {
	/**
	 * Returns the sprite kept inside this room.
	 * @return          the inner sprite
	 */
	public Sprite inner();
	/**
	 * Creates a new room with equal type and properties to this one,
	 * but with another inner sprite. Since a rooms position is determined
	 * by its inner sprite, the new room may have a different position.
	 * @param inner		a new sprite to keep within the room
	 * @return			the created room
	 */
	public Room withInner(Sprite inner);
}
