package uk.ac.ox.cs.sokobanexam.model;

import java.util.Set;

import uk.ac.ox.cs.sokobanexam.model.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.model.sprites.Sprite.SemanticType;

public interface IBoard {
	
	public int getHeight();

	public int getWidth();

	
	public Sprite getTopSpriteAt(Point point);

	public Sprite deleteTopSpriteAt(Point point);

	public Board insertSpriteAt(Point point, Sprite sprite);
	
	
	public Set<Point> getOccupiedPoints(SemanticType type);
}