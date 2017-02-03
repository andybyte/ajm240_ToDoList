package edu.pitt.todolist.model;

import java.sql.Timestamp;

//import java.text.SimpleDateFormat;


public class ListItem {
	private String description;
	private int idTodos;
	private Timestamp timeStamp; 
	
	public ListItem(int idTodos, String description, java.sql.Timestamp timeStamp) {
		this.description = description;
		this.idTodos = idTodos;
		this.timeStamp = timeStamp;
	}

//	public ListItem(String description) {
//		this.description = description;
//	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdTodos() {
		return idTodos;
	}

	public void setIdTodos(int idTodos) {
		this.idTodos = idTodos;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}


}
