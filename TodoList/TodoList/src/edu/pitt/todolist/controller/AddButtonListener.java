package edu.pitt.todolist.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButtonListener implements ActionListener {
	Controller controller;
	
	public AddButtonListener(Controller controller) {
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent e) {
		String desc = controller.getView().getInput().getText();
		controller.getModel().addListItem(desc);
		controller.getView().addToList(desc);
		controller.getView().getInput().setText("");
    }
}
