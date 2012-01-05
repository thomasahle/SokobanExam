package uk.ac.ox.cs.sokobanexam.ui;

import uk.ac.ox.cs.sokobanexam.util.Point;

public interface SelectionChangeListener {
	public void onSelectionChanged(MazeModel model, Point from, Point to);

	public void onHighlightChanged(MazeModel mazeModel, Point from, Point to);
}
