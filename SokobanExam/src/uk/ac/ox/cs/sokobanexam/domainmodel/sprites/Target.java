package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;


public class Target extends AbstractRoom {
	public Target(Sprite inner) {
		super(inner);
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public Room withInner(Sprite inner) {
		return new Target(inner);
	}
}
