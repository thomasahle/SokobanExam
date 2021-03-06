package uk.ac.ox.cs.sokobanexam.model.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;

public class Nothing extends AbstractSprite {
	/**
	 * Creates a nothing to put inside empty rooms.
	 * @param point		the position of the nothing
	 */
	public Nothing(Point point) {
		super(point);
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public Sprite move(Point point) {
		return new Nothing(point);
	}
}
