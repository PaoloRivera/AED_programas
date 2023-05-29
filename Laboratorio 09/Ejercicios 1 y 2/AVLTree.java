/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication3;

/**
 *
 * @author Alumno
 * @param <E>
 */
public class AVLTree<E extends Comparable<E>> extends BSTree<E>
{

@Override
    public String toString() {
        return toString((NodeAVL) this.root, 0);
    }

    private String toString(NodeAVL node, int level) {
        String str = "";
        if (node != null) {
            str += " ".repeat(level * 2); // Espacios de indentación
            str += "Nodo: " + node.data + ", Factor de Equilibrio: " + node.bf + "\n";
            str += toString((NodeAVL) node.left, level + 1); // Subárbol izquierdo
            str += toString((NodeAVL) node.right, level + 1); // Subárbol derecho
        }
        return str;
    }

  class NodeAVL extends Node {
    protected int bf;  // balance factor

    public NodeAVL(E data) {
      super(data);
      this.bf = 0; // inicializamos el factor de equilibrio a 0
    }
  }
  
  
  private boolean height; 

  
  private NodeAVL balanceToLeft(NodeAVL node) {
    NodeAVL hijo = (NodeAVL) node.right;
    switch (hijo.bf) {
        case 1:
            node.bf = 0;
            hijo.bf = 0;
            node = rotateSL(node);
            break;
        case -1:
            NodeAVL nieto = (NodeAVL) hijo.left;
            switch (nieto.bf) {
                case -1:
                    node.bf = 0;
                    hijo.bf = 1;
                    break;
                case 0:
                    node.bf = 0;
                    hijo.bf = 0;
                    break;
                case 1:
                    node.bf = 1;
                    hijo.bf = 0;
                    break;
            }
            nieto.bf = 0;
            node.right = rotateSR(hijo);
            node = rotateSL(node);
    }
    return node;
}

private NodeAVL balanceToRight(NodeAVL node) {
    NodeAVL hijo = (NodeAVL) node.left;
    switch (hijo.bf) {
        case -1:
            node.bf = 0;
            hijo.bf = 0;
            node = rotateSR(node);
            break;
        case 1:
            NodeAVL nieto = (NodeAVL) hijo.right;
            switch (nieto.bf) {
                case 1:
                    node.bf = 0;
                    hijo.bf = -1;
                    break;
                case 0:
                    node.bf = 0;
                    hijo.bf = 0;
                    break;
                case -1:
                    node.bf = -1;
                    hijo.bf = 0;
                    break;
            }
            nieto.bf = 0;
            node.left = rotateSL(hijo);
            node = rotateSR(node);
    }
    return node;
}
  
private NodeAVL rotateSL(NodeAVL node) {
    NodeAVL p = (NodeAVL) node.right;
    node.right = p.left;
    p.left = node;
    node = p;
    return node;
}

private NodeAVL rotateSR(NodeAVL node) {
    NodeAVL p = (NodeAVL) node.left;
    node.left = p.right;
    p.right = node;
    node = p;
    return node;
}


  
@Override
public void insert(E key) {
    this.root = insert(key, (NodeAVL) this.root);
}

public NodeAVL insert(E key, NodeAVL node) {
    NodeAVL fat = node;
    if (node == null) {
        this.height = true;
        fat = new NodeAVL(key);
    } else {
        int comparison = key.compareTo(node.data);
        if (comparison == 0) {
            // El elemento es duplicado, no se lanza ninguna excepción
            return fat;
        } else if (comparison < 0) {
            fat.left = insert(key, (NodeAVL) node.left);
            if (this.height) {
                switch (fat.bf) {
                    case 1:
                        fat.bf = 0; // El subárbol izquierdo se ha hecho más alto, balanceando el factor de equilibrio
                        this.height = false; // La altura no ha cambiado
                        break;
                    case 0:
                        fat.bf = -1; // El subárbol derecho ahora es más alto, incrementando el factor de equilibrio
                        this.height = true; // La altura ha cambiado
                        break;
                    case -1:
                        fat = balanceToRight(fat); // Desbalance hacia la izquierda, se realiza rotación para balancear
                        this.height = false; // La altura no ha cambiado
                        break;
                }
            }
        } else {
            fat.right = insert(key, (NodeAVL) node.right);
            if (this.height) {
                switch (fat.bf) {
                    case -1:
                        fat.bf = 0; // El subárbol derecho se ha hecho más alto, balanceando el factor de equilibrio
                        this.height = false; // La altura no ha cambiado
                        break;
                    case 0:
                        fat.bf = 1; // El subárbol izquierdo ahora es más alto, incrementando el factor de equilibrio
                        this.height = true; // La altura ha cambiado
                        break;
                    case 1:
                        fat = balanceToLeft(fat); // Desbalance hacia la derecha, se realiza rotación para balancear
                        this.height = false; // La altura no ha cambiado
                        break;
                }
            }
        }
    }
    return fat; // Se devuelve el nodo padre actualizado
}

@Override
public void delete(E key) {
    root = delete(key, (NodeAVL) root);
}

private NodeAVL delete(E key, NodeAVL node) {
    if (node == null) {
        return null; // El elemento no se encontró en el árbol
    }

    int comparison = key.compareTo(node.data);
    if (comparison < 0) {
        node.left = delete(key, (NodeAVL) node.left); // Buscar en el subárbol izquierdo
        if (height) {
            node = balanceToRight(node); // Realizar balanceo si es necesario
        }
    } else if (comparison > 0) {
        node.right = delete(key, (NodeAVL) node.right); // Buscar en el subárbol derecho
        if (height) {
            node = balanceToLeft(node); // Realizar balanceo si es necesario
        }
    } else {
        // Se encontró el elemento a eliminar
        if (node.left == null && node.right == null) {
            // Caso 1: El nodo es una hoja, simplemente se elimina
            node = null;
            height = true;
        } else if (node.left == null) {
            // Caso 2: El nodo tiene solo un hijo derecho
            node = (NodeAVL) node.right;
            height = true;
        } else if (node.right == null) {
            // Caso 3: El nodo tiene solo un hijo izquierdo
            node = (NodeAVL) node.left;
            height = true;
        } else {
            // Caso 4: El nodo tiene dos hijos, se busca el sucesor inorden
            NodeAVL successor = findMin((NodeAVL) node.right);
            node.data = successor.data; // Se reemplaza el valor del nodo por el sucesor inorden
            node.right = delete(successor.data, (NodeAVL) node.right);
            if (height) {
                node = balanceToLeft(node); // Realizar balanceo si es necesario
            }
        }
    }

    return node;
}

private NodeAVL findMin(NodeAVL node) {
    if (node.left == null) {
        return node; // El nodo actual es el mínimo
    }
    return findMin((NodeAVL) node.left); // Llamar recursivamente al subárbol izquierdo
}


@Override
    public boolean search(E key) {
        return search(key, (NodeAVL) root);
    }

    private boolean search(E key, NodeAVL node) {
        if (node == null) {
            return false; // El elemento no se encontró en el árbol
        }
        int comparison = key.compareTo(node.data);
        if (comparison == 0) {
            return true; // El elemento se encontró en el nodo actual
        } else if (comparison < 0) {
            return search(key, (NodeAVL) node.left); // Buscar en el subárbol izquierdo
        } else {
            return search(key, (NodeAVL) node.right); // Buscar en el subárbol derecho
        }
    }

@Override
    public int height() {
        return height((NodeAVL) root);
    }

    private int height(NodeAVL node) {
        if (node == null) {
            return -1; // Árbol vacío, altura es -1
        }
        int leftHeight = height((NodeAVL) node.left);
        int rightHeight = height((NodeAVL) node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }


}
