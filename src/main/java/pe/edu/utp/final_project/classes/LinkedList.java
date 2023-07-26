package pe.edu.utp.final_project.classes;

import pe.edu.utp.final_project.domain.dashboard.BuyItem;

public class LinkedList<E> {
  public Node<E> header;

  public LinkedList() {
    this.header = null;
  }

  private Node<E> getLastNode() {
    Node<E> lastNode = this.header;
    while (lastNode.getNext() != null) {
      lastNode = lastNode.getNext();
    }
    return lastNode;
  }

  public void addToStart(E value) {
    Node<E> newNode = new Node<E>(value);
    if (this.header != null) {
      newNode.setNext(this.header);
    }
    this.header = newNode;
  }

  public void addToEnd(E value) {
    Node<E> newNode = new Node<E>(value);
    if (this.header == null) {
      this.header = newNode;
    } else {
      Node<E> lastNode = getLastNode();
      lastNode.setNext(newNode);
    }
  }

  public void remove(E value) {
    if (this.header == null) {
      return;
    }
    if (this.header.getValue().equals(value)) {
      this.header = this.header.getNext();
      return;
    }
    Node<E> previousNode = this.header;
    Node<E> currentNode = previousNode.getNext();
    while (currentNode != null) {
      if (currentNode.getValue().equals(value)) {
        previousNode.setNext(currentNode.getNext());
        return;
      }
      previousNode = currentNode;
      currentNode = previousNode.getNext();
    }
  }

  public void search(E value) {
    Node<E> currentNode = this.header;
    int cont = 0;
    while (currentNode != null) {
      if (currentNode.getValue().equals(value)) {
        cont++;
      }
      currentNode = currentNode.getNext();
    }
    if (cont == 0) {
      System.out.println("Elemento no encontrado.");
    } else {
      System.out.println("Elemento encontrado.");
    }
  }

  public Node<E> getNodeAt(int index) {
    if (index < 0 || index >= this.size()) {
      return null;
    }
    Node<E> currentNode = this.header;
    int count = 0;
    while (count < index) {
      currentNode = currentNode.getNext();
      count++;
    }
    return currentNode;
  }

  public void sortByRUCProveedorAndRUCEntidad() {
    int size = this.size();
    int gap = size / 2;

    while (gap > 0) {
      for (int i = gap; i < size; i++) {
        Node<E> currentNode = this.getNodeAt(i);
        int j = i;

        while (j >= gap
            && ((BuyItem) this.getNodeAt(j - gap).getValue()).getRucProveedor() > ((BuyItem) currentNode.getValue())
                .getRucProveedor()) {
          this.getNodeAt(j).setValue(this.getNodeAt(j - gap).getValue());
          j -= gap;
        }
      }
      gap /= 2;
    }
  }

  public int size() {
    int cont = 0;
    if (this.header == null) {
      return 0;
    } else {
      Node<E> currentNode = this.header;
      while (currentNode != null) {
        cont++;
        currentNode = currentNode.getNext();
      }
      return cont;
    }
  }

  public void print() {
    Node<E> currentNode = this.header;
    while (currentNode != null) {
      System.out.print(currentNode.getValue() + " => ");
      currentNode = currentNode.getNext();
    }
    System.out.println();
  }
}
