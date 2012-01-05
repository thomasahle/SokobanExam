package uk.ac.ox.cs.sokobanexam.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.ac.ox.cs.sokobanexam.domainmodel.Board;
import uk.ac.ox.cs.sokobanexam.domainmodel.Rules;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Arrow;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Crate;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Human;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Target;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Wall;
import uk.ac.ox.cs.sokobanexam.util.Dir;
import uk.ac.ox.cs.sokobanexam.util.Point;

public class MazeModel {
	
	/**
	 * Because java has no way of discovering all subclasses of a certain class,
	 * we keep this map of sprites that we are interested in editing.
	 * These are the physical sprites, those that cannot be overwritten by insertion.
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
	
	private Board mBoard;
	private Rules mRules;
	
	private Point mSelected;
	private boolean mSelectionVisible = false;
	private Point mHighlighted;
	private String mHoverMessage;
	private Class<? extends Sprite> mTypeForInsertion;
	
	public MazeModel(Board board, Rules rules) {
		setBoard(board);
		setRules(rules);
	}
	
	public void setRules(Rules rules) {
		mRules = rules;
	}
	public Rules getRules() {
		return mRules;
	}
	public void setBoard(Board board) {
		mBoard = board;
		fireMazeChangeEvent();
	}
	public Board getBoard() {
		return mBoard;
	}
	
	// --------------- Listeners
	
	private List<MazeChangeListener> mMazeChangeListeners
			= new ArrayList<MazeChangeListener>();
	public void addMazeChangeListener(MazeChangeListener listener) {
		mMazeChangeListeners.add(listener);
	}
	private void fireMazeChangeEvent() {
		for (MazeChangeListener listener : mMazeChangeListeners)
			listener.onChange(this);
	}
	
	private List<SelectionChangeListener> mSelectionChangeListeners 
			= new ArrayList<SelectionChangeListener>();
	public void addSelectionChangeListener(SelectionChangeListener listener) {
		mSelectionChangeListeners.add(listener);
	}
	public void removeSelectionChangeListener(SelectionChangeListener listener) {
		mSelectionChangeListeners.remove(listener);
	}
	private void fireSelectionChangeEvent(Point from, Point to) {
		for (SelectionChangeListener listener : mSelectionChangeListeners)
			listener.onSelectionChanged(this, from, to);
	}
	private void fireHighlightChangeEvent(Point from, Point to) {
		for (SelectionChangeListener listener : mSelectionChangeListeners)
			listener.onHighlightChanged(this, from, to);
	}
	
	// --------------- Domain shortcuts
	
	public boolean move(Dir direction) {
		assert mRules.validateBoard(mBoard);
		if (!mRules.validateMove(mBoard,direction))
			return false;
		mRules.applyMove(mBoard,direction);
		fireMazeChangeEvent();
		return true;
	}
	public boolean isWon() {
		return mRules.isGameWon(mBoard);
	}
	public boolean isPlayable() {
		return mRules.isPlayable(mBoard);
	}
	
	// --------------- Non domain specific state
	
	public void setTypeForInsertion(Class<? extends Sprite> typeForInsertion) {
		mTypeForInsertion = typeForInsertion;
		Point old = mSelected;
		mSelected = null;
		fireSelectionChangeEvent(old, null);
	}
	public Class<? extends Sprite> getTypeForInsertion() {
		return mTypeForInsertion;
	}

	public void setSelected(Point point) {
		Point old = mSelected;
		mSelected = point;
		fireSelectionChangeEvent(old, point);
	}
	public Point getSelected() {
		return mSelected;
	}

	public void setHighlighted(Point point) {
		Point old = mHighlighted;
		mHighlighted = point;
		fireHighlightChangeEvent(old, point);
	}
	public Point getHighlighted() {
		return mHighlighted;
	}
	public void setSelectionVisible(boolean selectionVisible) {
		this.mSelectionVisible = selectionVisible;
	}
	public boolean isSelectionVisible() {
		return mSelectionVisible;
	}
	public void setHoverMessage(String hoverMessage) {
		this.mHoverMessage = hoverMessage;
	}
	public String getHoverMessage() {
		return mHoverMessage;
	}
}
