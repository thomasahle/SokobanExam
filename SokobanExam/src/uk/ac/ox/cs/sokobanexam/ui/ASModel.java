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
	private Board mBoard;
	private Rules mRules;
	private Point mSelected;
	private Class<? extends Sprite> mTypeForInsertion;
	
	public ASModel(Board board, Rules rules) {
		mBoard = board;
		mRules = rules;
	}
	
	private MazeChangeListener mMazeChangeListener;
	public void setMazeChangeListener(MazeChangeListener listener) {
		mMazeChangeListener = listener;
	}
	
	private SelectionChangeListener mSelectionChangeListener;
	public void setSelectionChangeListener(SelectionChangeListener listener) {
		mSelectionChangeListener = listener;
	}
	
	public boolean move(Dir direction) {
		assert mRules.validateBoard(mBoard);
		Point from = mBoard.getRoomsContaining(Human.class).iterator().next().point();
		Point to = from.plus(direction);
		if (!mRules.validateMove(mBoard,from,to))
			return false;
		mRules.applyMove(mBoard,from,to);
		mMazeChangeListener.onChange(this);
		return true;
	}
	
	public void setRules(Rules rules) {
		mRules = rules;
	}
	public void setBoard(Board board) {
		mBoard = board;
	}
	public Rules getRules() {
		return mRules;
	}
	public Board getBoard() {
		return mBoard;
	}
	
	public void setTypeForInsertion(Class<? extends Sprite> typeForInsertion) {
		this.mTypeForInsertion = typeForInsertion;
	}
	public Class<? extends Sprite> getTypeForInsertion() {
		return mTypeForInsertion;
	}

	public void setSelected(Point selected) {
		this.mSelected = selected;
		mSelectionChangeListener.onSelectionChange(this);
	}
	public Point getSelected() {
		return mSelected;
	}

	public void deleteSelected() {
		// TODO Auto-generated method stub
		
	}
}
