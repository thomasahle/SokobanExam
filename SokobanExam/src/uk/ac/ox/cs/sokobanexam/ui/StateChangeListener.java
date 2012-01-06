package uk.ac.ox.cs.sokobanexam.ui;

/**
 * Listener used by the MazeController to notify listeners that a new state has been set.
 * Mostly states will take care themselves of everything needed in the transition, but
 * for example views that allow state change might want to listen to this to stay in sync.
 */
public interface StateChangeListener {
	public void onStateChanged(MazeController source);
}
