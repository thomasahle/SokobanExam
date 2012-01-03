package uk.ac.ox.cs.sokobanexam.model;

import uk.ac.ox.cs.sokobanexam.util.Dir;

public class Model {
	private IBoard mBoard;
	private Rules mRules;
	public Model(IBoard board, Rules rules) {
		mBoard = board;
		mRules = rules;
	}
	public void setmRules(Rules mRules) {
		this.mRules = mRules;
	}
	public void setmBoard(IBoard mBoard) {
		this.mBoard = mBoard;
	}
	public Rules getmRules() {
		return mRules;
	}
	public IBoard getmBoard() {
		return mBoard;
	}
	
	////////// Logic
	
	public boolean move(Dir direction) {
		if (mBoard.getHumanPoint() == null)
			return false;
		Point from = mBoard.getHumanPoint();
		Point to = from.plus(direction);
		if (!mRules.validateMove(mBoard,from,to))
			return false;
		mRules.applyMove(mBoard,from,to);
		return true;
	}
	public boolean isGameWon() {
		return mRules.isGameWon(mBoard);
	}
}
