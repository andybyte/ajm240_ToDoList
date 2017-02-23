package edu.pitt.todolist.model;

/**
 * @coauthor andymrkva
 *
 */

import java.sql.Timestamp;

public class ListItem {
	
	// Properties for the item
	private String description;
	private int idTodos;
	private Timestamp timeStamp; 
	private int parentID;
	
	public ListItem(int idTodos, String description, java.sql.Timestamp timeStamp, int parentID) {
		this.description = description;
		this.idTodos = idTodos;
		this.timeStamp = timeStamp;
		this.setParentID(parentID);
	}
	
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

	/**
	 * @return the parentID
	 */
	public int getParentID() {
		return parentID;
	}

	/**
	 * @param parentID the parentID to set
	 */
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}


}
