package uk.ac.ox.cs.sokobanexam.util;


public enum Color {
	RED (new java.awt.Color(0xff0000)),
	GREEN (new java.awt.Color(0x00bb00)),
	BLUE (new java.awt.Color(0x0000ff));
	
	java.awt.Color mColor;
	Color(java.awt.Color color) {
		mColor = color;
	}
	public java.awt.Color getColor() {
		return mColor;
	}
}
