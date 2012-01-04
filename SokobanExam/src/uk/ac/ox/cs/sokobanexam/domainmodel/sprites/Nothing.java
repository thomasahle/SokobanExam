package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;

public class Nothing extends AbstractSprite {
	public Nothing(Point point) {
		super(point);
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
}
