package edu.pitt.todolist.view;

import java.awt.FlowLayout;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @coauthor andymrkva
 *
 */

public class View {
	private JFrame window;
	private JButton addButton;
	private JButton deleteButton;
	private JList todoList;
	private DefaultListModel listModel;
	private JTextField jtxtField;
	private JComboBox<String> cmbUser;
	
	public View() {
		window = new JFrame("ToDoList");
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

	    JPanel listPanel = new JPanel();
	    
	    // Building a list
		listModel = new DefaultListModel();
		todoList = new JList(listModel);
		
		
	    listPanel.add(todoList);
	    
	    panel.add(listPanel);

		JLabel label = new JLabel("Please enter task:");
		panel.add(label);
		
		jtxtField = new JTextField("list item");
		jtxtField.setColumns(20);
		panel.add(jtxtField);

		addButton = new JButton();
		addButton.setText("Add");
		panel.add(addButton);
	    
		deleteButton = new JButton();
		deleteButton.setText("Delete");
		panel.add(deleteButton);
		
		JLabel userLabel = new JLabel("Assigning task to:");
		panel.add(userLabel);
		
		// Combobox to select the user to assign the task.
		
		cmbUser = new JComboBox<String>();
		panel.add(cmbUser);
				
		window.add(panel);
		window.setSize(300, 300);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	/**
	 * @return the cmbUser contains the names of all the users in the User Database.
	 */
	public JComboBox<String> getCmbUser() {
		return cmbUser;
	}

	/**
	 * @param cmbUser the cmbUser to set
	 */
	public void setCmbUser(JComboBox<String> cmbUser) {
		this.cmbUser = cmbUser;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public JList<String> getTodoList() {
		return todoList;
	}

	public JTextField getInput() {
		return jtxtField;
	}

	public void addToList(String description) {
		listModel.addElement(description);
	}

	public void removeFromList(Vector<String> selectedItems) {
		for (int i = 0; i < listModel.size(); i++) {
			if (selectedItems.contains(listModel.getElementAt(i))) {
				listModel.removeElementAt(i);;
			}
		}
	}
	
	public void addToUserCombobox(String name) {
		cmbUser.addItem(name);
	}
}
