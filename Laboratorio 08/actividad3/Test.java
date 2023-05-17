/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication7;

/**
 *
 * @author Alumno
 */
public class Test {
    public static void main(String[] args) {
        try {
            // Crear un BST de enteros
            BSTree<Integer> bstInt = new BSTree<>();
            bstInt.insert(5);
            bstInt.insert(3);
            bstInt.insert(7);
            bstInt.insert(2);
            bstInt.insert(4);
            bstInt.insert(6);
            bstInt.insert(8);

            System.out.println("BST de enteros:");
            System.out.print("InOrden: ");
            bstInt.inOrden();
            System.out.println();
            System.out.println("Buscar 4: " + bstInt.search(4));
            System.out.println("Buscar 10: " + bstInt.search(2));

            bstInt.remove(5);
            System.out.print("InOrden después de eliminar el 5: ");
            bstInt.inOrden();
            System.out.println();

            System.out.println("-----------------------------------------");

            // Crear un BST de cadenas
            BSTree<String> bstStr = new BSTree<>();
            bstStr.insert("a");
            bstStr.insert("b");
            bstStr.insert("c");
            bstStr.insert("d");
            bstStr.insert("e");

            System.out.println("BST de cadenas:");
            System.out.print("InOrden: ");
            bstStr.inOrden();
            System.out.println();
            System.out.println("Buscar 'e': " + bstStr.search("e"));
            System.out.println("Buscar 'a': " + bstStr.search("a"));

            bstStr.remove("a");
            System.out.print("InOrden después de eliminar 'a': ");
            bstStr.inOrden();
            System.out.println();
            
            System.out.println("Buscar 'x': " + bstStr.search("x"));

        } catch (ItemDuplicated e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ItemNotFound e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
