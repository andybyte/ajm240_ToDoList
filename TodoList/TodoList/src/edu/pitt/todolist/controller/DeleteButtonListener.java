package edu.pitt.todolist.controller;

/**
 * @coauthor andymrkva
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class DeleteButtonListener implements ActionListener {
	Controller controller;
	
	public DeleteButtonListener(Controller controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {	
		
		// Retrieve selected tasks from the View for removal.
		
		List<String> selectedItems = controller.getView().getTodoList().getSelectedValuesList();
		Vector<String> selectedItemVector = new Vector<String>();
		for (String selectedItem : selectedItems) {
			controller.getModel().deleteListItem(selectedItem);
			selectedItemVector.add(selectedItem);
		}
		controller.getView().removeFromList(selectedItemVector);
    }
}
