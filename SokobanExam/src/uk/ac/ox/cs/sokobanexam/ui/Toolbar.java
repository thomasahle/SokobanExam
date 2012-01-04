package uk.ac.ox.cs.sokobanexam.ui;

import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite.SemanticType;

public class Toolbar extends JToolBar implements SelectionChangeListener {
	private static final long serialVersionUID = -3632217082483213540L;
	
	private ASModel mModel;
	private JComponent mConfigurationPanel;
	
	private static SemanticType[] creatables = new SemanticType[] {
		SemanticType.WALL, SemanticType.CRATE, SemanticType.ARROW_N,
		SemanticType.TARGET, SemanticType.HUMAN
	};
	
	public Toolbar(ASModel model) {
		setModel(model);
		
		setMargin(new Insets(10,10,10,10));
		ButtonGroup group = new ButtonGroup();
		JToggleButton editButton = new JToggleButton("Edit");
		group.add(editButton);
		for (SemanticType type : creatables) {
			JToggleButton createButton = new JToggleButton("Create " + type);
			group.add(createButton);
		}
		JToggleButton playButton = new JToggleButton("Play");
		group.add(playButton);
		
		for (Enumeration<AbstractButton> e = group.getElements(); e.hasMoreElements(); )
			add(e.nextElement());
		
		editButton.doClick();
		
		addSeparator();
		
		mConfigurationPanel = new JPanel();
		mConfigurationPanel.setLayout(new FlowLayout());
		JButton deleteButton = new JButton("Delete");
		mConfigurationPanel.add(deleteButton);
		deleteButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				mModel.deleteSelected();
			}
		});
	}
	
	
	public ASModel getModel() {
		return mModel;
	}
	public void setModel(ASModel model) {
		this.mModel = model;
		model.setSelectionChangeListener(this);
	}


	@Override
	public void onSelectionChange(ASModel model) {
		// Clear any old, custom configuration
		while (mConfigurationPanel.getComponentCount() > 1)
			mConfigurationPanel.remove(1);
		// If nothing is selected, we simply hide the component
		if (model.getSelected() == null) {
			mConfigurationPanel.setVisible(false);
		}
		// Otherwise we generate a new component with the selected sprite
		else {
			Sprite sprite = model.getBoard().getTopSpriteAt(model.getSelected());
			SpriteConfigurationCreator confCreator = new SpriteConfigurationCreator();
			sprite.accept(confCreator);
			mConfigurationPanel.add(confCreator.getResult());
			mConfigurationPanel.setVisible(true);
		}
	}
}
