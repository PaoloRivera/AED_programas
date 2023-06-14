/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab10;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ArbolB {
    NodoArbolB root;
    int t;

    //Constructor
    public ArbolB(int t) {
        this.t = t;
        root = new NodoArbolB(t);
    }

    //EJERCICIO 3
    //Busca el valor ingresado y muestra el contenido del nodo que contiene el valor
    public void buscarNodoPorClave(int num) {
        NodoArbolB temp = search(root, num);
        
        if (temp == null) {
            System.out.println("No se ha encontrado un nodo con el valor ingresado");
        } else {
            System.out.print("Buscando el nodo con el valor " + num + " en el arbol B:\n[");
            for (int i = 0; i < temp.n; i++) {
                if (i != 0) {
                    System.out.print(" | ");
                }
                System.out.print(temp.key[i]);
            }
            System.out.println("]");
        }
    }

    //Search
    private NodoArbolB search(NodoArbolB actual, int key) {
        int i = 0;//se empieza a buscar siempre en la primera posicion

        //Incrementa el indice mientras el valor de la clave del nodo sea menor
        while (i < actual.n && key > actual.key[i]) {
            i++;
        }

        //Si la clave es igual, entonces retornamos el nodo
        if (i < actual.n && key == actual.key[i]) {
            return actual;
        }

        //Si llegamos hasta aqui, entonces hay que buscar los hijos
        //Se revisa primero si tiene hijos
        if (actual.leaf) {
            return null;
        } else {
            //Si tiene hijos, hace una llamada recursiva
            return search(actual.child[i], key);
        }
    }

    public void insertar(int key) {
        NodoArbolB r = root;

        //Si el nodo esta lleno lo debe separar antes de insertar
        if (r.n == ((2 * t) - 1)) {
            NodoArbolB s = new NodoArbolB(t);
            root = s;
            s.leaf = false;
            s.n = 0;
            s.child[0] = r;
            split(s, 0, r);
            nonFullInsert(s, key);
        } else {
            nonFullInsert(r, key);
        }
    }
    // Caso cuando la raiz se divide
    // x =          | | | | | |
    //             /
    //      |10|20|30|40|50|
    // i = 0
    // y = |10|20|30|40|50|
    private void split(NodoArbolB x, int i, NodoArbolB y) {
        //Nodo temporal que sera el hijo i + 1 de x
        NodoArbolB z = new NodoArbolB(t);
        z.leaf = y.leaf;
        z.n = (t - 1);

        //Copia las ultimas (t - 1) claves del nodo y al inicio del nodo z      // z = |40|50| | | |
        for (int j = 0; j < (t - 1); j++) {
            z.key[j] = y.key[(j + t)];
        }

        //Si no es hoja hay que reasignar los nodos hijos
        if (!y.leaf) {
            for (int k = 0; k < t; k++) {
                z.child[k] = y.child[(k + t)];
            }
        }

        //nuevo tamanio de y                                                    // x =            | | | | | |
        y.n = (t - 1);                                                          //               /   \
                                                                                //  |10|20| | | |
        //Mueve los hijos de x para darle espacio a z
        for (int j = x.n; j > i; j--) {
            x.child[(j + 1)] = x.child[j];
        }
        //Reasigna el hijo (i+1) de x                                           // x =            | | | | | |
        x.child[(i + 1)] = z;                                                   //               /   \
                                                                                //  |10|20| | | |     |40|50| | | |
        //Mueve las claves de x
        for (int j = x.n; j > i; j--) {
            x.key[(j + 1)] = x.key[j];
        }

        //Agrega la clave situada en la mediana                                 // x =            |30| | | | |
        x.key[i] = y.key[(t - 1)];                                              //               /    \
        x.n++;                                                                  //  |10|20| | | |      |40|50| | | |
    }
    private void nonFullInsert(NodoArbolB x, int key) {
        //Si es una hoja
        if (x.leaf) {
            int i = x.n; //cantidad de valores del nodo
            //busca la posicion i donde asignar el valor
            while (i >= 1 && key < x.key[i - 1]) {
                x.key[i] = x.key[i - 1];//Desplaza los valores mayores a key
                i--;
            }

            x.key[i] = key;//asigna el valor al nodo
            x.n++; //aumenta la cantidad de elementos del nodo
        } else {
            int j = 0;
            //Busca la posicion del hijo
            while (j < x.n && key > x.key[j]) {
                j++;
            }

            //Si el nodo hijo esta lleno lo separa
            if (x.child[j].n == (2 * t - 1)) {
                split(x, j, x.child[j]);

                if (key > x.key[j]) {
                    j++;
                }
            }

            nonFullInsert(x.child[j], key);
        }
    }

    public void showBTree() {
        print(root);
    }

    //Print en preorder
    private void print(NodoArbolB n) {
        n.imprimir();

        //Si no es hoja
        if (!n.leaf) {
            //recorre los nodos para saber si tiene hijos
            for (int j = 0; j <= n.n; j++) {
                if (n.child[j] != null) {
                    System.out.println();
                    print(n.child[j]);
                }
            }
        }
    }
    
    //EJERCICIO 1
    // Método para eliminar un nodo
public void eliminar(int key) {
    NodoArbolB nodo = search(root, key);
    if (nodo != null) {
        eliminar(root, nodo, key);
    }
}

// Elimina la clave en el nodo dado
private void eliminar(NodoArbolB nodoPadre, NodoArbolB nodo, int key) {
    // Asegúrate de que el nodo es una hoja
    if (!nodo.leaf) {
        System.out.println("La eliminación de nodos no hoja no está soportada");
        return;
    }

    int i = 0;
    while (i < nodo.n && key > nodo.key[i]) {
        i++;
    }

    // Desplaza las claves y los hijos a la izquierda
    for (int j = i; j < nodo.n - 1; j++) {
        nodo.key[j] = nodo.key[j + 1];
    }
    nodo.key[nodo.n - 1] = 0; // set last key to 0

    nodo.n--;

    // Si el nodo está vacío y no es la raíz, entonces elimina el nodo
    if (nodo.n == 0 && nodoPadre != nodo) {
        int hijoIndice = 0;
        for (int j = 0; j < nodoPadre.n + 1; j++) {
            if (nodoPadre.child[j] == nodo) {
                hijoIndice = j;
                break;
            }
        }
        for (int j = hijoIndice; j < nodoPadre.n; j++) {
            nodoPadre.child[j] = nodoPadre.child[j + 1];
        }
        nodoPadre.child[nodoPadre.n] = null;
        nodoPadre.n--;
    }
}

//EJERCICIO 2
public void buscarYRastrear(int num) {
        List<NodoArbolB> path = new ArrayList<>();
        NodoArbolB temp = searchAndTrack(root, num, path);

        if (temp == null) {
            System.out.println("No se ha encontrado un nodo con el valor ingresado");
        } else {
            System.out.println("Camino recorrido:");
            for (NodoArbolB node : path) {
                node.imprimir();
                System.out.println();
            }
        }
    }

    private NodoArbolB searchAndTrack(NodoArbolB actual, int key, List<NodoArbolB> path) {
        path.add(actual); 

        int i = 0;
        while (i < actual.n && key > actual.key[i]) {
            i++;
        }

        if (i < actual.n && key == actual.key[i]) {
            return actual;
        }

        if (actual.leaf) {
            return null;
        } else {
            return searchAndTrack(actual.child[i], key, path);
        }
    }
    
    
    //EJERCICIO 4
     public int findMax() {
        return findMax(root);
    }

    private int findMax(NodoArbolB node) {
        // Recorre el árbol hasta llegar a la hoja más a la derecha
        while (!node.leaf) {
            node = node.child[node.n];
        }
        // Devuelve el último valor del nodo hoja
        return node.key[node.n - 1];
    }  
    
    
    //EJERCICIO 5
        public NodoArbolB findMinNode() {
        return findMinNode(root);
    }

    private NodoArbolB findMinNode(NodoArbolB node) {
        // Recorre el árbol hasta llegar a la hoja más a la izquierda
        while (!node.leaf) {
            node = node.child[0];
        }
        // Devuelve el nodo hoja
        return node;
    }
    
}
