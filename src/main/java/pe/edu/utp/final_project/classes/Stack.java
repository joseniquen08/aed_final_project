package pe.edu.utp.final_project.classes;

import java.util.NoSuchElementException;

public class Stack<E> {
  private Node<E> top;

  public void add(E value) {
    Node<E> newNode = new Node<E>(value);
    newNode.setNext(this.top);
    this.top = newNode;
  }

  public E remove() {
    if (isEmpty()) {
      throw new NoSuchElementException("La pila está vacía.");
    }
    E value = this.top.getValue();
    this.top = this.top.getNext();
    return value;
  }

  public E returnTop() {
    if (isEmpty()) {
      throw new NoSuchElementException("La pila está vacía.");
    }
    return this.top.getValue();
  }

  public boolean filter() {
    return true;
  }

  public void searchCont(E query) {
    if (isEmpty()) {
      throw new NoSuchElementException("La pila está vacía.");
    }
    Node<E> nodeRef = this.top;
    int cont = 0;
    while (nodeRef != null) {
      if (query == nodeRef.getValue()) {
        cont++;
      }
      nodeRef = nodeRef.getNext();
    }
    System.out.println("Encontrado " + cont + " " + (cont == 1 ? "vez." : "veces."));
  }

  public boolean isEmpty() {
    return this.top == null;
  }
}
