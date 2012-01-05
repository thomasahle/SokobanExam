package uk.ac.ox.cs.sokobanexam.ui;


public class MazeController {
	private MazeView mView;
	private MazeModel mModel;
	private ControllerState currentState;
	public MazeController(MazeModel model, MazeView view) {
		mModel = model;
		mView = view;
	}
	public MazeView getMazeView() {
		return mView;
	}
	public MazeModel getModel() {
		return mModel;
	}
	public void setCurrentState(ControllerState state) {
		if (currentState != null)
			currentState.detach();
		currentState = state;
		state.attach(this, mModel, mView);
	}
	public ControllerState getCurrentState() {
		return currentState;
	}
}

