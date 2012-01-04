package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;


public class Floor extends AbstractSprite implements FlatSprite {
	// Null pattern
	public Floor(Point point) {
		mPoint = point;
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public SemanticType type() {
		return SemanticType.NONE;
	}
	@Override
	public boolean equals(Object obj) {
        return super.equals(obj)
        		&& obj instanceof Floor;
    }
}
