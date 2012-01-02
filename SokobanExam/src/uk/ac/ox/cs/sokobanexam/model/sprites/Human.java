package uk.ac.ox.cs.sokobanexam.model.sprites;

public class Human implements Sprite {
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
}
