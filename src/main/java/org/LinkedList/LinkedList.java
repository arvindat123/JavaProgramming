package org.LinkedList;

public class LinkedList {
	private Node head;
	
	public void insertAtHead( int data) {
		Node newNode = new Node(data);
		newNode.next= this.head;
		this.head = newNode;
	}
	
	public int lenght() {
		int lenght = 0;
		Node current = this.head;
		while(current != null) {
			lenght ++;
			current = current.next;
		}
		return lenght;
	}
	
	public void deleteFromHead() {
		this.head = this.head.next;
	}
	
	public Node find(int data) {
		Node current = this.head;
		while(current != null) {
			if(current.data == data) {
				return current;
			}
			current = current.next;
		}
		return null;
	}

	public boolean hasCycle() {
		Node slowNode = head;
		Node fastNode = head;

		while (fastNode != null && fastNode.next != null) {
			fastNode = fastNode.next.next;
			slowNode = slowNode.next;
			if (fastNode == slowNode) {
				return true;
			}
		}
		return false;
	}

	public int lengthOfCycle() {
		Node fastNode = head;
		Node slowNode = head;
		int size = 0;
		while (fastNode != null && fastNode.next != null) {
			fastNode = fastNode.next.next;
			slowNode = slowNode.next;
			if (fastNode == slowNode) {
				Node temp = slowNode;
				do {
					temp = temp.next;
					size++;
				} while (temp != slowNode);
				return size;
			}
		}

		return size;
	}

	public Node middleNode(Node head) {
		Node slow = head;
		Node fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	
	@Override
	public String toString() {
		String result = "{ ";
		Node current = this.head;
		while (current != null) {
			result += current.toString() + ",";
			current = current.next;
		}
		result += "}";
		System.out.println(result);
		return result;
	}
	private class Node {
		private int data;
		private Node next;

		public Node (int data) {
			this.data = data;
		}



	}
}