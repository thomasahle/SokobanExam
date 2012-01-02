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
}
