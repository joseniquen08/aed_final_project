package pe.edu.utp.final_project.classes;

import pe.edu.utp.final_project.domain.dashboard.BuyItem;
import pe.edu.utp.final_project.domain.dashboard.statistics.Provider;

public class Tree<E> {
  private TreeNode<E> root;

  public Tree() {
    this.root = null;
  }

  public void insert(E value) {
    this.root = insertNode(this.root, value);
  }

  public TreeNode<E> getRoot() {
    return this.root;
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

  public int size() {
    return sizeRecursive(this.root);
  }

  public int sizeRecursive(TreeNode<E> currNode) {
    if (currNode == null) {
      return 0;
    } else {
      return 1 + sizeRecursive(currNode.getLeft()) + sizeRecursive(currNode.getRight());
    }
  }

  // ------------------------------------------------
  // specific methods for this project
  // ------------------------------------------------

  public void insertByType(E item, int type) {
    if (type == 1) {
      this.root = insertNodeByRucEntidad(this.root, item);
    } else {
      this.root = insertNodeByRucProveedor(this.root, item);
    }
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

  public TreeNode<E> insertNodeByRucEntidad(TreeNode<E> node, E item) {
    if (node == null) {
      return new TreeNode<>(item);
    } else {
      if (((BuyItem) item).getRucEntidad() < ((BuyItem) node.getValue()).getRucEntidad()) {
        node.setLeft(insertNodeByRucEntidad(node.getLeft(), item));
      } else {
        node.setRight(insertNodeByRucEntidad(node.getRight(), item));
      }
    }
    return node;
  }

  public BuyItem[] getItemsByType(int type, String value) {
    BuyItem[] items = new BuyItem[0];
    return getItemsByTypeRecursive(this.root, items, type, value);
  }

  public BuyItem[] getItemsByTypeRecursive(TreeNode<E> node, BuyItem[] items, int type, String value) {
    try {
      if (node != null) {
        items = getItemsByTypeRecursive(node.getLeft(), items, type, value);
        if (type == 1) {
          if (((BuyItem) node.getValue()).getRucProveedor() == Long.parseLong(value)) {
            items = addToArray(items, (BuyItem) node.getValue());
          }
        } else if (type == 3) {
          if (((BuyItem) node.getValue()).getRucEntidad() == Long.parseLong(value)) {
            items = addToArray(items, (BuyItem) node.getValue());
          }
        } else if (type == 10) {
          if (((BuyItem) node.getValue()).getFechaFormalizacion().equals(value)) {
            items = addToArray(items, (BuyItem) node.getValue());
          }
        } else if (type == 18) {
          if (((BuyItem) node.getValue()).getAcuerdoMarco().equals(value)) {
            items = addToArray(items, (BuyItem) node.getValue());
          }
        }
        items = getItemsByTypeRecursive(node.getRight(), items, type, value);
      }
      return items;
    } catch (NumberFormatException e) {
      return new BuyItem[0];
    }
  }

  public BuyItem[] addToArray(BuyItem[] items, BuyItem item) {
    BuyItem[] newItems = new BuyItem[items.length + 1];
    for (int i = 0; i < items.length; i++) {
      newItems[i] = items[i];
    }
    newItems[items.length] = item;
    return newItems;
  }

  // ================================================

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

  // ================================================

  public BuyItem[] getUniqueFrameworkAgreements() {
    BuyItem[] frameworkAgreements = new BuyItem[0];
    return getUniqueFrameworkAgreementsRecursive(this.root, frameworkAgreements);
  }

  public BuyItem[] getUniqueFrameworkAgreementsRecursive(TreeNode<E> node, BuyItem[] frameworkAgreements) {
    if (node != null) {
      frameworkAgreements = getUniqueFrameworkAgreementsRecursive(node.getLeft(), frameworkAgreements);
      if (!isFrameworkAgreementInArray(frameworkAgreements, ((BuyItem) node.getValue()).getAcuerdoMarco())) {
        frameworkAgreements = addFrameworkAgreementToArray(frameworkAgreements, (BuyItem) node.getValue());
      }
      frameworkAgreements = getUniqueFrameworkAgreementsRecursive(node.getRight(), frameworkAgreements);
    }
    return frameworkAgreements;
  }

  public boolean isFrameworkAgreementInArray(BuyItem[] frameworkAgreements, String frameworkAgreement) {
    for (BuyItem item : frameworkAgreements) {
      if (item.getAcuerdoMarco().equals(frameworkAgreement)) {
        return true;
      }
    }
    return false;
  }

  public BuyItem[] addFrameworkAgreementToArray(BuyItem[] frameworkAgreements, BuyItem frameworkAgreement) {
    BuyItem[] newFrameworkAgreements = new BuyItem[frameworkAgreements.length + 1];
    for (int i = 0; i < frameworkAgreements.length; i++) {
      newFrameworkAgreements[i] = frameworkAgreements[i];
    }
    newFrameworkAgreements[frameworkAgreements.length] = frameworkAgreement;
    return newFrameworkAgreements;
  }

  public Provider[] getUniqueProvidersByFrameworkAgreement(String frameworkAgreement) {
    Provider[] providers = new Provider[0];
    return getUniqueProvidersByFrameworkAgreementRecursive(this.root, providers, frameworkAgreement);
  }

  public Provider[] getUniqueProvidersByFrameworkAgreementRecursive(TreeNode<E> node, Provider[] providers,
      String frameworkAgreement) {
    if (node != null) {
      providers = getUniqueProvidersByFrameworkAgreementRecursive(node.getLeft(), providers, frameworkAgreement);
      if (((BuyItem) node.getValue()).getAcuerdoMarco().equals(frameworkAgreement)) {
        if (!isProviderInFAArray(providers, ((BuyItem) node.getValue()).getTotal(),
            ((BuyItem) node.getValue()).getProveedor(),
            ((BuyItem) node.getValue()).getEstadoOrdenElectronica())) {
          providers = addProviderToFAArray(providers, (BuyItem) node.getValue());
        }
      }
      providers = getUniqueProvidersByFrameworkAgreementRecursive(node.getRight(), providers, frameworkAgreement);
    }
    return providers;
  }

  public boolean isProviderInFAArray(Provider[] providers, Double total, String provider, String estado) {
    for (Provider item : providers) {
      if (item.getName().equals(provider)) {
        if (estado.equals("ACEPTADA")) {
          item.setQuantityAccepted(item.getQuantityAccepted() + total);
        } else if (estado.equals("PAGADA")) {
          item.setQuantityPaid(item.getQuantityPaid() + total);
        } else if (estado.equals("RESUELTA")) {
          item.setQuantitySuccess(item.getQuantitySuccess() + total);
        }
        return true;
      }
    }
    return false;
  }

  public Provider[] addProviderToFAArray(Provider[] providers, BuyItem provider) {
    Provider[] newProviders = new Provider[providers.length + 1];
    for (int i = 0; i < providers.length; i++) {
      newProviders[i] = providers[i];
    }
    if (provider.getEstadoOrdenElectronica().equals("ACEPTADA")) {
      newProviders[newProviders.length - 1] = new Provider(provider.getProveedor(), provider.getTotal(), 0, 0);
    } else if (provider.getEstadoOrdenElectronica().equals("PAGADA")) {
      newProviders[newProviders.length - 1] = new Provider(provider.getProveedor(), 0, provider.getTotal(), 0);
    } else if (provider.getEstadoOrdenElectronica().equals("RESUELTA")) {
      newProviders[newProviders.length - 1] = new Provider(provider.getProveedor(), 0, 0, provider.getTotal());
    }
    return newProviders;
  }

  // ================================================
}
