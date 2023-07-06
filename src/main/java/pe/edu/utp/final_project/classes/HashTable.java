package pe.edu.utp.final_project.classes;

public class HashTable<K, V> {
  private int size;
  private HashNode<K, V>[] table;

  public HashTable(int size) {
    this.size = size;
    this.table = new HashNode[size];
  }

  public int getSize() {
    return size;
  }

  private int hash(K key) {
    return Math.abs(key.hashCode() % size);
  }

  public void put(K key, V value) {
    int index = hash(key);
    HashNode<K, V> newNode = new HashNode<>(key, value);
    if (this.table[index] == null) {
      this.table[index] = newNode;
    } else {
      HashNode<K, V> currentNode = this.table[index];
      while (currentNode.getNext() != null) {
        currentNode = currentNode.getNext();
      }
      currentNode.setNext(newNode);
    }
  }

  public V get(K key) {
    int index = hash(key);
    HashNode<K, V> currentNode = this.table[index];
    while (currentNode != null) {
      if (currentNode.getKey().equals(key)) {
        return currentNode.getValue();
      }
      currentNode = currentNode.getNext();
    }
    return null;
  }

  public void remove(K key) {
    int index = hash(key);
    HashNode<K, V> currentNode = this.table[index];
    HashNode<K, V> previousNode = null;
    while (currentNode != null) {
      if (currentNode.getKey().equals(key)) {
        if (previousNode == null) {
          this.table[index] = currentNode.getNext();
        } else {
          previousNode.setNext(currentNode.getNext());
        }
        return;
      }
      previousNode = currentNode;
      currentNode = currentNode.getNext();
    }
  }
}
