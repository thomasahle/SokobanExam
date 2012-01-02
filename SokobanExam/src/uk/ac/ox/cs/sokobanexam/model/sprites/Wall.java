package uk.ac.ox.cs.sokobanexam.model.sprites;

public class Wall implements SolidSprite {
	private char mWriting;
	@Override
	public void accept(SpriteVisitor visitor) {
		visitor.visit(this);
	}
}
