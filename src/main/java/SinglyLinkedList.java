public class SinglyLinkedList<T> {

    // Inner Node class.
    public class Node<T> {
        // Properties of the Node class.
        // The two properties should be:
        // 1. data (the data stored in the node).
        // 2. next (a reference (also known as a pointer) to the next node.

        private T data;
        private Node next;

        // Constructor of the Node class.
        // The constructor should set the data property of the Node to be the value passed as a parameter.
        // The constructor should set the next property of the Node to be null.
        public Node (T data){
            this.data = data;
            this.next = null;
        }
    }

    // Properties of the Singly Linked List class.
    // The three properties should be:
    // 1. size (records the number of nodes in our Singly Linked List)
    // 2. head (a reference to the first (also known as the head) node in our Singly Linked List).
    // 3. tail (a reference to the last (also known as the tail) node in our Singly Linked List.

    private int size;
    private Node head;
    private Node tail;
    // Constructor.
    // Creates a Singly Linked List with a head node.
    public SinglyLinkedList(T value) {
        this.head = new Node(value);
        this.tail = head;
        this.size = 1;
    }

    // Methods

    // size
    // returns the size of the Singly Linked List.
    public int size() {
        return this.size;
    }

    // isEmpty
    // returns whether the Singly Linked List is empty.
    public boolean isEmpty() {
        return size <= 0;
    }
    // peekFirst
    // returns the data stored in the head node.
    // throws a run time exception if the Singly Linked List is empty.
    public T peekFirst() {
        if(isEmpty()){
            throw new RuntimeException("the Singly Linked List is empty.");
        }
        return (T) head.data;
    }

    // peekLast
    // returns the data stored in the tail node.
    // throws a run time exception if the Singly Linked List is empty.
    public T peekLast() {
        if(isEmpty()){
            throw new RuntimeException("the Singly Linked List is empty.");
        }
        return (T) tail.data;
    }

    // addFirst
    // Adds a node to the front of the Singly Linked List.
    // If the Singly Linked List is empty,
    public void addFirst(T value) {
        Node n = new Node(value);
        if(size == 0 || head==null){
            this.head = n;
            this.tail=n;
        }else{
            n.next = this.head;
            this.head = n;
        }
        this.size++;
    }

    // addLast
    // Adds a node to the back of the Singly Linked List.
    public void addLast(T value) {
        Node n = new Node(value);
        tail.next = n;
        tail = n;
        this.size++;
    }

    // insertAt
    // Inserts a node at a specific index.
    // If the index is equal to 0, then we can invoke the addFirst method.
    // If the index is equal to size, then we can invoke the addLast method.
    // throws an illegal argument exception if the index is invalid.
    public void insert(T value, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Invalid index.");
        }
        if (index == 0) {
            addFirst(value);
            return;
        }
        if (index == size) {
            addLast(value);
            return;
        }
        Node<T> n = new Node<>(value);
        Node<T> trav = head;
        for (int i = 0; i < index - 1; i++) {
            trav = trav.next;
        }
        n.next = trav.next;
        trav.next = n;
        size++;
    }


    // removeFirst
    // Removes the first (also known as the head node) from the Singly Linked List.
    // Throws a run time exception if the Singly Linked List is empty.
    // Returns the data stored in the head node.
    // If the size of the Singly Linked List becomes 0, need to set the tail to null.
    public T removeFirst() {
        if(isEmpty()) throw new RuntimeException("empty");
        if (head == null) return null;
        head = head.next;
        if(head==null) {
            size--;
            return null;
        }
        size--;
        return (T) head.data;
    }

    // removeLast
    // Removes the last (also known as the tail node) from the Singly Linked List.
    // Throws a run time exception if the Singly Linked List is empty.
    // Returns the data stored in the tail node.
    // If the size of the Singly Linked List becomes 0, need to set the tail to null.
    public T removeLast() {
        T removed = null;
        if(isEmpty()){
            throw new RuntimeException();
        }else{
            if(size==1){
                removeFirst();
            }else{
                removed = (T) tail.data;
                Node trav = head;
                while(trav.next!=tail){
                    trav = trav.next;
                }
                tail = trav;
                trav.next = null;
                size--;
            }
        }
        return removed;

    }

    // removeAt
    // Removes a node at a specific index.
    // Returns the data stored in the removed node.
    // If the index is equal to 0, then we can invoke the removeFirst method.
    // If the index is equal to size-1, then we can invoke the removeLast method.
    // throws an illegal argument exception if the index is invalid.

    public T removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid index.");
        }

        if (index == 0) {
            return removeFirst();
        }

        if (index == size - 1) {
            return removeLast();
        }

        Node<T> cur = head;
        Node<T> prev = null;
        int i = 0;

        while (i < index) {
            prev = cur;
            cur = cur.next;
            i++;
        }
        T data = cur.data;
        prev.next = cur.next;
        size--;
        return data;
    }





    // contains
    // Determines whether the Singly Linked List contains a node that holds data equivalent to the value passed.
    // Returns a boolean.
    public boolean contains(T value) {
        Node<T> trav = head;
        if (trav.data.equals(value)) {
            return true;
        }
        while (trav.next != null) {
            trav = trav.next;
            if (trav.data.equals(value)) {
                return true;
            }
        }
        return false;
    }

    // valueAt
    // Returns the data held in the node at a specified index.
    // Throws an illegal argument exception if the index is invalid.
    public T valueAt(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid index.");
        }
        int i = 0;
        Node trav = this.head;
        while (i != index) {
            i++;
            trav = trav.next;
        }
        return (T) trav.data;
    }

    // reverse
    // Reverses the Singly Linked List.
    public void reverse() {
        if (head == null) {
            return;
        }
        Node<T> prev = null;
        Node<T> cur = head;
        while (cur != null) {
            Node<T> next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        head = prev;
    }

    // toString
    // Returns a String representation of the Singly Linked List. like  "0 -> 0 -> 7 -> null"
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> trav = head;
        while (trav != null) {
            sb.append(trav.data);
            if (trav.next != null) {
                sb.append(" -> ");
            }
            trav = trav.next;
        }
        sb.append(" -> null");
        return sb.toString();
    }

}
