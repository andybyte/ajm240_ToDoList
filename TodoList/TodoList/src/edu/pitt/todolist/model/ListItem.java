package edu.pitt.todolist.model;

import java.text.SimpleDateFormat;

public class ListItem {
	private String description;
	private int idTodos;
	private SimpleDateFormat timeStamp; 
	
	public ListItem(int idTodos, String description, SimpleDateFormat timeStamp) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
