package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;


public class Target implements FlatSprite {
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public SemanticType type() {
		return SemanticType.TARGET;
	}
}
