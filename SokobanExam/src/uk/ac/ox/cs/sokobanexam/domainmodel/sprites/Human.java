package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Dir;
import uk.ac.ox.cs.sokobanexam.util.Point;

// Skift navn til Robot?

public class Human extends AbstractSprite {
	private Dir mDirection;
	public Human(Point point) {
		this(point, Dir.NORTH);
	}
	public Human(Point point, Dir direction) {
		super(point);
		mDirection = direction;
	}
	/** The direction in which the human as just walked, and is now looking. */
	public Dir getDirection() {
		return mDirection;
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public Sprite move(Point point) {
		return new Human(point, getDirection());
	}
}
