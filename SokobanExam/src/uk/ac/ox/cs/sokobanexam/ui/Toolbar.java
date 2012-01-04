package uk.ac.ox.cs.sokobanexam.ui;

import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Arrow;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Crate;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Human;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Nothing;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Room;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Sprite;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Target;
import uk.ac.ox.cs.sokobanexam.domainmodel.sprites.Wall;

public class Toolbar extends JToolBar implements SelectionChangeListener {
	private static final long serialVersionUID = -3632217082483213540L;
	
	private ASModel mModel;
	private JComponent mConfigurationPanel;
	
	@SuppressWarnings("serial")
	private static final Map<Class<? extends Sprite>, String> creatables
			= new HashMap<Class<? extends Sprite>, String>() {{
		put(Wall.class, "Wall");
		put(Crate.class, "Crate");
		put(Arrow.class, "Arrow");
		put(Target.class, "Target");
		put(Human.class, "Player");
	}};
	
	public Toolbar(ASModel model) {
		setModel(model);
		
		setMargin(new Insets(10,10,10,10));
		ButtonGroup group = new ButtonGroup();
		JToggleButton editButton = new JToggleButton("Edit");
		group.add(editButton);
		for (Map.Entry<Class<? extends Sprite>, String> entry : creatables.entrySet()) {
			JToggleButton createButton = new JToggleButton("Create " + entry.getValue());
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
			Room room = model.getBoard().getRoom(model.getSelected());
			Sprite sprite = room.inner() instanceof Nothing ? room : room.inner();
			SpriteConfigurationCreator confCreator = new SpriteConfigurationCreator();
			sprite.accept(confCreator);
			mConfigurationPanel.add(confCreator.getResult());
			mConfigurationPanel.setVisible(true);
		}
	}
}
