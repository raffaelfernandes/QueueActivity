package queue;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {
	private Node<Item> first;
	private Node<Item> last;
	private Node<Item> aux;
	private int currentPriorityQuantity, currentNormalQuantity;
	private int currentSize, servedNormalQuantity, servedPriorityQuantity, totalServedQuantity;

	public void enqueue(Item item) {
		Node<Item> newNode = new Node<Item>(item);
		if (isEmpty()) {
			first = newNode;
			last = newNode;
		} else {
			last.setNext(newNode);
			last = newNode;
		}
		currentSize++;
		currentNormalQuantity++;
		
	}

	public void priorityEnqueue(Item item) {
		Node<Item> newNode = new Node<Item>(item);
		if (isEmpty()) {
			first = newNode;
			last = newNode;
			currentSize++;
			currentPriorityQuantity++;
			return;
		} else {
			Node<Item> tempAux = first;
			aux = first;
			while(aux!=null) {
				if((newNode.getItemAge()/10)>(aux.getItemAge()/10)) {
					if(aux == first) {
						newNode.setNext(first);
						first = newNode;
					}else {
						tempAux.setNext(newNode);
						newNode.setNext(aux);
					}
					currentSize++;
					currentPriorityQuantity++;
					return;
				}
				if(aux!=first) {
					tempAux = tempAux.getNext();
				}
				aux = aux.getNext();
			}
			enqueue(item);
			currentNormalQuantity--;
			currentPriorityQuantity++;;
		}
	}

	public Item dequeue() {
		if (isEmpty()) {
			return null; // ERRO!
		} else {
			Node<Item> node = first;
			first = first.getNext();
			if (isEmpty()) {
				last = null;
			}
			if (node.getItemAge() >= 60) {
				servedPriorityQuantity++;
				currentPriorityQuantity--;
			} else {
				servedNormalQuantity++;
				currentNormalQuantity--;
			}
			currentSize--;
			totalServedQuantity++;
			return node.getItem();
		}
	}

	public String showQueue() {
		if (!isEmpty()) {
			String message = "";
			int cont = 1;
			aux = first;
			for (int i = 0; i < currentSize; i++) {
				message += cont + ". " + aux.getItemName() + "\n";
				cont++;
				aux = aux.getNext();
			}
			return message;
		} else {
			return "A fila está vazia."; // Erro ao retornar lista, pois está vazia
		}
	}

	public String showStats() {
		if (totalServedQuantity != 0) {
			double priorityServedPercentage = ((double) servedPriorityQuantity / totalServedQuantity) * 100;
			double normalServedPercentage = ((double) servedNormalQuantity / totalServedQuantity) * 100;
			String message = String.format("%.2f", priorityServedPercentage)
					+ "% dos atendimentos foram prioritários.\n" + String.format("%.2f", normalServedPercentage)
					+ "% dos atendimentos foram não prioritários.\n" + "No momento, a fila possui " + currentSize
					+ " pessoa(s), sendo " + currentPriorityQuantity + " prioritária(s)" + " e " + currentNormalQuantity
					+ " não prioritária(s).\n";
			return message;
		} else {
			return "Nenhuma pessoa foi atentida ainda.\n";
		}
	}

	public boolean isEmpty() {
		if (first == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
