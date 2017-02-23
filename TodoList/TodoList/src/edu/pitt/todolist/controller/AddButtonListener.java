package edu.pitt.todolist.controller;

/**
 * @coauthor andymrkva
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;

import edu.pitt.todolist.model.ListItem;

public class AddButtonListener implements ActionListener {
	Controller controller;
	
	public AddButtonListener(Controller controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		// Grab the timestamp of when the button was pressed to create the task.
		
		java.sql.Timestamp timeStamp = new Timestamp(Calendar.getInstance().getTimeInMillis());

		// Get the parent ID if there is a parent.
	    DefaultMutableTreeNode parent = (DefaultMutableTreeNode) controller.getView().getJTreeParent().getLastSelectedPathComponent();

	    //Check if the node is null.
	    if (parent == null)	
	    	// There is no parent selected.
	    return;
	    
	    Object parentInfo = parent.getUserObject();
	    int parentID = 0;
	    
	    String desc = controller.getView().getInput().getText();
	    
	    if (parent.isRoot()) {
	    	// We'll add task to the root level with a parentID of 0.
	    } else {
	    	// The user selected a task and we're going to add the new task as a child.
	    	Vector<ListItem> items = controller.getModel().getList();
	 		for (ListItem item : items) {
	 			if(item.getDescription().equals(parentInfo)) {
	 				parentID = item.getIdTodos();
	 			}
	 		}
	 	}
	    
	    
	    
//			int parent = controller.getView().getSelectedTask();
			controller.getModel().addListItem(0, desc, timeStamp, parentID);
			controller.getView().addToList(desc, parentID, parent);
			controller.getView().getInput().setText("");
	  }
}
