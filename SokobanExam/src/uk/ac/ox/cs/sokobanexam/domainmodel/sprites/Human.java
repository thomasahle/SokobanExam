package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;


public class Human extends AbstractSprite implements Sprite {
	public Human(Point point) {
		mPoint = point;
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
