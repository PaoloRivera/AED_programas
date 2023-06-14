/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab10;

/**
 *
 * @author Usuario
 */
public class TestArbolB {

    public static void main(String[] args) {
        //grado minimo del Arbol B es t=3
        //Cada nodo soporta 2t hijos y 2t-1 claves
        int t = 3;

        //Se crea el arbol B segun t
        ArbolB arbolB = new ArbolB(t);
        
        //Valores a ingresar
        int[] valoresUno = {20, 10, 50, 30, 40, 60, 80, 70, 90};
        System.out.println("-- INICIO --");
        System.out.println("INSERTANDO VALORES AL ARBOL B");
        for(int i=0; i<valoresUno.length; i++) {
            System.out.println("Insertando... valor " + valoresUno[i]);
            arbolB.insertar(valoresUno[i]);
        }
        
        //EJERCICIO 3
        arbolB.buscarNodoPorClave(80);
        
        //EJERCICIO 4
        System.out.println("\nEl valor máximo en el arbol B es: " + arbolB.findMax());

        //EJERCICIO 5
        System.out.println("\nEl valor mínimo es:");
        NodoArbolB minNode = arbolB.findMinNode();
        minNode.imprimir();
        
        System.out.println("\n");
        
        //Mostrando arbol B por pantalla en preorder
        System.out.println("ESTADO ARBOL B");
        arbolB.showBTree();
        System.out.println("");

        
        //Eliminar
        System.out.println("ELIMINANDO VALOR DEL ARBOL B");
        System.out.println("Eliminando... valor 90");
        arbolB.eliminar(90);

        //EJERCICIO 2
        System.out.println("\nBuscando el nodo con el valor 80 en el arbol B y rastreando el camino:");
        arbolB.buscarYRastrear(80);
        
        

        
        //Mostrando arbol B por pantalla en preorder
        System.out.println("ESTADO ARBOL B");
        arbolB.showBTree();
        System.out.println("");
       
        
        System.out.println("");
        System.out.println("-- FIN --");
    }
}

