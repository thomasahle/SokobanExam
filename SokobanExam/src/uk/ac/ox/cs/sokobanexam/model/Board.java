package uk.ac.ox.cs.sokobanexam.model;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ox.cs.sokobanexam.model.sprites.Floor;
import uk.ac.ox.cs.sokobanexam.model.sprites.Sprite;

public class Board {
	public final static int MAX_WIDTH = 100;
	public final static int MAX_HEIGHT = 100;
	
	// Class Invariant: mMap[y][x].length >= 1
	private List<Sprite>[][] mMap;
	
	@SuppressWarnings("unchecked")
	public Board(int width, int height) {
		assert 0 < width && width < MAX_WIDTH;
		assert 0 < height && height < MAX_HEIGHT;
		mMap = new List[height][width];
		// Initialize class invariant:
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++) {
				mMap[y][x] = new ArrayList<Sprite>();
				mMap[y][x].add(new Floor());
			}
	}
	
	public int getHeight() {
		return mMap.length;
	}
	public int getWidth() {
		return mMap[0].length;
	}
	
	public List<Sprite> getSpritesAt(Point point) {
		if (!(0 <= point.x && point.x < getWidth())
				|| !(0 <= point.y && point.y < getHeight()))
			throw new IndexOutOfBoundsException();
		return mMap[point.y][point.x];
	}
	public Sprite getTopSpriteAt(Point point) {
		List<Sprite> sprites = getSpritesAt(point);
		return sprites.get(sprites.size()-1);
	}
	public Sprite deleteTopSpriteAt(Point point) {
		List<Sprite> sprites = getSpritesAt(point);
		if (sprites.size() == 0)
			return sprites.get(0);
		return sprites.remove(sprites.size()-1);
	}
	public Board insertSpriteAt(Point point, Sprite sprite) {
		getSpritesAt(point).add(sprite);
		return this;
	}

	public Point getHumanPoint() {
		// TODO Auto-generated method stub
		return null;
	}
}
