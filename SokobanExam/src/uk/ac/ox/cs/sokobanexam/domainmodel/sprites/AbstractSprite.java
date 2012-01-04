package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;

public abstract class AbstractSprite implements Sprite {
	private Point mPoint;
	public AbstractSprite(Point point) {
		mPoint = point;
	}
	@Override
	public Point point() {
		return mPoint;
	}
}
