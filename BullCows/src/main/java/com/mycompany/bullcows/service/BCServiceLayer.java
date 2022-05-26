/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullcows.service;

import com.mycompany.bullcows.data.BCPersistenceException;
import com.mycompany.bullcows.models.BC;
import com.mycompany.bullcows.models.BCRounds;
import java.util.List;

/**
 *
 * @author leela
 */
public interface BCServiceLayer {

    public List<BC> all() throws BCPersistenceException;

    public List<BCRounds> allRounds() throws BCPersistenceException;

    public BC findById(int gameId) throws BCDuplicateIdException,
            BCDataValidationException;

    public BC begin(BC game) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException;

    public BCRounds findByRoundId(int roundId) throws BCDuplicateIdException,
            BCDataValidationException;

    public BC create(BC game) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException;

    public BCRounds createRound(BCRounds round) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException;

    public BCRounds guess(BCRounds game) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException;

    public boolean update(BC game) throws BCDuplicateIdException,
            BCDataValidationException;

    public boolean deleteById(int gameId) throws BCDuplicateIdException,
            BCDataValidationException;

    public boolean deleteByRoundId(int roundId) throws BCDuplicateIdException,
            BCDataValidationException;

    public boolean updateRound(BCRounds round) throws BCDuplicateIdException,
            BCDataValidationException;

    public int getDigits()throws BCPersistenceException;

    public int exactCounter(int userGuess, int answer)
            throws BCPersistenceException;

    public int partialCounter(int userGuess, int answer) 
            throws BCPersistenceException;

}
