package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;

public class Wall extends AbstractRoom {
	private String mWriting;
	/**
	 * Creates a new, empty wall room with a default direction.
	 * @param point		the position of the new sprite
	 */
	public Wall(Point point) {
		this(new Nothing(point), "");
	}
	/**
	 * Creates a new wall with something within it.
	 * @param inner		the sprite inside the room
	 * @param writing	the writing on the wall
	 */
	public Wall(Sprite inner, String writing) {
		super(inner);
		mWriting = writing;
	}
	/**
	 * Creates a new wall with something within it.
	 * @param inner		the sprite inside the room
	 */
	public Wall(Sprite inner) {
		this(inner, "");
	}
	/**
	 * Gets the writing on the wall
	 * @return			a short string written on the wall
	 */
	public String writing() {
		return mWriting;
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public Room withInner(Sprite inner) {
		return new Wall(inner, writing());
	}
	@Override
	public Sprite move(Point point) {
		return new Wall(inner().move(point), writing());
	}
}
