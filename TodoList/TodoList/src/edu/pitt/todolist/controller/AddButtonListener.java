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

	    String desc = controller.getView().getInput().getText().trim();
	    
	    if (parentNode != null) {
	    	
	    	
		    String parentInfo = parentNode.getUserObject().toString();
		    System.out.println(parentInfo);
		    
		    if (parentNode.isRoot()) {
		    	// We'll add task to the root level with a parentID of 0.
		    	controller.getModel().addListItem(desc, timeStamp, parentID);
		    	controller.getView().addToList(desc, parentNode.getUserObject().toString());
 				controller.getView().getInput().setText("");
		    	
		    } else {
			    // The user selected a task and we're going to add the new task as a child.
		    	Vector<ListItem> items1 = controller.getModel().getList();
		 		for (ListItem item : items1) {
		 			if(item.getDescription().equals(parentInfo)) {
		 				parentID = item.getIdTodos();
		 			}
		 		}
		 		controller.getModel().addListItem(desc, timeStamp, parentID);
		 	}	
		    
		 // Check that the todo doesn't already exist on the JTree before adding it.
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
