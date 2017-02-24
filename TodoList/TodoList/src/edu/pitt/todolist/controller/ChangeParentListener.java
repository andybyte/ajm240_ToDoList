/**
 * 
 */
package edu.pitt.todolist.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author andymrkva
 *
 */
public class ChangeParentListener implements TreeSelectionListener{
	Controller controller;
	
	public ChangeParentListener(Controller controller) {
		this.controller = controller;
	}
	
	// Retrieve the current user from the View's combobox and store it into the model for assigning tasks.
	public void valueChanged(TreeSelectionEvent e) {
//		String test = (String) this.controller.getView().getTodoList().getLastSelectedPathComponent();
//		int id = this.controller.getModel().getUserList().get(i).getUserID();
//		this.controller.getModel().setCurrentUser(id);
		
	    //Returns the last path element of the selection.
	    //This method is useful only when the selection model allows a single selection.
	    DefaultMutableTreeNode node = (DefaultMutableTreeNode) controller.getView().getJTreeParent().getLastSelectedPathComponent();

	    if (node == null)
	    //Nothing is selected.  
	    return;

	    Object nodeInfo = node.getUserObject();
    }

}
