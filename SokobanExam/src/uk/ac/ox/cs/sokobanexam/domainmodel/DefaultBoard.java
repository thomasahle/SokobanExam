package uk.ac.ox.cs.sokobanexam.domainmodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Floor;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite.SemanticType;
import uk.ac.ox.cs.sokobanexam.util.Point;
import uk.ac.ox.cs.sokobanexam.util.PointRangeSet;

// Alternative representation:
//     A set of sprites all containing their own coordinates (passer bedre med beskrivelsen), z-index?

public class DefaultBoard implements Board {
	
	// Class Invariant:
	//		mMap.length >= 1
	//		mMap[y].length >= 1
	//		mMap[y][x].size() >= 1
	private List<Sprite>[][] mMap;
	
	// Class Invariant: mOccupiedPoints[type] = {point in mMap | mMap[point].type() = type}
	private Map<SemanticType,Set<Point>> mOccupiedPoints;
	
	@SuppressWarnings("unchecked")
	public DefaultBoard(int width, int height) {
		if (width <= 0 || height <= 0)
			throw new IllegalArgumentException("Board size: "+width+"x"+height);
		mMap = new List[height][width];
		mOccupiedPoints = new HashMap<SemanticType,Set<Point>>();
		
		// Initialize class invariant:
		for (SemanticType type : SemanticType.values())
			mOccupiedPoints.put(type, new HashSet<Point>());
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++) {
				mMap[y][x] = new ArrayList<Sprite>();
				insertSpriteAt(Point.at(x,y), new Floor(Point.at(x,y)));
			}
	}
	
	@Override
	public int getHeight() {
		return mMap.length;
	}

	@Override
	public int getWidth() {
		return mMap[0].length;
	}
	
	@Override
	public Set<Point> getContainedPoints() {
		return new PointRangeSet(getWidth(), getHeight());
	}
	
	@Override
	public List<Sprite> getSpritesAt(Point point) {
		if (!getContainedPoints().contains(point))
			throw new IndexOutOfBoundsException();
		return Collections.unmodifiableList(mMap[point.y][point.x]);
	}
	
	@Override
	public Sprite getTopSpriteAt(Point point) {
		List<Sprite> sprites = mMap[point.y][point.x];
		return sprites.get(sprites.size()-1);
	}

	@Override
	public Sprite deleteTopSpriteAt(Point point) {
		List<Sprite> sprites = mMap[point.y][point.x];
		if (sprites.size() == 0)
			return sprites.get(0);
		Sprite sprite = sprites.get(sprites.size()-1);
		mOccupiedPoints.get(sprite.type()).remove(point);
		return sprites.remove(sprites.size()-1);
	}

	@Override
	public DefaultBoard insertSpriteAt(Point point, Sprite sprite) {
		mMap[point.y][point.x].add(sprite);
		mOccupiedPoints.get(sprite.type()).add(point);
		return this;
	}
	
	@Override
	public Set<Point> getOccupiedPoints(SemanticType type) {
		return Collections.unmodifiableSet(mOccupiedPoints.get(type));
	}
}
