package uk.ac.ox.cs.sokobanexam.model.sprites;

/**
 * Rooms are {@link Sprite}s that contain another sprite.
 * Sokoban generally disallows rooms within rooms per the rules, which
 * also removes the possibility of cycles.
 * The position of a room is uniquely determined by its inner sprite.
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
