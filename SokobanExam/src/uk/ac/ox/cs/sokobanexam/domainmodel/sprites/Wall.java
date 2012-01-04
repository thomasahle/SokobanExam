package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;



public class Wall extends AbstractSprite implements SolidSprite {
	private String mWriting;
	public Wall(Point point, String writing) {
		mPoint = point;
		mWriting = writing;
	}
	public Wall(Point point) {
		this(point, "");
	}
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public SemanticType type() {
		return SemanticType.WALL;
	}
	public String getWriting() {
		return mWriting;
	}
	public Sprite setWriting(String writing) {
		return new Wall(mPoint, writing);
	}
	@Override
	public int hashCode() {
		return super.hashCode() ^ mWriting.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
        return super.equals(obj)
        		&& obj instanceof Wall
        		&& mWriting.equals(((Wall)obj).getWriting());
    }
}
