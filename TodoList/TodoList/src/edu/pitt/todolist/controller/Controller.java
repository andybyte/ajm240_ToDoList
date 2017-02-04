package edu.pitt.todolist.controller;

import java.util.Vector;

import edu.pitt.todolist.model.ListItem;
import edu.pitt.todolist.model.Model;
import edu.pitt.todolist.view.View;

public class Controller {
	private AddButtonListener addButtonListener;
	private DeleteButtonListener deleteButtonListener;
	private View view;
	private Model model;
	
	public Controller(View view, Model model) {
		// Use a vector to grab the list from the model and send it to the View to update the view's list.
		Vector<ListItem> listFromDB;
		
		this.view = view;
		this.model = model;
		view.getAddButton().addActionListener(new AddButtonListener(this));
		view.getDeleteButton().addActionListener(new DeleteButtonListener(this));
		
		// Create a vector from the model
		listFromDB = this.model.getList();
		
		// Cycle through vector and add each item to the view's list.
		for (ListItem item : listFromDB) {
			String desc = item.getDescription();
			this.getView().addToList(desc);
		}
		
	}
	
	public AddButtonListener getAddButtonListener() {
		return addButtonListener;
	}

	public DeleteButtonListener getDeleteButtonListener() {
		return deleteButtonListener;
	}

	public View getView() {
		return view;
	}

	public Model getModel() {
		return model;
	}
}
