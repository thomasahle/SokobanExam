package uk.ac.ox.cs.sokobanexam.model.sprites;

public interface Sprite {
	public enum SemanticType {
		NONE,
		CRATE, HUMAN, TARGET, WALL,
		ARROW_N, ARROW_E, ARROW_S, ARROW_W
	}
	SemanticType type();
	
	void accept(SpriteVisitor visitor);
}
