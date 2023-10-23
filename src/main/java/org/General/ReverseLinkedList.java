package org.General;
class NodeExample {
    int data;
    NodeExample next;

    public NodeExample(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedListExample {
	NodeExample head;

    // Constructor
    public LinkedListExample() {
        this.head = null;
    }

    // Method to reverse the linked list
    public void reverse() {
    	NodeExample prev = null;
    	NodeExample current = head;
    	NodeExample next;

        while (current != null) {
            next = current.next; // Save the next node
            current.next = prev; // Reverse the link
            prev = current;      // Move prev to the current node
            current = next;      // Move current to the next node
        }

        head = prev; // Update the head to the last node (which is now the first)
    }

    // Method to print the linked list
    public void printList() {
    	NodeExample temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Method to add a new node to the linked list
    public void addNode(int data) {
    	NodeExample newNode = new NodeExample(data);
        newNode.next = head;
        head = newNode;
    }
}

public class ReverseLinkedList {
    public static void main(String[] args) {
    	LinkedListExample linkedList = new LinkedListExample();

        // Adding nodes to the linked list
        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.addNode(4);

        System.out.println("Original linked list:");
        linkedList.printList();

        // Reversing the linked list
        linkedList.reverse();

        System.out.println("Reversed linked list:");
        linkedList.printList();
    }
}
