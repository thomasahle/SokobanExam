package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

public interface Room extends Sprite {
	public Sprite inner();

	public Room withInner(Sprite inner);
}
