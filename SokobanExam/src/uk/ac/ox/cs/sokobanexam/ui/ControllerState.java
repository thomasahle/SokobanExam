package uk.ac.ox.cs.sokobanexam.ui;

/**
 * Represents a state of the MazeController.
 */
public interface ControllerState {
	/**
	 * Called when the state is set on the controller.
	 * Called after the last state has been detached.
	 */
	void attach(MazeController controller, MazeModel model, MazeView view);
	/**
	 * Called when the state is no longer current, but before any other state is set.
	 * Please use this to detach yourself from ANY LISTENERS and anything else that might keep you around.
	 */
	void detach();
}