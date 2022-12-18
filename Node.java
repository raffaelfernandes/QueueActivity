package queue;

public class Node<Item> {
	private Item item;
	private Node<Item> next;
	
	public Node(Item item, Node<Item> next) {
		this.item = item;
		this.next = next;
	}
	
	public Node(Item item) {
		this.item = item;
		this.next = null;
	}
	
	public Node() {
		this.item = null;
		this.next = null;
	}

	public Item getItem() {
		return item;
	}
	
	public int getItemAge() {
		return((Person) item).getAge();
	}
	
	public String getItemName() {
		return((Person) item).getName();
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Node<Item> getNext() {
		return next;
	}

	public void setNext(Node<Item> next) {
		this.next = next;
	}
	
	
	
}
