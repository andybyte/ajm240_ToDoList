package edu.pitt.todolist.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Calendar;

public class AddButtonListener implements ActionListener {
	Controller controller;
	
	public AddButtonListener(Controller controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		// Grab the timestamp of when the button was pressed to create the task.
		
		java.sql.Timestamp timeStamp = new Timestamp(Calendar.getInstance().getTimeInMillis());

		String desc = controller.getView().getInput().getText();
		controller.getModel().addListItem(0, desc, timeStamp);
		controller.getView().addToList(desc);
		controller.getView().getInput().setText("");
    }
}
