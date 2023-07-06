package pe.edu.utp.final_project.classes;

import pe.edu.utp.final_project.domain.dashboard.BuyItem;
import pe.edu.utp.final_project.domain.dashboard.SearchItem;
import pe.edu.utp.final_project.domain.dashboard.statistics.Provider;

public class Tree<E> {
  TreeNode<E> root;

  public Tree() {
    this.root = null;
  }

  public void insert(E value) {
    this.root = insertNode(this.root, value);
  }

  public TreeNode<E> getRoot() {
    return root;
  }

  public TreeNode<E> insertNode(TreeNode<E> node, E value) {
    if (node == null) {
      return new TreeNode<E>(value);
    } else {
      if (Integer.parseInt((String) value) < Integer.parseInt((String) node.getValue())) {
        node.setLeft(insertNode(node.getLeft(), value));
      } else {
        node.setRight(insertNode(node.getRight(), value));
      }
    }
    return node;
  }

  public void printInOrder() {
    printInOrderRecursive(this.root);
  }

  public void printInOrderRecursive(TreeNode<E> node) {
    if (node != null) {
      printInOrderRecursive(node.getLeft());
      System.out.println(((SearchItem) node.getValue()).getRucProveedor() + " | ");
      printInOrderRecursive(node.getRight());
    }
  }

  // specific methods for this project

  public void insertByRucProveedor(E item) {
    this.root = insertNodeByRucProveedor(this.root, item);
  }

  public TreeNode<E> insertNodeByRucProveedor(TreeNode<E> node, E item) {
    if (node == null) {
      return new TreeNode<>(item);
    } else {
      if (((BuyItem) item).getRucProveedor() < ((BuyItem) node.getValue()).getRucProveedor()) {
        node.setLeft(insertNodeByRucProveedor(node.getLeft(), item));
      } else {
        node.setRight(insertNodeByRucProveedor(node.getRight(), item));
      }
    }
    return node;
  }

  public TreeNode<E> searchUniqueProvidersByEntity(TreeNode<E> node, String entity) {
    if (node != null) {
      node.setLeft(searchUniqueProvidersByEntity(node.getLeft(), entity));
      if (((BuyItem) node.getValue()).getEntidad().equals(entity)) {
        return node;
      }
      node.setRight(searchUniqueProvidersByEntity(node.getRight(), entity));
    }
    return null;
  }

  public BuyItem[] getUniqueEntities() {
    BuyItem[] entities = new BuyItem[0];
    return getUniqueEntitiesRecursive(this.root, entities);
  }

  public BuyItem[] getUniqueEntitiesRecursive(TreeNode<E> node, BuyItem[] entities) {
    if (node != null) {
      entities = getUniqueEntitiesRecursive(node.getLeft(), entities);
      if (!isEntityInArray(entities, ((BuyItem) node.getValue()).getEntidad())) {
        entities = addEntityToArray(entities, (BuyItem) node.getValue());
      }
      entities = getUniqueEntitiesRecursive(node.getRight(), entities);
    }
    return entities;
  }

  public boolean isEntityInArray(BuyItem[] entities, String entity) {
    for (BuyItem item : entities) {
      if (item.getEntidad().equals(entity)) {
        return true;
      }
    }
    return false;
  }

  public BuyItem[] addEntityToArray(BuyItem[] entities, BuyItem entity) {
    BuyItem[] newEntities = new BuyItem[entities.length + 1];
    for (int i = 0; i < entities.length; i++) {
      newEntities[i] = entities[i];
    }
    newEntities[entities.length] = entity;
    return newEntities;
  }

  public Provider[] getUniqueProvidersByEntity(String entity) {
    Provider[] providers = new Provider[0];
    return getUniqueProvidersByEntityRecursive(this.root, providers, entity);
  }

  public Provider[] getUniqueProvidersByEntityRecursive(TreeNode<E> node, Provider[] providers, String entity) {
    if (node != null) {
      providers = getUniqueProvidersByEntityRecursive(node.getLeft(), providers, entity);
      if (((BuyItem) node.getValue()).getEntidad().equals(entity)) {
        if (!isProviderInArray(providers, ((BuyItem) node.getValue()).getProveedor(),
            ((BuyItem) node.getValue()).getEstadoOrdenElectronica())) {
          providers = addProviderToArray(providers, (BuyItem) node.getValue());
        }
      }
      providers = getUniqueProvidersByEntityRecursive(node.getRight(), providers, entity);
    }
    return providers;
  }

  public boolean isProviderInArray(Provider[] providers, String provider, String estado) {
    for (Provider item : providers) {
      if (item.getName().equals(provider)) {
        if (estado.equals("ACEPTADA")) {
          item.setQuantityAccepted(item.getQuantityAccepted() + 1);
        } else if (estado.equals("PAGADA")) {
          item.setQuantityPaid(item.getQuantityPaid() + 1);
        } else if (estado.equals("RESUELTA")) {
          item.setQuantitySuccess(item.getQuantitySuccess() + 1);
        }
        return true;
      }
    }
    return false;
  }

  public Provider[] addProviderToArray(Provider[] providers, BuyItem provider) {
    Provider[] newProviders = new Provider[providers.length + 1];
    for (int i = 0; i < providers.length; i++) {
      newProviders[i] = providers[i];
    }
    if (provider.getEstadoOrdenElectronica().equals("ACEPTADA")) {
      newProviders[newProviders.length - 1] = new Provider(provider.getProveedor(), 1, 0, 0);
    } else if (provider.getEstadoOrdenElectronica().equals("PAGADA")) {
      newProviders[newProviders.length - 1] = new Provider(provider.getProveedor(), 0, 1, 0);
    } else if (provider.getEstadoOrdenElectronica().equals("RESUELTA")) {
      newProviders[newProviders.length - 1] = new Provider(provider.getProveedor(), 0, 0, 1);
    }
    return newProviders;
  }
}
