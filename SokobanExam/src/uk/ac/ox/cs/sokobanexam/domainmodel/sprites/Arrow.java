package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Dir;
import uk.ac.ox.cs.sokobanexam.util.Point;

public class Arrow extends AbstractSprite implements SolidSprite {
	private Dir mDirection;
	public Arrow(Point point, Dir direction) {
		mDirection = direction;
		mPoint = point;
	}
	public Arrow setDirection(Dir direction) {
		return new Arrow(mPoint, direction);
	}
	public Dir getDirection() {
		return mDirection;
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public SemanticType type() {
		switch (mDirection) {
		case NORTH: return SemanticType.ARROW_N;
		case EAST: return SemanticType.ARROW_E;
		case SOUTH: return SemanticType.ARROW_S;
		case WEST: return SemanticType.ARROW_W;
		}
		return null;
	}
	@Override
	public int hashCode() {
		return super.hashCode() ^ mDirection.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO: That we don't test for semantic type here, shows that we have really discarded it.
        return super.equals(obj)
        		&& obj instanceof Arrow
        		&& mDirection == ((Arrow)obj).getDirection();
    }
}
