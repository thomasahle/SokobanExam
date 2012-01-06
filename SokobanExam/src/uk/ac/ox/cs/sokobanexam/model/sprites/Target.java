package uk.ac.ox.cs.sokobanexam.model.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;

public class Target extends AbstractRoom {
	/**
	 * Creates a new, empty target room with a default direction.
	 * @param point		the position of the new sprite
	 */
	public Target(Point point) {
		this(new Nothing(point));
	}
	/**
	 * Creates a new target with something within it.
	 * @param inner		the sprite inside the room
	 */
	public Target(Sprite inner) {
		super(inner);
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public Room withInner(Sprite inner) {
		return new Target(inner);
	}
	@Override
	public Sprite move(Point point) {
		return new Target(inner().move(point));
	}
}
