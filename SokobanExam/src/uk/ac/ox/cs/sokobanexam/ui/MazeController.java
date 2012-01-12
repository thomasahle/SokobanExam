package uk.ac.ox.cs.sokobanexam.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.ac.ox.cs.sokobanexam.model.sprites.Arrow;
import uk.ac.ox.cs.sokobanexam.model.sprites.Crate;
import uk.ac.ox.cs.sokobanexam.model.sprites.Human;
import uk.ac.ox.cs.sokobanexam.model.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.model.sprites.Target;
import uk.ac.ox.cs.sokobanexam.model.sprites.Wall;

/**
 * The controller of the MazeView.
 * This class doesn't contain any controlling logic itself, but delegates to {@link ControllerState}s.
 */
public class MazeController {
	
	/*
	 * Because java has no way of discovering all subclasses of a certain class,
	 * we keep this map of sprites that we are interested in editing.
	 * These are the physical sprites, those that can be inserted from the toolbar
	 * and cannot be overwritten by later insertions.
	 */
	@SuppressWarnings("serial")
	protected static final Map<Class<? extends Sprite>, String> PHYSICAL_SPRITES
			= new HashMap<Class<? extends Sprite>, String>() {{
		put(Wall.class, "Wall");
		put(Crate.class, "Crate");
		put(Arrow.class, "Arrow");
		put(Target.class, "Target");
		put(Human.class, "Player");
	}};
	protected static boolean isEditableType(Sprite sprite) {
		return PHYSICAL_SPRITES.containsKey(sprite.getClass());
	}
	
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
