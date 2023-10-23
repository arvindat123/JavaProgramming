package org.ParameterizeBehaviour;

public class LinkedListDemo {

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		
		list.insertAtHead(3);
		list.insertAtHead(1);
		list.insertAtHead(5);
		list.insertAtHead(2);
		list.insertAtHead(9);
		list.insertAtHead(6);
		list.insertAtHead(8);
		
		System.out.println(list);
		System.out.println("lenght of the List " + list.lenght());
		
		list.deleteFromHead();
		
		System.out.println(list);
		System.out.println(list.lenght());
		
		System.out.println(list.find(15));
		
		
	}

}
