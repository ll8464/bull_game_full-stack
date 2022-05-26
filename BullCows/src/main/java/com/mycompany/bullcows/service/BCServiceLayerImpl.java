/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullcows.service;

import com.mycompany.bullcows.data.BCDao;
import com.mycompany.bullcows.data.BCPersistenceException;
import com.mycompany.bullcows.models.BC;
import com.mycompany.bullcows.models.BCRounds;
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
    public List<BC> all() throws BCPersistenceException {
        return dao.getAll();
    }

    @Override
    public List<BCRounds> allRounds() throws BCPersistenceException {
        return dao.getAllRounds();
    }

    @Override
    public BC findById(int gameId) throws BCDuplicateIdException,
            BCDataValidationException{        
        return dao.findById(gameId);
    }

    @Override
    public BC begin(BC game) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException {
        int randomDigits = getDigits();
        return dao.begin(randomDigits, game);
    }

    @Override
    public BCRounds findByRoundId(int roundId) throws BCDuplicateIdException,
            BCDataValidationException {

        return dao.findByRoundId(roundId);
    }

    @Override
    public BC create(BC game) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException {
        return dao.add(game);
    }

    @Override
    public BCRounds createRound(BCRounds round) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException {
        return dao.addRound(round);
    }

    @Override
    public BCRounds guess(BCRounds round) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException {
        int exact = exactCounter(round.getUserGuess(),
                dao.findById(round.getGameId()).getAnswer());
        int partial = partialCounter(round.getUserGuess(),
                dao.findById(round.getGameId()).getAnswer());
        
        if (round.getUserGuess() == dao.findById(round.getGameId()).getAnswer() ){
            dao.findById(round.getGameId()).setFinished(true);}
    
    
        String results = "e:" +exact+"p:"+partial;
        
        round.setExactWins(exact);
        round.setPartialWins(partial);
        round.setUserGuess(round.getUserGuess());
        round.setResults(results);
        return dao.guessInput(round);
    }

    @Override
    public boolean update(BC game) throws BCDuplicateIdException,
            BCDataValidationException {
        return dao.update(game);
    }

    @Override
    public boolean deleteById(int gameId) throws BCDuplicateIdException,
            BCDataValidationException {
        return dao.deleteById(gameId);
    }

    @Override
    public boolean deleteByRoundId(int roundId) throws BCDuplicateIdException,
            BCDataValidationException {
        return dao.deleteByRoundId(roundId);
    }

    @Override
    public boolean updateRound(BCRounds round) throws BCDuplicateIdException,
            BCDataValidationException {
        return dao.updateRound(round);
    }

    //Calculations
    @Override
    public int getDigits() throws BCPersistenceException {
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
            
            if (num1>9){
            num1--;}
            if (num2>9){
            num2--;}
            if (num3>9){
            num3--;}
            if (num4>9){
            num4--;}
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
    public int exactCounter(int userGuess, int answer) 
            throws BCPersistenceException {
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
    public int partialCounter(int userGuess, int answer)
            throws BCPersistenceException{
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
