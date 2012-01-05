package uk.ac.ox.cs.sokobanexam.ui;

public interface ControllerState {
	void attach(MazeController controller, MazeModel model, MazeView view);
	void detach();
}