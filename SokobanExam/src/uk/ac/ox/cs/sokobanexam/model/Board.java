package uk.ac.ox.cs.sokobanexam.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import uk.ac.ox.cs.sokobanexam.model.sprites.Floor;
import uk.ac.ox.cs.sokobanexam.model.sprites.Human;
import uk.ac.ox.cs.sokobanexam.model.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.model.sprites.Target;

public class Board implements IBoard {
	
	// Class Invariant:
	//		mMap.length >= 1
	//		mMap[y].length >= 1
	//		mMap[y][x].size() >= 1
	private List<Sprite>[][] mMap;
	
	// Class Invariant: {mHumanPoint} = {point in mMap | mMap[point] instanceof Human}
	private Point mHumanPoint;
	
	// Class Invariant: mTargetPoints = {point in mMap | mMap[point] instanceof Target}
	private Set<Point> mTargetPoints;
	
	@SuppressWarnings("unchecked")
	public Board(int width, int height) {
		if (width <= 0 || height <= 0)
			throw new IllegalArgumentException("Board size: "+width+"x"+height);
		mMap = new List[height][width];
		// Initialize class invariant:
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++) {
				mMap[y][x] = new ArrayList<Sprite>();
				mMap[y][x].add(new Floor());
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
	
	public List<Sprite> getSpritesAt(Point point) {
		if (!(0 <= point.x && point.x < getWidth())
				|| !(0 <= point.y && point.y < getHeight()))
			throw new IndexOutOfBoundsException();
		return Collections.unmodifiableList(mMap[point.y][point.x]);
	}
	
	@Override
	public Sprite getTopSpriteAt(Point point) {
		List<Sprite> sprites = getSpritesAt(point);
		return sprites.get(sprites.size()-1);
	}

	@Override
	public Sprite deleteTopSpriteAt(Point point) {
		List<Sprite> sprites = getSpritesAt(point);
		if (sprites.size() == 0)
			return sprites.get(0);
		Sprite sprite = sprites.get(sprites.size()-1);
		if (sprite instanceof Target)
			mTargetPoints.remove(point);
		else if (sprite instanceof Human)
			mHumanPoint = null;
		return sprites.remove(sprites.size()-1);
	}

	@Override
	public Board insertSpriteAt(Point point, Sprite sprite) {
		getSpritesAt(point).add(sprite);
		if (sprite instanceof Human)
			mHumanPoint = point;
		else if (sprite instanceof Target)
			mTargetPoints.add(point);
		return this;
	}
	
	@Override
	public Point getHumanPoint() {
		return mHumanPoint;
	}

	@Override
	public Set<Point> getTargetPoints() {
		return Collections.unmodifiableSet(mTargetPoints);
	}
}
