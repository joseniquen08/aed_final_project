package pe.edu.utp.final_project.classes;

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

  public void sort() {
    Node<E> currentNode = this.header;
    Node<E> nextNode;
    E auxObject;
    while (currentNode.getNext() != null) {
      nextNode = currentNode.getNext();
      while (nextNode != null) {
        if (Integer.parseInt((String) currentNode.getValue()) > Integer.parseInt((String) nextNode.getValue())) {
          auxObject = nextNode.getValue();
          nextNode.setValue(currentNode.getValue());
          currentNode.setValue(auxObject);
        }
        nextNode = nextNode.getNext();
      }
      currentNode = currentNode.getNext();
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
