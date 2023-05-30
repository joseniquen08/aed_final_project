package pe.edu.utp.final_project.classes;

import java.util.NoSuchElementException;

public class Queue<E> {
  private Node<E> first;
  private Node<E> last;

  public void add(E value) {
    Node<E> newNode = new Node<E>(value);
    if (isEmpty()) {
      this.first = newNode;
      this.last = newNode;
    } else {
      this.last.setNext(newNode);
      this.last = newNode;
    }
  }

  public E remove() {
    if (isEmpty()) {
      throw new NoSuchElementException("La cola está vacía.");
    }
    E value = this.first.getValue();
    this.first = this.first.getNext();
    if (this.first == null) {
      this.last = null;
    }
    return value;
  }

  public E returnFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException("La cola está vacía.");
    }
    return this.first.getValue();
  }

  public boolean isEmpty() {
    return first == null;
  }
}
