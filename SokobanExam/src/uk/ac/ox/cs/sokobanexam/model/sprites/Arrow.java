package uk.ac.ox.cs.sokobanexam.model.sprites;

import uk.ac.ox.cs.sokobanexam.util.Dir;

public class Arrow implements SolidSprite {
	private Dir mDirection;
	public Arrow(Dir direction) {
		mDirection = direction;
	}
	public Arrow setDirection(Dir direction) {
		return new Arrow(direction);
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
}
