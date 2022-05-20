/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.classroster.ui;

import java.util.Scanner;

/**
 *
 * @author leela
 */
public class UserIOConsoleImpl implements UserIO {
    //Variables
    


    private String message = "";
    private String prompt = "";
    private int num=0;    
    private double doubleNum = 0.0;
    private float floatNum =  0.0f;
    private long longNum = 0;
    
    //Methods
    @Override
    public void print(String message){
   this.message = message;
        System.out.println(message);}
    
    @Override
    public String readString(String prompt){
        System.out.println(prompt);
        Scanner input = new Scanner(System.in);
        String userString = input.nextLine();
     return userString;}
    
    @Override
    public int readInt(String prompt){
    System.out.println(prompt);
    
    Scanner input = new Scanner(System.in);
         num = input.nextInt();
        return num;}
   
    @Override
    public int readInt (String prompt, int min, int max){
    System.out.println(prompt);
    
    Scanner input = new Scanner(System.in);
    
    num = input.nextInt();
    
    while ((num < min)||(num> max)){
        System.out.println("Enter numbers within specificied range. " + min+
                " - "+ max);
         num = input.nextInt();
    }         
    return num;}
    
    
    @Override
    public double readDouble(String prompt){
         System.out.println(prompt);
    
    Scanner input = new Scanner(System.in);
         doubleNum = input.nextDouble();
    return doubleNum;}
    
    @Override
    public double readDouble(String prompt, double min, double max){
    System.out.println(prompt);
    
    Scanner input = new Scanner(System.in);
    
    doubleNum = input.nextInt();
    
    while ((doubleNum < min)||(doubleNum > max)){
        System.out.println("Enter numbers within specificied range. " + min+
                " - "+ max);
         doubleNum = input.nextDouble();
    }         
        
        return doubleNum;
    }
    
    @Override
    public float readFloat(String prompt){
         System.out.println(prompt);
    
    Scanner input = new Scanner(System.in);
         floatNum = input.nextFloat();
    return floatNum;}
    
    @Override
    public float readFloat(String prompt, float min, float max){
    System.out.println(prompt);
    
    Scanner input = new Scanner(System.in);
    
    floatNum = input.nextInt();
    
    while ((floatNum < min)||(floatNum > max)){
        System.out.println("Enter numbers within specificied range. " + min+
                " - "+ max);
         floatNum = input.nextFloat();
    }         
        
        return floatNum;}
    
    @Override
    public long readLong(String prompt){
         System.out.println(prompt);
    
    Scanner input = new Scanner(System.in);
         longNum = input.nextLong();
    return longNum;}
    
    @Override
    public long readLong(String prompt, long min, long max){
    System.out.println(prompt);
    
    Scanner input = new Scanner(System.in);
    
    longNum = input.nextInt();
    
    while ((longNum < min)||(longNum > max)){
        System.out.println("Enter numbers within specificied range. " + min+
                " - "+ max);
         longNum = input.nextLong();
    }         
        
        
        return longNum;}
    
}
