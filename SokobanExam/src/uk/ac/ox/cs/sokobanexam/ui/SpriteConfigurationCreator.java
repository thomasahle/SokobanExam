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
	public void setSpriteChangeListener(SpriteChangeListener listener) {
		mSpriteChangeListener = listener;
	}
	
	@Override
	public void visit(final Arrow sprite) {
		// Possibility: Create a hole hiearcy of configurators,
		// 	            "EnumConfigurator" could then be a superclass
		
		// TODO: This will break if more enum types are added
		String[] labels = {"↑Up", "→Right", "↓Down", "←Left"};
		final JComboBox combobox = new JComboBox(labels);
		combobox.setSelectedIndex(Arrays.binarySearch(Dir.values(), sprite.getDirection()));
		combobox.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				Dir newDirection = Dir.values()[combobox.getSelectedIndex()];
				if (newDirection != sprite.getDirection())
					mSpriteChangeListener.onSpriteChanged(
							sprite,
							sprite.setDirection(newDirection));
			}
		});
		mResult = combobox;
	}

	@Override
	public void visit(final Crate sprite) {
		// TODO: This will break if more enum types are added
		String[] labels = {"Red", "Blue", "Green"};
		final JComboBox combobox = new JComboBox(labels);
		combobox.setSelectedIndex(Arrays.binarySearch(Color.values(), sprite.getColor()));
		combobox.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				Color newColor = Color.values()[combobox.getSelectedIndex()];
				if (newColor != sprite.getColor())
					mSpriteChangeListener.onSpriteChanged(
							sprite,
							sprite.setColor(newColor));
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
		textField.setText(sprite.getWriting());
		textField.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				String newText = textField.getText();
				if (!newText.equals(sprite.getWriting()))
					mSpriteChangeListener.onSpriteChanged(
							sprite,
							sprite.setWriting(newText));
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
