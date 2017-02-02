package edu.pitt.todolist.controller;

import edu.pitt.todolist.model.Model;
import edu.pitt.todolist.view.View;

public class Controller {
	private AddButtonListener addButtonListener;
	private DeleteButtonListener deleteButtonListener;
	private View view;
	private Model model;
	
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
		view.getAddButton().addActionListener(new AddButtonListener(this));
		view.getDeleteButton().addActionListener(new DeleteButtonListener(this));
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
