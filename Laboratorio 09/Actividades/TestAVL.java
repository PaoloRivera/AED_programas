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
       
    AVLTree<Integer> avlTree = new AVLTree<>();

    try {
      // Caso 1: RSR
      avlTree.insert(30);
      avlTree.insert(20);
      avlTree.insert(10);
      // Después de insertar 10, se produce un desequilibrio en el nodo 30. Se aplica RSR.
      System.out.println("Después del Caso 1 (RSR): " + avlTree.toString());

      // Caso 2: RSL
      avlTree.insert(40);
      avlTree.insert(50);
      // Después de insertar 50, se produce un desequilibrio en el nodo 20. Se aplica RSL.
      System.out.println("Después del Caso 2 (RSL): " + avlTree.toString());

      // Caso 3: RDR
      avlTree.insert(25);
      // Después de insertar 25, se produce un desequilibrio en el nodo 40. Se aplica RDR.
      System.out.println("Después del Caso 3 (RDR): " + avlTree.toString());

      // Caso 4: RDL
      avlTree.insert(35);
      // Después de insertar 35, se produce un desequilibrio en el nodo 30. Se aplica RDL.
      System.out.println("Después del Caso 4 (RDL): " + avlTree.toString());

      // Repitiendo los casos para que haya al menos 2 de cada tipo.
      // Caso 5: RSR
      avlTree.insert(9);
      avlTree.insert(8);
      System.out.println("Después del Caso 5 (RSR): " + avlTree.toString());

      // Caso 6: RSL
      avlTree.insert(51);
      avlTree.insert(52);
      System.out.println("Después del Caso 6 (RSL): " + avlTree.toString());

      // Caso 7: RDR
      avlTree.insert(26);
      System.out.println("Después del Caso 7 (RDR): " + avlTree.toString());

      // Caso 8: RDL
      avlTree.insert(36);
      System.out.println("Después del Caso 8 (RDL): " + avlTree.toString());

    } catch (ItemDuplicated e) {
      e.printStackTrace();
    }
  }
}

