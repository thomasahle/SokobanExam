package uk.ac.ox.cs.sokobanexam.ui;

import uk.ac.ox.cs.sokobanexam.model.sprites.Sprite;

/**
 * Listener used to inform when a sprite has changed.
 * This usually happens when a user edits it in the edit mode.
 * You must make sure this change is represented in the model.
 */
public interface SpriteChangeListener {
	void onSpriteChanged(Sprite oldSprite, Sprite newSprite);
}
