package edu.pitt.todolist.model;

import java.util.Vector;
import java.sql.*;

//The following steps are involved with making a typical database query:
//– Load the driver
//– Get a connection
//– Execute a statement
//– Parse the result set
//– Close the result set, statement, and connection objects

public class Model {
	private Vector<ListItem> todoList;
	private Connection connector;
	
	
	public Model() {
		this.todoList = new Vector<ListItem>();
		
		String strServerAddress = "jdbc:mysql://sis-teach-01.sis.pitt.edu:3306/ajm240is1017";
		String strUserName = "ajm240is1017";
		String strPassword = "ajm240@pitt.edu";
		String qry = "SELECT * FROM ajm240is1017.Todos;";
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
				connector = DriverManager.getConnection(strServerAddress,strUserName,strPassword);
				Statement statement = connector.createStatement();
				ResultSet rs = statement.executeQuery(qry);
				while(rs.next()) {
					this.todoList.add(new ListItem(rs.getInt("idTodos"),rs.getString("Description"), rs.getTimestamp("timeStamp")));
				}
				
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

	public void addListItem(int idTodos, String description, Timestamp timeStamp) {
		int checkString = 0;
		int newID = 0;
		int rowCount = 0;
		
		if (description.equals("")) {
			checkString = 1;
		} else {
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
				// See if the table is empty
				String qry = "SELECT * FROM ajm240is1017.Todos;";
				Statement statement = this.connector.createStatement();
				ResultSet rs = statement.executeQuery(qry);
				rs.last();
				rowCount = rs.getRow();
				rs.close();
				
				if (rowCount == 0) {
					// The table is empty and we can insert a 0 ID task.
					String qryAddZero = "INSERT INTO `ajm240is1017`.`Todos` (`idTodos`, `Description`, `Timestamp`) VALUES ('" + newID + "', '"+description+"','"+timeStamp+"');";
					Statement statementZero = this.connector.createStatement();
					statementZero.execute(qryAddZero);
					System.out.println("Item Added at " + newID);
					rs.close();
					
				} else {
					
					// The table has at least one row and we can attempt a new ID.
					String getMax = "SELECT MAX(`idTodos`) FROM `ajm240is1017`.`Todos`";
					Statement statementMax = this.connector.createStatement();
					ResultSet rsMax = statementMax.executeQuery(getMax);
					while (rsMax.next()) {
						newID = Integer.parseInt(rsMax.getString(1)) + 1;	
					}
					rsMax.close();
					
					String qryAddNew = "INSERT INTO `ajm240is1017`.`Todos` (`idTodos`, `Description`, `Timestamp`) VALUES ('" + newID + "', '"+description+"','"+timeStamp+"');";
					Statement statementNew = this.connector.createStatement();
					statementNew.execute(qryAddNew);
					System.out.println("Item Added at " + newID);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			System.out.println("The task already exists or the field is empty. Please add a new task.");
		}
		
	}
	
	public void deleteListItem(String description) {
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
		
		Statement statement;
		try {
			for (int i : vectorRemove){
				statement = this.connector.createStatement();
				String qry = "DELETE FROM `ajm240is1017`.`Todos` WHERE `idTodos`='"+i+"';";
				statement.execute(qry);
				System.out.println("Item Removed at " + i);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Vector<ListItem> getList() {
		return todoList;
	}
}
