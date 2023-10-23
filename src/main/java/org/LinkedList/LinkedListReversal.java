package org.LinkedList;


public class LinkedListReversal {

    Node head;
    private int size;
    LinkedListReversal(){
        head = null;
        this.size = 0;
    }

    class Node{
        String data;
        Node next;

        public Node(String data) {
            this.data = data;
            this.next = null;
            size++;
        }
    }



    //Add at the starting point of linkedlist
    public void firstAdd(String data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    //add at the end of the linkedlist
    public void lastAdd(String data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }
        Node currNode = head;
        while(currNode.next != null){
            currNode = currNode.next;
        }
        currNode.next = newNode;
    }

    //print list
    public void printList(){
        if(head == null){
            System.out.println("List is empty");
            return;
        }
        Node currNode = head;
        while(currNode !=null){
            System.out.print(currNode.data+" -> ");
            currNode = currNode.next;
        }
        System.out.println("NULL");
    }
    //delete first
    public void deleteFirst(){
        if(head == null){
            System.out.println("This list is empty");
            return;
        }
        size--;
        head = head.next;
    }

    //delete last
    public void deleteLast(){
        if(head == null){
            System.out.println("The list is empty");
            return;
        }
        size--;
        if(head.next == null){
            head = null;
            return;
        }
        Node secondLast = head;
        Node lastNode = head.next;
        while(lastNode.next != null){
            secondLast = secondLast.next;
            lastNode = lastNode.next;
        }
        secondLast.next = null;
    }

    //get the size of list
    public int getSize(){
        return size;
    }

    public static void main(String[] args) {
        LinkedListReversal obj = new LinkedListReversal();
        obj.firstAdd("This");
        obj.firstAdd("is");
        obj.firstAdd("java");
        obj.lastAdd("Created by Arvind");
        obj.printList();


        obj.deleteFirst();
        obj.deleteLast();
        obj.printList();
        System.out.println(obj.getSize());

    }
}
