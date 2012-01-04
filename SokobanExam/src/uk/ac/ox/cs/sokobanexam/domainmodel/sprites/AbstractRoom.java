package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;


public abstract class AbstractRoom extends AbstractSprite implements Room {
	private Sprite mInner;
	public AbstractRoom(Sprite inner) {
		super(inner.point());
		mInner = inner;
	}
	public Sprite inner() {
		return mInner;
	}
}
