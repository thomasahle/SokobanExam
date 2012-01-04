package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Color;
import uk.ac.ox.cs.sokobanexam.util.Point;

public class Crate extends AbstractSprite implements SolidSprite {
	private Color mColor;
	public Crate(Point point, Color color) {
		mPoint = point;
		mColor = color;
	}
	public Crate setColor(Color color) {
		return new Crate(mPoint, color);
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
	@Override
	public int hashCode() {
		return super.hashCode() ^ mColor.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
        return super.equals(obj)
        		&& obj instanceof Crate
        		&& mColor == ((Crate)obj).getColor();
    }
}
