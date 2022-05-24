/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullcows.service;

import com.mycompany.bullcows.models.BC;
import com.mycompany.bullcows.models.BCRounds;
import java.util.List;

/**
 *
 * @author leela
 */
public interface BCServiceLayer {

    public List<BC> all();

    public List<BCRounds> allRounds();

    public BC findById(int gameId);

    public BC begin(BC game);

    public BCRounds findByRoundId(int roundId);

    public BC create(BC game);

    public BCRounds createRound(BCRounds round);

    public BCRounds guess(BCRounds game);

    public boolean update(BC game);

    public boolean deleteById(int gameId);

    public boolean deleteByRoundId(int roundId);

    public boolean updateRound(BCRounds round);

    public int getDigits();

    public int exactCounter(int userGuess, int answer);

    public int partialCounter(int userGuess, int answer);

}
