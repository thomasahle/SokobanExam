package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Dir;
import uk.ac.ox.cs.sokobanexam.util.Point;

public class Arrow extends AbstractRoom {
	private Dir mDirection;
	/**
	 * Creates a new, empty arrow with a default direction.
	 * @param point		the position of the new sprite
	 */
	public Arrow(Point point) {
		this(new Nothing(point), Dir.NORTH);
	}
	/**
	 * Creates a new arrow with something within it.
	 * @param inner		the sprite inside the room
	 * @param direction	the direction of the new arrow
	 */
	public Arrow(Sprite inner, Dir direction) {
		super(inner);
		mDirection = direction;
	}
	/**
	 * Gets the direction in which the arrow is pointing.
	 * @return			the direction of the arrow
	 */
	public Dir direction() {
		return mDirection;
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public Room withInner(Sprite inner) {
		return new Arrow(inner, direction());
	}
	@Override
	public Sprite move(Point point) {
		return new Arrow(inner().move(point), direction());
	}
}
