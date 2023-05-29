/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;

/**
 *
 * @author Alumno
 * @param <E>
 */
public class BSTree<E extends Comparable<E>> {
    protected class Node {
        E data;
        Node left;
        Node right;

        public Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    protected Node root;

    public BSTree() {
        this.root = null;
    }

    // Métodos de inserción, eliminación, búsqueda, etc. aquí...
    
    
public void insert(E key) {
    root = insert(key, root);
}

    private Node insert(E key, Node node) {
        if (node == null) {
            return new Node(key);
        }
        int comparison = key.compareTo(node.data);
        if (comparison < 0) {
            node.left = insert(key, node.left);
        } else if (comparison > 0) {
            node.right = insert(key, node.right);
        }
        return node;
    }
    
    

 public void delete(E key) {
    root = delete(key, root);
}

private Node delete(E key, Node node) {
    if (node == null) {
        return null; // El elemento no se encontró en el árbol
    }

    int comparison = key.compareTo(node.data);
    if (comparison < 0) {
        node.left = delete(key, node.left); // Buscar en el subárbol izquierdo
    } else if (comparison > 0) {
        node.right = delete(key, node.right); // Buscar en el subárbol derecho
    } else {
        // Se encontró el elemento a eliminar
        if (node.left == null && node.right == null) {
            // Caso 1: El nodo es una hoja, simplemente se elimina
            node = null;
        } else if (node.left == null) {
            // Caso 2: El nodo tiene solo un hijo derecho
            node = node.right;
        } else if (node.right == null) {
            // Caso 3: El nodo tiene solo un hijo izquierdo
            node = node.left;
        } else {
            // Caso 4: El nodo tiene dos hijos, se busca el sucesor inorden
            Node successor = findMin(node.right);
            node.data = successor.data; // Se reemplaza el valor del nodo por el sucesor inorden
            node.right = delete(successor.data, node.right); // Se elimina el sucesor inorden del subárbol derecho
        }
    }

    return node;
}

private Node findMin(Node node) {
    if (node.left == null) {
        return node; // El nodo actual es el mínimo
    }
    return findMin(node.left); // Seguir buscando en el subárbol izquierdo
}
   


    
        public boolean search(E key) {
        return search(key, root);
    }

    private boolean search(E key, Node node) {
        if (node == null) {
            return false;
        }
        int comparison = key.compareTo(node.data);
        if (comparison == 0) {
            return true;
        } else if (comparison < 0) {
            return search(key, node.left);
        } else {
            return search(key, node.right);
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return -1; // Árbol vacío, altura es -1
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    @Override
       public String toString() {
        return toString(root);
    }

    private String toString(Node node) {
        if (node == null) {
            return "";
        }
        String str = "";
        str += toString(node.left); // Recorrer subárbol izquierdo
        str += node.data + " "; // Agregar el elemento actual
        str += toString(node.right); // Recorrer subárbol derecho
        return str;
    }
}
    
   




