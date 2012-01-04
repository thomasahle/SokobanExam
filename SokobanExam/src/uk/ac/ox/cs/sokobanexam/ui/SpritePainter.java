package uk.ac.ox.cs.sokobanexam.ui;

import java.awt.Graphics2D;

import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Arrow;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Crate;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Floor;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Human;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.SpriteVisitor;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Target;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Wall;

public class SpritePainter implements SpriteVisitor {
	
	protected final Graphics2D g;
	
	public SpritePainter(Graphics2D g) {
		this.g = g;
	}
	
	@Override
	public void visit(Arrow sprite) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Crate sprite) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Floor sprite) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Human sprite) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Target sprite) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Wall sprite) {
		// TODO Auto-generated method stub

	}

}
