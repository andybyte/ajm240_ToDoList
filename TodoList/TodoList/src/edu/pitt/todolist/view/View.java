package edu.pitt.todolist.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.TextField;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View {
	private JFrame window;
	private JButton addButton;
	private JButton deleteButton;
	private JList todoList;
	private DefaultListModel listModel;
	private JTextField jtxtField;
	
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

		JLabel label = new JLabel("Please enter item here:");
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
}
