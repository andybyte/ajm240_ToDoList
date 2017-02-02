package edu.pitt.todolist.model;

import java.util.Vector;
import java.sql.*;
import java.net.URL;

//The following steps are involved with making a typical database query:
//– Load the driver
//– Get a connection
//– Execute a statement
//– Parse the result set
//– Close the result set, statement, and connection objects

public class Model {
	private Vector<ListItem> todoList;
	
	
	public Model() {
		this.todoList = new Vector<ListItem>();
		
		String strServerAddress = "jdbc:mysql://sis-teach-01.sis.pitt.edu:3306/ajm240is1017";
		String strUserName = "ajm240is1017";
		String strPassword = "ajm240@pitt.edu";
		String qry = "SELECT * FROM tblToDoList";
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection connector = DriverManager.getConnection(strServerAddress,strUserName,strPassword);
				Statement statement = connector.createStatement();
				ResultSet rs = statement.executeQuery("SELECT * FROM ajm240is1017.Todos;");
				System.out.print(rs);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void addListItem(String description) {
		this.todoList.add(new ListItem(description));
	}
	
	public void deleteListItem(String description) {
		Vector<ListItem> itemsToDelete = new Vector<ListItem>();
		for (ListItem item : todoList) {
			if (item.getDescription().equals(description)) {
				itemsToDelete.add(item);
			}
		}
		for (ListItem item : itemsToDelete) {
			todoList.remove(item);
		}
	}

	public Vector<ListItem> getList() {
		return todoList;
	}
}
