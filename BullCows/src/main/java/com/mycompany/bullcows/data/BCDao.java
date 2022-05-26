/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullcows.data;

import com.mycompany.bullcows.models.BC;
import com.mycompany.bullcows.models.BCRounds;
import com.mycompany.bullcows.service.BCDataValidationException;
import com.mycompany.bullcows.service.BCDuplicateIdException;
import java.util.List;

/**
 *
 * @author leela
 */
public interface BCDao {

    BC add(BC game) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException;

    List<BC> getAll() throws BCPersistenceException;

    List<BCRounds> getAllRounds() throws BCPersistenceException;

    BCRounds addRound(BCRounds round) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException;

    BC findById(int id) throws BCDuplicateIdException,
            BCDataValidationException;

    public BCRounds findByRoundId(int id) throws BCDuplicateIdException,
            BCDataValidationException;

    // true if item exists and is updated
    boolean update(BC game) throws BCDuplicateIdException,
            BCDataValidationException;

    boolean updateRound(BCRounds round) throws BCDuplicateIdException,
            BCDataValidationException;

    // true if item exists and is deleted
    boolean deleteById(int id) throws BCDuplicateIdException,
            BCDataValidationException;

    boolean deleteByRoundId(int id) throws BCDuplicateIdException,
            BCDataValidationException;

    public BC begin(int rndDigits, BC game) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException;

    public BCRounds guessInput(BCRounds round) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException;
}
