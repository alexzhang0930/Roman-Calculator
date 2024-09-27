public class LinkedListStack<Item> {
    private class Node<Item> {
        Item item;
        Node<Item> next;
    }

    private Node<Item> head;

    public LinkedListStack() {
        head = null;
    }
    public void push(Item data) {
        Node<Item> temp = new Node<>();
        temp.item = data;
        temp.next = head;
        head = temp;
    }

    public Item pop() {
        Item temp = head.item;
        head = head.next;
        return temp;
    }
}
