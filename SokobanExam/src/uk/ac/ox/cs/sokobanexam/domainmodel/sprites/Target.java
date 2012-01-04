package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;


public class Target extends AbstractRoom {
	public Target(Point point, Sprite inner) {
		super(point, inner);
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public Room setInner(Sprite inner) {
		return new Target(point(), inner);
	}
}
