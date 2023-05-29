/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;

/**
 *
 * @author Alumno
 */
public class TestAVL {
    public static void main(String[] args) {
        // Caso de prueba 1: Eliminación de elementos en un árbol AVL
        System.out.println("Caso de prueba 1: Eliminación de elementos en un árbol AVL");

        AVLTree<Integer> avl = new AVLTree<>();

        // Insertar elementos
        avl.insert(10);
        avl.insert(5);
        avl.insert(15);
        avl.insert(3);
        avl.insert(8);
        avl.insert(12);
        avl.insert(18);

        // Mostrar árbol AVL inicial
        System.out.println("Árbol AVL inicial:");
        System.out.println(avl.toString());

        // Eliminar elementos
        avl.delete(5);
        avl.delete(12);
        avl.delete(18);

        // Mostrar árbol AVL después de la eliminación
        System.out.println("Árbol AVL después de la eliminación:");
        System.out.println(avl.toString());
    }
}






