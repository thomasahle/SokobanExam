package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;


public class Human implements Sprite {
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
	@Override
	public SemanticType type() {
		return SemanticType.HUMAN;
	}
}
