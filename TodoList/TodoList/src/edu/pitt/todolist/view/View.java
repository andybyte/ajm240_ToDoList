package edu.pitt.todolist.view;

import java.awt.FlowLayout;
import java.util.Enumeration;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 * @coauthor andymrkva
 *
 */

public class View {
	private JFrame window;
	private JButton addButton;
	private JButton deleteButton;
	private JTextField jtxtField;
	private JComboBox<String> cmbUser;
	private JTree treeTodos;
	private DefaultTreeModel treeModel;
	
	public View() {
		window = new JFrame("ToDoList");
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
	    JPanel listPanel = new JPanel();
	    
	    // Building a JTree
	    DefaultMutableTreeNode top = new DefaultMutableTreeNode("To Do List");
   
	    treeModel = new DefaultTreeModel(top);
	    treeTodos = new JTree(treeModel);

	    //Where the tree is initialized
	    treeTodos.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	 
	    // Build out interface
	    listPanel.add(treeTodos);
	    panel.add(listPanel);

		JLabel label = new JLabel("Please select parent and enter task:");
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

	public JTree getTodoList() {
		return treeTodos;
	}

	public JTextField getInput() {
		return jtxtField;
	}

	public void addToList(String description, String parentDesc) {

		// Add the task to the root or the parent
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(description);
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode();
		
		if (parentDesc.equals("")) {
			// Add to the root.
			treeModel.insertNodeInto(newNode, root, root.getChildCount());
		} else {
			// Find the parent node and add as a child.			
			Enumeration<DefaultMutableTreeNode> en = root.depthFirstEnumeration();
			while (en.hasMoreElements()) {
			  DefaultMutableTreeNode node = (DefaultMutableTreeNode) en.nextElement();
			  if (node.getUserObject().equals(parentDesc)) {
				  parent = node;
			  }
			}
			// Now insert into the View's model to update the display.
			treeModel.insertNodeInto(newNode, parent, parent.getChildCount());
		}
	}
	
	public void addToUserCombobox(String name) {
		cmbUser.addItem(name);
	}

	public JTree getJTreeParent() {
		return treeTodos;
	}
	
	public DefaultTreeModel getTreeModel() {
		return treeModel;
	}
}
