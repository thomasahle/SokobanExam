package uk.ac.ox.cs.sokobanexam.domainmodel.sprites;

public interface Sprite {
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
	
	void accept(SpriteVisitor visitor);
}
