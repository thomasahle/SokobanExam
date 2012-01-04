package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Color;
import uk.ac.ox.cs.sokobanexam.util.Point;

public class Crate extends AbstractSprite {
	private Color mColor;
	public Crate(Point point, Color color) {
		super(point);
		mColor = color;
	}
	public Crate setColor(Color color) {
		return new Crate(point(), color);
	}
	public Color getColor() {
		return mColor;
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
}
