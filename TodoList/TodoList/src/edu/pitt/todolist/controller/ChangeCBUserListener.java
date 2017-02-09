/**
 * 
 */
package edu.pitt.todolist.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @author andymrkva
 *
 */
public class ChangeCBUserListener implements ActionListener {
	Controller controller;
	
	public ChangeCBUserListener(Controller controller) {
		this.controller = controller;
	}
	
	// Retrieve the current user from the View's combobox and store it into the model for assigning tasks.
	public void actionPerformed(ActionEvent e) {
		int i = this.controller.getView().getCmbUser().getSelectedIndex();
		int id = this.controller.getModel().getUserList().get(i).getUserID();
		this.controller.getModel().setCurrentUser(id);
    }
	
	
}
