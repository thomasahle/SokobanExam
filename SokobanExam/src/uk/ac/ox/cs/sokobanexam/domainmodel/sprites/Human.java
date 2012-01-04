package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Dir;
import uk.ac.ox.cs.sokobanexam.util.Point;


public class Human extends AbstractSprite implements Sprite {
	private Dir mDirection;
	public Human(Point point, Dir direction) {
		mDirection = direction;
		mPoint = point;
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
	public SemanticType type() {
		return SemanticType.HUMAN;
	}
	@Override
	public boolean equals(Object obj) {
        return super.equals(obj)
        		&& obj instanceof Human;
    }
}
