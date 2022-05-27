/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullcows.service;

import com.mycompany.bullcows.data.BCPersistenceException;
import com.mycompany.bullcows.models.BC;
import com.mycompany.bullcows.models.BCRounds;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leela
 */
@Repository
@Profile("serviceStub")
public class BCServiceLayerStub implements BCServiceLayer {

    public BC game;
    public BCRounds round;

    public BCServiceLayerStub() {
        game = new BC();
        game.setAnswer(1234);
        game.setFinished(true);
        game.setGameId(1);
        round = new BCRounds();
        round.setExactWins(1);
        round.setGameId(1);
        round.setExactWins(4);
        round.setPartialWins(4);
        round.setResults("e:4:p:4");
        round.setUserGuess(1234);
        round.setGuessTime("2022/05/25");
    }

    @Override
    public BC findById(int id) throws BCDuplicateIdException,
            BCDataValidationException {
        if (id == game.getGameId()) {
            return game;
        } else {
            return null;
        }
    }

    @Override
    public BCRounds findByRoundId(int id) throws BCDuplicateIdException,
            BCDataValidationException {
        if (id == round.getRoundId()) {
            return round;
        } else {
            return null;
        }
    }

    @Override
    public List<BC> all() throws BCPersistenceException {
        List<BC> gameList = new ArrayList<>();
        gameList.add(game);
        return gameList;
    }

    @Override
    public List<BCRounds> allRounds() throws BCPersistenceException {
        List<BCRounds> roundList = new ArrayList<>();
        roundList.add(round);
        return roundList;
    }

    // true if item exists and is updated
    @Override
    public boolean update(BC game) throws BCDuplicateIdException,
            BCDataValidationException {
        if (game != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateRound(BCRounds round) throws BCDuplicateIdException,
            BCDataValidationException {
        if (round != null) {
            return true;
        } else {
            return false;
        }
    }

    // true if item exists and is deleted
    @Override
    public boolean deleteById(int id) throws BCDuplicateIdException,
            BCDataValidationException {

        if (id == game.getGameId()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteByRoundId(int id) throws BCDuplicateIdException,
            BCDataValidationException {

        if (id == round.getRoundId()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public BC begin(BC game) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException {
        if (game != null) {
            return game;
        } else {
            return null;
        }
    }

    @Override
    public BCRounds guess(BCRounds round) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException {

        if (round != null) {
            return round;
        } else {
            return null;
        }
    }

    @Override
    public int getDigits() throws BCPersistenceException {

        return game.getAnswer();
    }

    @Override
    public int exactCounter(int userGuess, int answer)
            throws BCPersistenceException {

        return round.getExactWins();
    }

    @Override
    public int partialCounter(int userGuess, int answer)
            throws BCPersistenceException {

        return round.getPartialWins();
    }

    @Override
    public BC create(BC game) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException {
        return game;
    }

    @Override
    public BCRounds createRound(BCRounds round) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException {
        return round;
    }

}
