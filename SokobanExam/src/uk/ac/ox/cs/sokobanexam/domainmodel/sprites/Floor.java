package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;


public class Floor implements FlatSprite {
	// Null pattern
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public SemanticType type() {
		return SemanticType.NONE;
	}
}
