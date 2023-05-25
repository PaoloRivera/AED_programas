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
            System.out.println("Buscar 2: " + bstInt.search(2));          
            
            System.out.println("Número de nodos no-hojas en el BST: " + bstInt.countNonLeafNodes()); //EJERCICIO 1
            System.out.println("Altura de nodo 6 en el BST: " + bstInt.getNodeHeight(6)); //EJERCICIO 2
            System.out.println("Recorrido PreOrden del BST: ");
            bstInt.preOrderIterative(); //EJERCICIO 3
            System.out.println("\n");
            System.out.println("Área del BST de enteros: " + bstInt.calculateBSTArea()); //EJERCICIO 4
            System.out.println("Valor mínimo en el BST: " + bstInt.getMin());  //EJERCICIO 6
            System.out.println("Valor máximo en el BST: " + bstInt.getMax()); //EJERCICIO 7
            bstInt.parenthesize(); //EJERCICIO 8
            
            System.out.println("\n");
           
            System.out.print("InOrden después de eliminar el 5: ");
            bstInt.remove(5);
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

            
            
            
            System.out.println("Número de nodos no-hojas en el BST: " + bstStr.countNonLeafNodes()); //EJERCICIO 1
            System.out.println("Altura de nodo 'd' en el BST: " + bstStr.getNodeHeight("d")); //EJERCICIO 2
            System.out.println("Recorrido PreOrden del BST: ");
            bstStr.preOrderIterative(); //EJERCICIO 3
            System.out.println("\n");
            System.out.println("Área del BST de str: " + bstStr.calculateBSTArea()); //EJERCICIO 4
            System.out.println("Valor mínimo en el BST: " + bstStr.getMin()); //EJERCICIO 6
            System.out.println("Valor máximo en el BST: " + bstStr.getMax()); //EJERCICIO 7
            bstStr.parenthesize(); //EJERCICIO 8
            
            
            
            bstStr.remove("a");
            System.out.print("InOrden después de eliminar 'a': ");
            bstStr.inOrden();
            System.out.println();
            
            //System.out.println("Buscar 'x': " + bstStr.search("x"));

            
            System.out.println("Tienen la misma area? " + tieneMismaArea(bstInt, bstStr)); //EJERCICIO 5

            
        } catch (ItemDuplicated e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ItemNotFound e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    //EJERCICIO 5
    public static boolean tieneMismaArea(BSTree<?> bst1, BSTree<?> bst2) {
        return bst1.calculateBSTArea() == bst2.calculateBSTArea();
    }
}
