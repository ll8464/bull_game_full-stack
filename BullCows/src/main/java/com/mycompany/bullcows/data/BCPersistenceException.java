/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullcows.data;

/**
 *
 * @author leela
 */
public class BCPersistenceException extends Exception {
    public BCPersistenceException(String message){
    super(message);}
    
    public BCPersistenceException (String message, Throwable cause){
    super(message,cause);}
}
