package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;


public class Wall implements SolidSprite {
	private char mWriting;
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public SemanticType type() {
		return SemanticType.WALL;
	}
}
