/**
 * 
 */
package edu.pitt.todolist.model;

/**
 * @author andymrkva
 *
 */
public class User {
	// Properties of a user.
	private int userID;
	private String firstName;
	private String lastName;
	
	/**
	 * @param userID
	 * @param firstName
	 * @param lastName
	 */
	
	// User constructor
	
	public User(int userID, String firstName, String lastName) {
		super();
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	
	// Getters and setters
	
	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}


	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	

}
