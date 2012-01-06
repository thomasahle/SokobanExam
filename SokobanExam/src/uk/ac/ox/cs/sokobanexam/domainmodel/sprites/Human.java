package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Dir;
import uk.ac.ox.cs.sokobanexam.util.Point;

// Skift navn til Robot?

public class Human extends AbstractSprite {
	private Dir mDirection;
	/**
	 * Creates a new human with a default direction.
	 * @param point		the position of the new sprite
	 */
	public Human(Point point) {
		this(point, Dir.WEST);
	}
	/**
	 * Creates a new human.
	 * @param point		the position of the new sprite
	 * @param color		the direction in which the human is looking
	 */
	public Human(Point point, Dir direction) {
		super(point);
		mDirection = direction;
	}
	/**
	 * Gets the direction in which the human as just walked, and is now looking.
	 * @return			the direction of the human
	 */
	public Dir direction() {
		return mDirection;
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public Sprite move(Point point) {
		return new Human(point, direction());
	}
}
