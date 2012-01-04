package uk.ac.ox.cs.sokobanexam.domainmodel;

import java.util.List;
import java.util.Set;

import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite.SemanticType;

public interface Board {
	
	public int getHeight();

	public int getWidth();
	
	
	public List<Sprite> getSpritesAt(Point point);
	
	public Sprite getTopSpriteAt(Point point);

	public Sprite deleteTopSpriteAt(Point point);

	public DefaultBoard insertSpriteAt(Point point, Sprite sprite);
	
	
	public Set<Point> getContainedPoints();
	
	public Set<Point> getOccupiedPoints(SemanticType type);
}