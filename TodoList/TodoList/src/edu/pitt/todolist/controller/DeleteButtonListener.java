package edu.pitt.todolist.controller;

/**
 * @coauthor andymrkva
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;

public class DeleteButtonListener implements ActionListener {
	Controller controller;
	
	public DeleteButtonListener(Controller controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		// Retrieve selected tasks from the View for removal.
		
	    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) controller.getView().getJTreeParent().getLastSelectedPathComponent();
		
	    if (selectedNode != null) {
	    	String description = selectedNode.getUserObject().toString();

			Vector<String> selectedItemVector = new Vector<String>();
			selectedItemVector.addElement(description);
			controller.getModel().deleteListItem(description);	
			controller.getView().removeFromList(selectedItemVector);
			controller.getView().getTreeModel().removeNodeFromParent(selectedNode);
	    }
    }
}
