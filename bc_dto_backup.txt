/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullcows.models;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author leela
 */
public class BC {
    private int gameId;
    private int roundId;
    private int partialWins;
    private int exactWins;
    private boolean finished;
    private int answer;
    private String guessTime;
    private int userGuess;

    public int getUserGuess() {
        return userGuess;
    }

    public void setUserGuess(int userGuess) {
        this.userGuess = userGuess;
    }

    public String getGuessTime() {
        return guessTime;
    }

    public void setGuessTime(String guessTime) {
        this.guessTime = guessTime;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getPartialWins() {
        return partialWins;
    }

    public void setPartialWins(int partialWins) {
        this.partialWins = partialWins;
    }

    public int getExactWins() {
        return exactWins;
    }

    public void setExactWins(int exactWins) {
        this.exactWins = exactWins;
    }
    
    public boolean isFinished(){
        return finished;
    }
    
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
