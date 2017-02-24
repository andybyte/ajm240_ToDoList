package edu.pitt.todolist.controller;

/**
 * @coauthor andymrkva
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;

public class DeleteButtonListener implements ActionListener {
	Controller controller;
	
	public DeleteButtonListener(Controller controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		// Retrieve selected task from the View for removal.
		
	    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) controller.getView().getJTreeParent().getLastSelectedPathComponent();
		
	    // Check that a task is selected and then remove it from the Model and view.
	    if (selectedNode != null) {
	    	String description = selectedNode.getUserObject().toString();
			controller.getModel().deleteListItem(description);	
			controller.getView().getTreeModel().removeNodeFromParent(selectedNode);
	    }
    }
}
