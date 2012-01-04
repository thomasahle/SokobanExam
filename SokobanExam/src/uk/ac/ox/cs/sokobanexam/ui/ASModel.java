package uk.ac.ox.cs.sokobanexam.ui;

import uk.ac.ox.cs.sokobanexam.domainmodel.Board;
import uk.ac.ox.cs.sokobanexam.domainmodel.Rules;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Human;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.util.Dir;
import uk.ac.ox.cs.sokobanexam.util.Point;

// Do we need some State machine pattern here?

// If we change Board->Maze we can change ASModel->Model

public class ASModel {
	
	public enum State { EDITING, INSERTING, PLAYING; }
	
	private Board mBoard;
	private Rules mRules;
	
	private State mState;
	private Point mSelected;
	private Class<? extends Sprite> mTypeForInsertion;
	
	public ASModel(Board board, Rules rules, State state) {
		setBoard(board);
		setRules(rules);
		setState(state);
	}
	
	private MazeChangeListener mMazeChangeListener;
	public void setMazeChangeListener(MazeChangeListener listener) {
		mMazeChangeListener = listener;
	}
	
	private SelectionChangeListener mSelectionChangeListener;
	public void setSelectionChangeListener(SelectionChangeListener listener) {
		mSelectionChangeListener = listener;
	}
	
	private StateChangeListener mStateChangeListener;
	public void setStateChangeListener(StateChangeListener listener) {
		mStateChangeListener = listener;
	}
	
	public boolean move(Dir direction) {
		assert mRules.validateBoard(mBoard);
		Point from = mBoard.getRoomsContaining(Human.class).iterator().next().point();
		if (!mRules.validateMove(mBoard,from,direction))
			return false;
		mRules.applyMove(mBoard,from,direction);
		if (mMazeChangeListener != null)
			mMazeChangeListener.onChange(this);
		return true;
	}
	
	public void setRules(Rules rules) {
		mRules = rules;
	}
	public Rules getRules() {
		return mRules;
	}
	public void setBoard(Board board) {
		mBoard = board;
	}
	public Board getBoard() {
		return mBoard;
	}
	/**
	 * @param state the new state for the model
	 * @return false if the new state could not be accepted, true otherwise
	 */
	public boolean setState(State state) {
		if (state == State.PLAYING && !mRules.isPlayable(mBoard))
			return false;
		if (state != mState) {
			mState = state;
			if (mStateChangeListener != null)
				mStateChangeListener.onStateChanged(this);
		}
		return true;
	}
	public State getState() {
		return mState;
	}
	
	public void setTypeForInsertion(Class<? extends Sprite> typeForInsertion) {
		mTypeForInsertion = typeForInsertion;
		mSelected = null;
		if (mSelectionChangeListener != null)
			mSelectionChangeListener.onSelectionChange(this);
	}
	public Class<? extends Sprite> getTypeForInsertion() {
		return mTypeForInsertion;
	}

	public void setSelected(Point selected) {
		this.mSelected = selected;
		if (mSelectionChangeListener != null)
			mSelectionChangeListener.onSelectionChange(this);
	}
	public Point getSelected() {
		return mSelected;
	}

	public void deleteSelected() {
		// TODO Auto-generated method stub
		
	}
}

/*interface State {
	public void setSelected(Point point);
	public void setTypeForInsertion(Class<? extends Sprite> type);
	public boolean move(Dir direction);
}

class EditingState implements State {
	
}

class InsertingState implements State {
	
}

class PlayingState implements State {
	
}
*/