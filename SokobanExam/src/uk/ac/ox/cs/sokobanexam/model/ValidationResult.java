package uk.ac.ox.cs.sokobanexam.model;

/**
 * This class represents answers from the {@link Rules} classes to their clients
 * informing them about the reasons for their decisions.
 */
public class ValidationResult {
	private boolean mLegal;
	private String mMessage;
	public ValidationResult() {
		mLegal = true;
		mMessage = "OK";
	}
	public ValidationResult(String message) {
		mLegal = false;
		mMessage = message;
	}
	public boolean isLegal() {
		return mLegal;
	}
	public String getMessage() {
		return mMessage;
	}
}
