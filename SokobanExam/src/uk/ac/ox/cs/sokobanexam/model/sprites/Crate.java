package uk.ac.ox.cs.sokobanexam.model.sprites;

import uk.ac.ox.cs.sokobanexam.util.Color;
import uk.ac.ox.cs.sokobanexam.util.Point;

public class Crate extends AbstractSprite {
	private Color mColor;
	/**
	 * Creates a new crate with a default color.
	 * @param point		the position of the new sprite
	 */
	public Crate(Point point) {
		this(point, Color.RED);
	}
	/**
	 * Creates a new crate.
	 * @param point		the position of the new sprite
	 * @param color		the color of the new sprite
	 */
	public Crate(Point point, Color color) {
		super(point);
		mColor = color;
	}
	/**
	 * Gets the color of the crate.
	 * @return			the color of the crate
	 */
	public Color color() {
		return mColor;
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public Sprite move(Point point) {
		return new Crate(point, color());
	}
}
