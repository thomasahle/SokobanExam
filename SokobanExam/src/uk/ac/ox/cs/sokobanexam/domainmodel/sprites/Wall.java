package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;

public class Wall extends AbstractRoom {
	private String mWriting;
	public Wall(Point point) {
		this(new Nothing(point), "");
	}
	public Wall(Sprite inner, String writing) {
		super(inner);
		mWriting = writing;
	}
	public Wall(Sprite inner) {
		this(inner, "");
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	public String getWriting() {
		return mWriting;
	}
	@Override
	public Room withInner(Sprite inner) {
		return new Wall(inner, getWriting());
	}
	@Override
	public Sprite move(Point point) {
		return new Wall(inner().move(point), getWriting());
	}
}
