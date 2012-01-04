package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;

// Null object

public class Floor extends AbstractRoom {
	public Floor(Point point, Sprite inner) {
		super(point, inner);
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public Room setInner(Sprite inner) {
		return new Floor(point(), inner);
	}
}
