/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullcows.data;

import com.mycompany.bullcows.models.BC;
import com.mycompany.bullcows.models.BCRounds;
import java.util.List;

/**
 *
 * @author leela
 */
public interface BCDao {

    BC add(BC game);

    List<BC> getAll();

    List<BCRounds> getAllRounds();

    BCRounds addRound(BCRounds round);

    BC findById(int id);

    public BCRounds findByRoundId(int id);

    // true if item exists and is updated
    boolean update(BC game);

    boolean updateRound(BCRounds round);

    // true if item exists and is deleted
    boolean deleteById(int id);

    boolean deleteByRoundId(int id);

    public BC begin(int rndDigits, BC game);

    public BCRounds guessInput(BCRounds round);
}
