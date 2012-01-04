package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;


public class Target extends AbstractSprite implements FlatSprite {
	public Target(Point point) {
		mPoint = point;
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public SemanticType type() {
		return SemanticType.TARGET;
	}
	@Override
	public boolean equals(Object obj) {
        return super.equals(obj)
        		&& obj instanceof Target;
    }
}
