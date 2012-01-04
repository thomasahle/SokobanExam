package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Dir;
import uk.ac.ox.cs.sokobanexam.util.Point;

public class Arrow extends AbstractRoom {
	private Dir mDirection;
	public Arrow(Point point, Sprite inner, Dir direction) {
		super(point, inner);
		mDirection = direction;
	}
	public Arrow setDirection(Dir direction) {
		return new Arrow(point(), inner(), direction);
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
	public Room setInner(Sprite inner) {
		return new Arrow(point(), inner, getDirection());
	}
}
