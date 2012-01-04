package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;

// Null object

public class Floor extends AbstractRoom {
	public Floor(Sprite inner) {
		super(inner);
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public Room withInner(Sprite inner) {
		return new Floor(inner);
	}
	@Override
	public Sprite move(Point point) {
		return new Floor(inner().move(point));
	}
}
