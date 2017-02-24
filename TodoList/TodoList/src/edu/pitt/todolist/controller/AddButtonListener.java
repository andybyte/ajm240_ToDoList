package edu.pitt.todolist.controller;

/**
 * @coauthor andymrkva
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.pitt.todolist.model.ListItem;

public class AddButtonListener implements ActionListener {
	Controller controller;
	
	public AddButtonListener(Controller controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		int parentID = 0;
		
		// Grab the timestamp of when the button was pressed to create the task.
		
		java.sql.Timestamp timeStamp = new Timestamp(Calendar.getInstance().getTimeInMillis());

		// Get the parent ID if there is a parent.
	    DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) controller.getView().getJTreeParent().getLastSelectedPathComponent();
	    
	    // Get text for task.
	    String desc = controller.getView().getInput().getText().trim();
	    
	    // Task must have a parent (root is the highest level). If it does, we'll add it to the list. 
	    if (parentNode != null) {
	    	
		    String parentInfo = parentNode.getUserObject().toString();
		    
		    // If parent is NOT the root we will get the parent ID.
		    if (!parentNode.isRoot()) {
			    // The user selected a task and we're going to add the new task to the Model using the Model's list to find the parent's ID.
		    	Vector<ListItem> items1 = controller.getModel().getList();
		 		for (ListItem item : items1) {
		 			if(item.getDescription().equals(parentInfo)) {
		 				parentID = item.getIdTodos();
		 			}
		 		}
		 		
		 	}	
		    controller.getModel().addListItem(desc, timeStamp, parentID);
		    
		 // Check that the todo doesn't already exist on the JTree before adding it to the View.
			Vector<ListItem> items2 = controller.getModel().getList();
	 		for (ListItem item : items2) {
	 			if(item.getTimeStamp() == timeStamp) {
	 				controller.getView().addToList(desc, parentInfo);
	 				controller.getView().getInput().setText("");
	 			}
	 		}
	    } else {
	    	JOptionPane.showMessageDialog(null, "You must select a parent or the root first.");
	    }
	 }
}
