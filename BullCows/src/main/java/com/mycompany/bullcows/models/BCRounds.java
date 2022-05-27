/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullcows.models;

/**
 *
 * @author leela
 */
public class BCRounds {

    private int gameId;
    private int roundId;
    private int partialWins;
    private int exactWins;
    private String guessTime;
    private int userGuess;
    private String results;

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
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

    public String getGuessTime() {
        return guessTime;
    }

    public void setGuessTime(String guessTime) {
        this.guessTime = guessTime;
    }

    public int getUserGuess() {
        return userGuess;
    }

    public void setUserGuess(int userGuess) {
        this.userGuess = userGuess;
    }
}
