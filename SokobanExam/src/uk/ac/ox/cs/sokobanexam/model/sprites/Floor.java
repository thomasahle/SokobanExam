package uk.ac.ox.cs.sokobanexam.model.sprites;

public class Floor implements FlatSprite {
	// Null pattern
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
}
