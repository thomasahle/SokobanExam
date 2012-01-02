package uk.ac.ox.cs.sokobanexam.model;

import uk.ac.ox.cs.sokobanexam.util.Dir;

public class Model {
	private Board mBoard;
	private Rules mRules;
	public Model(Board board, Rules rules) {
		mBoard = board;
		mRules = rules;
	}
	public void setmRules(Rules mRules) {
		this.mRules = mRules;
	}
	public void setmBoard(Board mBoard) {
		this.mBoard = mBoard;
	}
	public Rules getmRules() {
		return mRules;
	}
	public Board getmBoard() {
		return mBoard;
	}
	
	////////// Logic
	
	
	public boolean move(Dir direction) {
		Point from = mBoard.getHumanPoint();
		Point to = from.plus(direction);
		if (!mRules.validateMove(mBoard,from,to))
			return false;
		//TODO
		return true;
	}
	public boolean isFinished() {
		return false;
	}
}
