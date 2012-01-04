package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;



public class Wall extends AbstractRoom {
	private String mWriting;
	public Wall(Point point, Sprite inner, String writing) {
		super(point, inner);
		mWriting = writing;
	}
	public Wall(Point point, Sprite inner) {
		this(point, inner, "");
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	public String getWriting() {
		return mWriting;
	}
	public Sprite setWriting(String writing) {
		return new Wall(point(), inner(), writing);
	}
	@Override
	public Room setInner(Sprite inner) {
		return new Wall(point(), inner, getWriting());
	}
}
