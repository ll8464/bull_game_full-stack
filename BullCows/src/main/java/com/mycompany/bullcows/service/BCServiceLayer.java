/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullcows.service;

import com.mycompany.bullcows.models.BC;
import java.util.List;

/**
 *
 * @author leela
 */
public interface BCServiceLayer {

    public List<BC> all();

    public List<BC> allRounds();

    public BC findById(int gameId);

    public BC begin(BC game);

    public BC findByRoundId(int roundId);

    public BC create(BC game);

    public BC createRound(BC round);

    public BC guess(BC game);

    public boolean update(BC game);

    public boolean deleteById(int gameId);

    public boolean deleteByRoundId(int roundId);

    public boolean updateRound(BC round);

    public int getDigits();

    public int exactCounter(int userGuess, int answer);

    public int partialCounter(int userGuess, int answer);

}
