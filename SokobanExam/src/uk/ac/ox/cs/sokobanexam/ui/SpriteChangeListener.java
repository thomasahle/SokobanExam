package uk.ac.ox.cs.sokobanexam.ui;

import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite;

public interface SpriteChangeListener {

	void onSpriteChanged(Sprite oldSprite, Sprite newSprite);

}
