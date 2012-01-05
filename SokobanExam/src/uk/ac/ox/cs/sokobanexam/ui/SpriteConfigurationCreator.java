package uk.ac.ox.cs.sokobanexam.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Arrow;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Crate;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Floor;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Human;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Nothing;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.SpriteVisitor;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Target;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Wall;
import uk.ac.ox.cs.sokobanexam.util.Color;
import uk.ac.ox.cs.sokobanexam.util.Dir;

public class SpriteConfigurationCreator implements SpriteVisitor {
	
	private JComponent mResult;
	
	private SpriteChangeListener mSpriteChangeListener;
	public SpriteConfigurationCreator(SpriteChangeListener listener) {
		mSpriteChangeListener = listener;
	}
	
	@Override
	public void visit(final Arrow sprite) {
		// Possibility: Create a hole hiearcy of configurators,
		//              "EnumConfigurator" could then be an abstract superclass
		
		final Dir[] directions = {Dir.NORTH, Dir.EAST, Dir.SOUTH, Dir.WEST};
		String[] labels = {"↑ Up", "→ Right", "↓ Down", "← Left"};
		final JComboBox combobox = new JComboBox(labels);
		combobox.setSelectedIndex(Arrays.binarySearch(directions, sprite.direction()));
		combobox.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				Dir newDirection = directions[combobox.getSelectedIndex()];
				if (newDirection != sprite.direction())
					mSpriteChangeListener.onSpriteChanged(
							sprite,
							new Arrow(sprite.inner(), newDirection));
			}
		});
		mResult = combobox;
	}

	@Override
	public void visit(final Crate sprite) {
		final Color[] colors = {Color.RED, Color.GREEN, Color.BLUE};
		String[] labels = {"Red", "Green", "Blue"};
		final JComboBox combobox = new JComboBox(labels);
		combobox.setSelectedIndex(Arrays.binarySearch(colors, sprite.color()));
		combobox.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				Color newColor = colors[combobox.getSelectedIndex()];
				if (newColor != sprite.color())
					mSpriteChangeListener.onSpriteChanged(
							sprite,
							new Crate(sprite.point(), newColor));
			}
		});
		mResult = combobox;
	}

	@Override
	public void visit(Floor sprite) {
		mResult = new JPanel();
	}

	@Override
	public void visit(Human sprite) {
		mResult = new JPanel();
	}

	@Override
	public void visit(Target sprite) {
		mResult = new JPanel();
	}

	@Override
	public void visit(final Wall sprite) {
		final JTextField textField = new JTextField(1);
		textField.setText(sprite.writing());
		textField.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				String newText = textField.getText();
				if (!newText.equals(sprite.writing()))
					mSpriteChangeListener.onSpriteChanged(
							sprite,
							new Wall(sprite.inner(), newText));
			}
		});
		mResult = textField;
	}

	public JComponent getResult() {
		return mResult;
	}

	@Override
	public void visit(Nothing nothing) {
		mResult = new JPanel();
	}
}
