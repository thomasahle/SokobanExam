package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;



public class Wall implements SolidSprite {
	private String mWriting;
	public Wall(String writing) {
		mWriting = writing;
	}
	public Wall() {
		this("");
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
		return new Wall(writing);
	}
}
