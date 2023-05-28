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


  
 public void insert(E x) throws ItemDuplicated {
    this.height = false;
    this.root = insert(x, (NodeAVL) this.root);
}

protected NodeAVL insert(E x, NodeAVL node) throws ItemDuplicated {
    NodeAVL fat = node;
    if (node == null) { // Si el nodo es nulo, se crea un nuevo nodo con el elemento x
        this.height = true; // Se indica que la altura ha cambiado
        fat = new NodeAVL(x);
    } else {
        int resC = node.data.compareTo(x);
        if (resC == 0) { // Si el elemento x es igual al elemento del nodo actual, se lanza la excepción ItemDuplicated
            throw new ItemDuplicated(x + " ya se encuentra en el árbol...");
        }
        if (resC < 0) { // Si el elemento x es mayor que el elemento del nodo actual, se inserta en el subárbol derecho
            fat.right = insert(x, (NodeAVL) node.right);
            if (this.height) { // Si la altura ha cambiado
                switch (fat.bf) { // Se verifica el factor de equilibrio del nodo padre
                    case -1: // Si era -1, se actualiza a 0 y se indica que la altura no ha cambiado
                        fat.bf = 0;
                        this.height = false;
                        break;
                    case 0: // Si era 0, se actualiza a 1 y se indica que la altura ha cambiado
                        fat.bf = 1;
                        this.height = true;
                        break;
                    case 1: // Si era 1 (desbalance hacia la derecha), se realiza el balanceo hacia la izquierda
                        fat = balanceToLeft(fat);
                        this.height = false;
                        break;
                }
            }
        } else { // Si el elemento x es menor que el elemento del nodo actual, se inserta en el subárbol izquierdo
            fat.left = insert(x, (NodeAVL) node.left);
            if (this.height) { // Si la altura ha cambiado
                switch (fat.bf) { // Se verifica el factor de equilibrio del nodo padre
                    case 1: // Si era 1, se actualiza a 0 y se indica que la altura no ha cambiado
                        fat.bf = 0;
                        this.height = false;
                        break;
                    case 0: // Si era 0, se actualiza a -1 y se indica que la altura ha cambiado
                        fat.bf = -1;
                        this.height = true;
                        break;
                    case -1: // Si era -1 (desbalance hacia la izquierda), se realiza el balanceo hacia la derecha
                        fat = balanceToRight(fat);
                        this.height = false;
                        break;
                }
            }
        }
    }
    return fat; // Se devuelve el nodo padre actualizado
}

}
