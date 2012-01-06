package uk.ac.ox.cs.sokobanexam.ui;

/**
 * Listener used to inform when the value of model.getMaze() has changed.
 * Usually this will be due to a move having been applied, or a new maze
 * having been set on the model.
 */
public interface MazeChangeListener {
	public void onChange(MazeModel model);
}
