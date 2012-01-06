package uk.ac.ox.cs.sokobanexam.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * The controller of the MazeView.
 * This class doesn't contain any controlling logic itself, but delegates to {@link ControllerState}s.
 */
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
		fireStateChangedEvent();
	}
	public ControllerState getCurrentState() {
		return currentState;
	}
	
	private List<StateChangeListener> mStateChangeListeners
			= new ArrayList<StateChangeListener>();
	public void addStateChangeListener(StateChangeListener listener) {
		mStateChangeListeners.add(listener);
	}
	public void fireStateChangedEvent() {
		for (StateChangeListener listener : mStateChangeListeners)
			listener.onStateChanged(this);
	}
}
