/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication7;

/**
 *
 * @author Alumno
 */
public class ItemNotFound extends Exception {

    public ItemNotFound(String msg) {
        super(msg);
    }
    
    public ItemNotFound() {
        super("El elemento no se encuentra en el BTS.");
    }
}
