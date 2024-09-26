public class LinkedListStack<Item> {
    private class Node<Item> {
        Item item;
        Node<Item> next;
    }

    private Node<Item> head;
    private int size;

    public LinkedListStack () {
        head = null;
        size = 0;
    }
    public void push(Item data) {
        Node<Item> temp = new Node<>();
        temp.item = data;
        temp.next = head;
        head = temp;
        size++;
    }

    public Item pop() {
        Item temp = head.item;
        head.next = head;
        size--;
        return temp;
    }

    public Item peak() {
        return (Item) head.item;
    }
}
