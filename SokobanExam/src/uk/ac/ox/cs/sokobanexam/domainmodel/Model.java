package uk.ac.ox.cs.sokobanexam.domainmodel;

import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite.SemanticType;
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
		assert mRules.validateBoard(mBoard);
		Point from = mBoard.getOccupiedPoints(SemanticType.HUMAN).iterator().next();
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
