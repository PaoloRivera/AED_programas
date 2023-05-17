/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication7;

/**
 *
 * @author Alumno
 */

/*
public class BSTree<E extends Comparable<E>> {
    class Node{
        protected E data;
        protected Node left, right;
        
        public Node(E data){
            this (data,null,null);
        }
        public Node(E data, Node left, Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    
    private Node root;
    
    public BSTree(){ this.root = null; }
    public boolean isEmpty() { return this.root == null;}
    
    //Metodo que inserta un elemento al BST.
    //Si el elemento ya exite en el arbol levanta la excepción ItemDuplicated
    public void insert(E x) throws ItemDuplicated {
        //Escrbiir codigo aqui
    }
    
    //Metodo que busca un elemento y retorna su información
    //Si no existe se levanta la excepción ItemNoFound
    public E search(E x) throws ItemNotFound{
        //escriba el codigo aqui
    }
    
    //Metodo que elimina un elemento del BST
    //Si no existe se levanta la exepción ItemNotFound
    public void remove (E x) throws ItemNotFound{
        //escriba el codigo aqui
    }
    
    //Retorna la cadena que tiene toda la información del BST
    //Utiliza alguno de los recorridos
    public String toString(){
        //escriba su código
    }
    
    public void inOrden(){
        //escriba su codigo aqui
    }
    
    //Metodo recursivo que realiza el recorrido inOrden a partir de un nodo
    protected void inOrden(Node actual){
        //escriba su codigo aqui
    }
    
}
*/

public class BSTree<E extends Comparable<E>> {
    class Node {
        protected E data;
        protected Node left, right;

        public Node(E data) {
            this(data, null, null);
        }

        public Node(E data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;

    public BSTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    // Método que inserta un elemento al BST.
    // Si el elemento ya existe en el árbol, levanta la excepción ItemDuplicated
public void insert(E x) throws ItemDuplicated {
    root = insertRecursive(root, x);
}

// Método auxiliar recursivo para la inserción de un elemento en el BST
private Node insertRecursive(Node node, E x) throws ItemDuplicated {
    if (node == null) {
        return new Node(x);
    }

    int compareResult = x.compareTo(node.data);

    if (compareResult < 0) {
        node.left = insertRecursive(node.left, x);
    } else if (compareResult > 0) {
        node.right = insertRecursive(node.right, x);
    } else {
        throw new ItemDuplicated("El elemento ya existe en el BST.");
    }

    return node;
}


    // Método que busca un elemento y retorna su información
    // Si no existe, levanta la excepción ItemNotFound
    public E search(E x) throws ItemNotFound {
        Node result = searchRecursive(root, x);

        if (result == null) {
            throw new ItemNotFound("El elemento no se encuentra en el BST.");
        }

        return result.data;
    }

    // Método auxiliar recursivo para la búsqueda de un elemento en el BST
    private Node searchRecursive(Node node, E x) {
        if (node == null || x.compareTo(node.data) == 0) {
            return node;
        }

        if (x.compareTo(node.data) < 0) {
            return searchRecursive(node.left, x);
        }

        return searchRecursive(node.right, x);
    }

    // Método que elimina un elemento del BST
    // Si no existe, levanta la excepción ItemNotFound
    public void remove(E x) throws ItemNotFound {
        root = removeRecursive(root, x);
    }

    // Método auxiliar recursivo para la eliminación de un elemento en el BST
    private Node removeRecursive(Node node, E x) throws ItemNotFound {
        if (node == null) {
            throw new ItemNotFound("El elemento no se encuentra en el BST.");
        }

        int compareResult = x.compareTo(node.data);

        if (compareResult < 0) {
            node.left = removeRecursive(node.left, x);
        } else if (compareResult > 0) {
            node.right = removeRecursive(node.right, x);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node successor = findMin(node.right);
                node.data = successor.data;
                node.right = removeRecursive(node.right, successor.data);
            }
        }

        return node;
    }

    // Método auxiliar para encontrar el nodo con el valor mínimo en un subárbol
    private Node findMin(Node node) {
        if (node == null) {
            return null;
        }

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    // Retorna la cadena que contiene toda la información del BST en orden ascendente
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrden(root, sb);
        return sb.toString();
    }

    // Realiza el recorrido inOrden del BST y construye la cadena con la información de los nodos
    private void inOrden(Node node, StringBuilder sb) {
        if (node != null) {
            inOrden(node.left, sb);
            sb.append(node.data).append(" ");
            inOrden(node.right, sb);
        }
    }

    public void inOrden() {
        inOrden(root);
    }

    // Método auxiliar recursivo para el recorrido inOrden del BST
    private void inOrden(Node node) {
        if (node != null) {
            inOrden(node.left);
            System.out.print(node.data + " ");
            inOrden(node.right);
        }
    }
}




