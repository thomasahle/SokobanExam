package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Color;

public class Crate implements SolidSprite {
	private Color mColor;
	public Crate(Color color) {
		mColor = color;
	}
	public Crate setColor(Color color) {
		return new Crate(color);
	}
	public Color getColor() {
		return mColor;
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public SemanticType type() {
		return SemanticType.CRATE;
	}
}