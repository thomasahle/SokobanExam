package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;

// Null object

public class Floor extends AbstractRoom {
	/**
	 * Creates a new, empty floor room with a default direction.
	 * @param point		the position of the new sprite
	 */
	public Floor(Point point) {
		super(new Nothing(point));
	}
	/**
	 * Creates a new floor with something within it.
	 * @param inner		the sprite inside the room
	 */
	public Floor(Sprite inner) {
		super(inner);
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public Room withInner(Sprite inner) {
		return new Floor(inner);
	}
	@Override
	public Sprite move(Point point) {
		return new Floor(inner().move(point));
	}
}
