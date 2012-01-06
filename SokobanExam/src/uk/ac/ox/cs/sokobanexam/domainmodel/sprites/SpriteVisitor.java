package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

/**
 * Represents a visitor for the {@link Sprite} class.
 */
public interface SpriteVisitor {
	public void visit(Arrow sprite);
	public void visit(Crate sprite);
	public void visit(Floor sprite);
	public void visit(Human sprite);
	public void visit(Target sprite);
	public void visit(Wall sprite);
	public void visit(Nothing nothing);
}
