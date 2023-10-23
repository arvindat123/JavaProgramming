
package org.ParameterizeBehaviour;
public class LinkedList {
	private Node head;
	
	public void insertAtHead( int data) {
		Node newNode = new Node(data);
		newNode.setNextNode(this.head);
		this.head = newNode;
	}
	
	public int lenght() {
		int lenght = 0;
		Node current = this.head;
		while(current != null) {
			lenght ++;
			current = current.getNextNode();
		}
		return lenght;
	}
	
	public void deleteFromHead() {
		this.head = this.head.getNextNode();
	}
	
	public Node find(int data) {
		Node current = this.head;
		while(current != null) {
			if(current.getData() == data) {
				return current;
			}
			current = current.getNextNode();
		}
		return null;
	}
	
	@Override
	public String toString() {
		String result = "{ ";
		Node current = this.head;
		while (current != null) {
			result += current.toString() + ",";
			current = current.getNextNode();
		}
		result += "}";
		System.out.println(result);
		return result;
	}
}