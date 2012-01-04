package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

import uk.ac.ox.cs.sokobanexam.util.Point;

public interface Sprite {
	// Det her dør nok
	public enum SemanticType {
		NONE (false),
		CRATE (true),
		HUMAN (true),
		TARGET (false),
		WALL (true),
		ARROW_N (true),
		ARROW_E (true),
		ARROW_S (true),
		ARROW_W (true);
		public final boolean solid;
		private SemanticType(boolean solid) {
	        this.solid = solid;
	    }
	}
	SemanticType type();
	
	public Point point();
	
	public void accept(SpriteVisitor visitor);
}
