/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.bullcows;

import java.util.Scanner;

/**
 *
 * @author leela
 */
public class TesterApp {

    private static TesterApp obj = new TesterApp();
    int num1 = (int) (Math.random() * (9 - 0));
    int num2 = (int) (Math.random() * (9 - 0));
    int num3 = (int) (Math.random() * (9 - 0));
    int num4 = (int) (Math.random() * (9 - 0));
    int partial;
    int userWins;

    public static void main(String[] args) {

        //System.out.println(obj.getDigits());
        //Set up try again while loop. Need a round counter
        //Order to call functions:
        obj.getDigits();
        obj.pointCounter();
        obj.displayResults(obj.partial, obj.userWins);

        System.out.println(obj.getDigits());
    }

    //getDigits produces the randomly generated digit
    public String getDigits() {

        //Keeps going until all digits are unique
        while (true) {
            if (num1 == num2 || num1 == num3 || num1 == num4) {
                num1++;
            } else if (num2 == num3
                    || num2 == num4) {
                num2++;
            } else if (num3 == num4) {
                num3++;
            }
            //The loop only stops when none of the digits match
            if (num1 != num2 && num1 != num3 && num1 != num4 && num2 != num3
                    && num2 != num4 && num3 != num4) {
                break;
            }

        }
        //Update num attributes in object
        obj.num1 = num1;
        obj.num2 = num2;
        obj.num3 = num3;
        obj.num4 = num4;

        String ans = "" + num1 + num2 + num3 + num4;
        //System.out.println(ans);
        return ans;

    }

    public void pointCounter() {
        //Variables
        Scanner input = new Scanner(System.in);
        int userWin = 0;
        int partial = 0;

        //UserInputs
        System.out.println("Guess the first digit!");
        int userInput1 = input.nextInt();

        System.out.println("Guess the second digit!");
        int userInput2 = input.nextInt();

        System.out.println("Guess the third digit!");
        int userInput3 = input.nextInt();

        System.out.println("Guess the fourth digit!");
        int userInput4 = input.nextInt();

        //Check that the user completely guessed 4 digits before giving partials
        if (userInput1 == num1 && userInput2 == num2 && userInput3 == num3
                && userInput4 == num4) {
            obj.userWins++;
        }

        //Partial Points
        if (userInput1 == num1) {
            System.out.println("Digit 1 is Correct! It is: " + num1);
            obj.partial++;
        }
        if (userInput2 == num2) {
            System.out.println("Digit 2 is Correct! It is: " + num2);
            obj.partial++;
        }
        if (userInput3 == num3) {
            System.out.println("Digit 3 is Correct! It is: " + num3);
            obj.partial++;
        }
        if (userInput4 == num4) {
            System.out.println("Digit 4 is Correct! It is: " + num4);
            obj.partial++;
        }

    }

    public void displayResults(int p, int e) {
        System.out.println("User Results: "
                + "e:" + obj.userWins + ":p:" + obj.partial);
    }
}
