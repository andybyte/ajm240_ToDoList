package edu.pitt.todolist.model;

/**
 * @coauthor andymrkva
 *
 */

import java.util.HashMap;
import java.util.Vector;

import javax.swing.JOptionPane;

import java.sql.*;

//The following steps are involved with making a typical database query:
//– Load the driver
//– Get a connection
//– Execute a statement
//– Parse the result set
//– Close the result set, statement, and connection objects

public class Model {
	private Vector<ListItem> todoList;
	private Vector<User> userList;
	private Connection connector;
	private HashMap<Long,Long> mapTodoToUser;
	private int currentUser;
	
	public Model() {
		this.todoList = new Vector<ListItem>();
		this.userList = new Vector<User>();
		this.mapTodoToUser = new HashMap<Long,Long>();	
		this.currentUser = 0;
		
		// Parts to create a connection to the databases:
		String strServerAddress = "jdbc:mysql://sis-teach-01.sis.pitt.edu:3306/ajm240is1017";
		String strUserName = "ajm240is1017";
		String strPassword = "ajm240@pitt.edu";
		
		// Connect to Todos database and add each item to the Model's Vector of ListItems:
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
				connector = DriverManager.getConnection(strServerAddress,strUserName,strPassword);
				Statement statement = connector.createStatement();
				String qry = "SELECT * FROM ajm240is1017.Todos;";
				ResultSet rs = statement.executeQuery(qry);
				while(rs.next()) {
					this.todoList.add(new ListItem(rs.getInt("idTodos"),rs.getString("Description"), rs.getTimestamp("Timestamp"), rs.getInt("parentID")));
				}	
				rs.close();
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
		
		// Connect to Users database and add each item to the Model's Vector of Users:
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
				connector = DriverManager.getConnection(strServerAddress,strUserName,strPassword);
				Statement statement = connector.createStatement();
				String qry = "SELECT * FROM ajm240is1017.Users;";
				ResultSet rs = statement.executeQuery(qry);
				while(rs.next()) {
					this.userList.add(new User(rs.getInt("idUsers"),rs.getString("FirstName"), rs.getString("LastName")));
				}	
				rs.close();
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
		
		// Create the mapping table Todos_Users
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
				connector = DriverManager.getConnection(strServerAddress,strUserName,strPassword);
				Statement statement = connector.createStatement();
				String qry = "SELECT * FROM ajm240is1017.Todos_Users;";
				ResultSet rs = statement.executeQuery(qry);
				while(rs.next()) {
					this.mapTodoToUser.put(new Long(rs.getInt("todo_id")), new Long(rs.getInt("user_id")));
				}	
				rs.close();
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

	/**
	 * @param idTodos Unique ID for the task.
	 * @param description Description of the task.
	 * @param timeStamp Timestamp of the task.
	 */
	public void addListItem(String description, Timestamp timeStamp, int parentID) {
		int checkString = 0;
		int newID = 1;
		int rowCount = 0;
		
		// Check to see if the task is empty or a string:
		if (description.equals("")) {
			checkString = 1;
		} else {
			
			// Check to see if the task already exists in the list:
			try {
				Statement statement = this.connector.createStatement();
				String qry = "SELECT * FROM ajm240is1017.Todos;";
				ResultSet rs = statement.executeQuery(qry);
				while (rs.next()) {
					if (rs.getString("Description").equals(description)) {
						checkString += 1;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (checkString == 0) {
			
			try {
				// See if the table is empty, if so we'll add a task with ID 0.
				String qry = "SELECT * FROM ajm240is1017.Todos;";
				Statement statement = this.connector.createStatement();
				ResultSet rs = statement.executeQuery(qry);
				rs.last();
				rowCount = rs.getRow();
				rs.close();
				
				if (rowCount == 0) {
					// The table is empty and we can insert a 1 ID task.
					String qryAddZero = "INSERT INTO `ajm240is1017`.`Todos` (`idTodos`, `Description`, `Timestamp`, `parentID`) VALUES ('" + newID + "', '"+description+"','"+timeStamp+"','"+parentID+"');";
					Statement statementZero = this.connector.createStatement();
					statementZero.execute(qryAddZero);
					rs.close();
					
				} else {
					
					// The table has at least one row and we can attempt a new ID by taking the highest ID number and adding 1.
					String getMax = "SELECT MAX(`idTodos`) FROM `ajm240is1017`.`Todos`";
					Statement statementMax = this.connector.createStatement();
					ResultSet rsMax = statementMax.executeQuery(getMax);
					while (rsMax.next()) {
						System.out.println(newID);
						newID = Integer.parseInt(rsMax.getString(1)) + 1;
						System.out.println(newID);
					}
					rsMax.close();
					
					// Create a new task in the database.
					String qryAddNew = "INSERT INTO `ajm240is1017`.`Todos` (`idTodos`, `Description`, `Timestamp`, `parentID`) VALUES ('" + newID + "', '"+description+"','"+timeStamp+"','"+parentID+"');";
					Statement statementNew = this.connector.createStatement();
					statementNew.execute(qryAddNew);
					
					// Add to mapping
					String qryAddNewMap = "INSERT INTO `ajm240is1017`.`Todos_Users` (`todo_id`, `user_id`) VALUES ('" + newID + "', '"+this.currentUser+"');";
					Statement statementNewMap = this.connector.createStatement();
					statementNewMap.execute(qryAddNewMap);
					this.mapTodoToUser.put(new Long(newID), new Long(this.currentUser));

				}
				
				// Add to Vector of items.
				this.todoList.add(new ListItem(newID, description, timeStamp, parentID));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			// Item already exists or the task string is empty.
			System.out.println("The task already exists or the field is empty. Please add a new task.");
			JOptionPane.showMessageDialog(null, "The task already exists or the field is empty. Please add a new task.");
		}
		
	}
	
	/**
	 * @param description Description of the task.
	 */
	
	public void deleteListItem(String description) {
		
		// Grab which items are selected and delete them from the model and the database.
		Vector<Integer> vectorRemove = new Vector<Integer>();
		Vector<ListItem> itemsToDelete = new Vector<ListItem>();
		for (ListItem item : todoList) {
			if (item.getDescription().equals(description)) {
				itemsToDelete.add(item);
				vectorRemove.add(item.getIdTodos());
			}
		}
		for (ListItem item : itemsToDelete) {
			todoList.remove(item); 
		}
		
		// Now remove from the database.
		Statement statement;
		try {
			for (int i : vectorRemove){
				statement = this.connector.createStatement();
				String qry = "DELETE FROM `ajm240is1017`.`Todos` WHERE `idTodos`='"+i+"';";
				statement.execute(qry);
				
				// Remove from mapping
				String qryRemoveFromMap = "DELETE FROM `ajm240is1017`.`Todos_Users` WHERE `todo_id`='"+i+"';";
//				DELETE FROM `ajm240is1017`.`Todos_Users` WHERE `todo_id`='6';
				Statement statementRemoveFromMap = this.connector.createStatement();
				statementRemoveFromMap.execute(qryRemoveFromMap);
				this.mapTodoToUser.remove(i, this.currentUser);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Vector<ListItem> getList() {
		return todoList;
	}
	
	public Vector<User> getUserList() {
		return userList;
	}
	

	/**
	 * @return the currentUser so that we can assign them to the new task.
	 */
	public int getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(int currentUser) {
		this.currentUser = currentUser;
	}
	
	public String getDescriptionFromID (int id) {
		for (ListItem item: this.getList()) {
			if (item.getIdTodos() == id) {
				return item.getDescription();
			}
		}
		return "";
	}
}
