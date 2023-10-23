package org.ParameterizeBehaviour;

public class DoublyLinkedList {
	private DoublyLinkedNode head;
	
	public void insertAtHead(int data) {
		DoublyLinkedNode newNode = new DoublyLinkedNode(data);
		newNode.setNextNode(this.head);
		if(this.head != null) {//to check if list is empty
			this.head.setPreviousNode(newNode);
		}
		this.head = newNode;
	}
	
	public void printList(DoublyLinkedList data) {
		
	}
	
	public int length() {
		if(head == null) {
			return 0;
		}
		int length = 0;
		DoublyLinkedNode current = this.head;
		
		while(current != null) {
			length ++;
			current = current.getNextNode();
		}
		return length;
	}
}
