package uk.ac.ox.cs.sokobanexam.model.sprites;

public class Target implements FlatSprite {
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
}
