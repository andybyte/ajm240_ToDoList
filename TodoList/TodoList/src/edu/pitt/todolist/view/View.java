package edu.pitt.todolist.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.TextField;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View {
	private JFrame window;
	private JButton addButton;
	private JButton deleteButton;
	private List todoList;
	private TextField textField;
	
	public View() {
		window = new JFrame("ToDoList");
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

	    JPanel listPanel = new JPanel();
	    
	    todoList = new List(10, true);
	    todoList.setMaximumSize(new Dimension(280, 200));
	    todoList.setMinimumSize(new Dimension(280, 200));
	    listPanel.add(todoList);
	    
	    panel.add(listPanel);

		JLabel label = new JLabel("Please enter item here:");
		panel.add(label);
		textField = new TextField("list item");
		panel.add(textField);

		addButton = new JButton();
		addButton.setText("Add");
		panel.add(addButton);
	    
		deleteButton = new JButton();
		deleteButton.setText("Delete");
		panel.add(deleteButton);
	    
		window.add(panel);
		window.setSize(300, 300);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public List getTodoList() {
		return todoList;
	}

	public TextField getInput() {
		return textField;
	}

	public void addToList(String description) {
		todoList.add(description);
	}

	public void removeFromList(Vector<String> selectedItems) {
		Vector<String> list = new Vector<String>();
		for (String listItem : todoList.getItems()) {
			if (!selectedItems.contains(listItem)) {
				list.add(listItem);
			}
		}
		todoList.removeAll();
		for (String item : list) {
			todoList.add(item);
		}
	}
}
