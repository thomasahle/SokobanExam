package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Dir;
import uk.ac.ox.cs.sokobanexam.util.Point;

public class Arrow extends AbstractRoom {
	private Dir mDirection;
	public Arrow(Point point) {
		this(new Nothing(point), Dir.NORTH);
	}
	public Arrow(Sprite inner, Dir direction) {
		super(inner);
		mDirection = direction;
	}
	public Arrow setDirection(Dir direction) {
		return new Arrow(inner(), direction);
	}
	/** The direction in which the arrow is pointing */
	public Dir getDirection() {
		return mDirection;
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public Room withInner(Sprite inner) {
		return new Arrow(inner, getDirection());
	}
	@Override
	public Sprite move(Point point) {
		return new Arrow(inner().move(point), getDirection());
	}
}
