/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullcows.service;

/**
 *
 * @author leela
 */
public class BCDuplicateIdException extends Exception {
    public BCDuplicateIdException(String message) {
        super(message);
    }

    public BCDuplicateIdException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
