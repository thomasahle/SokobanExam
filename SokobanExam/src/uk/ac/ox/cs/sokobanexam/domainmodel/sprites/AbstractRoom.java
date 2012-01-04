package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;

public abstract class AbstractRoom extends AbstractSprite implements Room {
	private Sprite mInner;
	public AbstractRoom(Point point, Sprite inner) {
		super(point);
		mInner = inner;
	}
	// TODO: Should this be the only version?
	private AbstractRoom(Sprite inner) {
		super(inner.point());
		mInner = inner;
	}
	public Sprite inner() {
		return mInner;
	}
}
