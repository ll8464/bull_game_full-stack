/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullcows.service;

import com.mycompany.bullcows.data.BCDao;
import com.mycompany.bullcows.models.BC;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leela
 */
@Repository
public class BCServiceLayerImpl implements BCServiceLayer {

    @Autowired
    BCDao dao;

    private static BCServiceLayerImpl obj = new BCServiceLayerImpl();

    int partialWins;
    int exactWins;

    //Database Manipulation via Dao
    @Override
    public List<BC> all() {
        return dao.getAll();
    }

    @Override
    public List<BC> allRounds() {
        return dao.getAllRounds();
    }

    @Override
    public BC findById(int gameId) {

        return dao.findById(gameId);
    }

    @Override
    public BC begin(BC game) {
        int randomDigits = getDigits();
        return dao.begin(randomDigits, game);
    }

    @Override
    public BC findByRoundId(int roundId) {

        return dao.findById(roundId);
    }

    @Override
    public BC create(BC game) {
        return dao.add(game);
    }

    @Override
    public BC createRound(BC round) {
        return dao.addRound(round);
    }

    @Override
    public BC guess(BC game) {
        int exact = exactCounter(game.getUserGuess(),
                dao.findById(game.getGameId()).getAnswer());
        int partial = partialCounter(game.getUserGuess(),
                dao.findById(game.getGameId()).getAnswer());
        game.setExactWins(exact);
        game.setPartialWins(partial);
        return dao.guessInput(game);
    }

    @Override
    public boolean update(BC game) {
        return dao.update(game);
    }

    @Override
    public boolean deleteById(int gameId) {
        return dao.deleteById(gameId);
    }

    @Override
    public boolean deleteByRoundId(int roundId) {
        return dao.deleteByRoundId(roundId);
    }

    @Override
    public boolean updateRound(BC round) {
        return dao.updateRound(round);
    }

    //Calculations
    @Override
    public int getDigits() {
        int num1 = (int) (Math.random() * (9 - 0));
        int num2 = (int) (Math.random() * (9 - 0));
        int num3 = (int) (Math.random() * (9 - 0));
        int num4 = (int) (Math.random() * (9 - 0));
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

        String ansString = "" + num1 + num2 + num3 + num4;

        int ans = Integer.parseInt(ansString);
        return ans;

    }

    // Guess: 1234 => "e4p0"
    // Guess: 1432 => "e2p2"
    // Guess: 4321 => "e0p4"
    @Override
    public int exactCounter(int userGuess, int answer) {
        //Checks for exact wins 
        //Converts ints to String to allow comparision of digits
        String userInput = Integer.toString(userGuess);
        String ans = Integer.toString(answer);

        //Loops through each digit to count up exact Wins
        for (int i = 0; i < 4; i++) {
            if (userInput.charAt(i) == ans.charAt(i)) {
                exactWins++;
            }
        }
        obj.exactWins = exactWins;
        return obj.exactWins;
    }

    @Override
    public int partialCounter(int userGuess, int answer) {
        //Converts ints to String to allow comparision of digits
        String userInput = Integer.toString(userGuess);
        String ans = Integer.toString(answer);

        //UserInputs
        //Loops through each digit to count up partial Wins
        //The != ensures the exact Wins are not included in the count
        for (int i = 0; i < 4; i++) {
            if (userInput.indexOf(ans.charAt(i)) > -1 && userInput.charAt(i)
                    != ans.charAt(i)) {
                partialWins++;
            }

        }

        obj.partialWins = partialWins;

        return obj.partialWins;
    }

}
