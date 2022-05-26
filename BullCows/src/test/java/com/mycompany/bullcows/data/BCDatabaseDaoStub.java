/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullcows.data;

import com.mycompany.bullcows.models.BC;
import com.mycompany.bullcows.models.BCRounds;
import com.mycompany.bullcows.service.BCDataValidationException;
import com.mycompany.bullcows.service.BCDuplicateIdException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leela
 */
@Repository
@Profile("daoStub")
public class BCDatabaseDaoStub implements BCDao {
    @Autowired
    public BC game;
    @Autowired
    public BCRounds round;
    
    public BCDatabaseDaoStub(){
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
    public BC add(BC game) throws BCPersistenceException,
            BCDataValidationException, BCDuplicateIdException {
        if (game.getGameId()==(this.game.getGameId())){
            return game;
        }
        else{return null;}
   }

    @Override
    public List<BC> getAll() throws BCPersistenceException{
    List<BC> gameList = new ArrayList<>();
    gameList.add(game);
    return gameList;
    }

    @Override
    public List<BCRounds> getAllRounds()throws BCPersistenceException{
    List<BCRounds> roundList = new ArrayList<>();
    roundList.add(round);
    return roundList;
    }

    @Override
    public BCRounds addRound(BCRounds round) throws BCPersistenceException,
            BCDataValidationException, BCDuplicateIdException{
    if (round.getRoundId()==(this.round.getRoundId())){
            return round;
        }
        else{return null;}
    }

    @Override
    public BC findById(int id) throws BCDuplicateIdException,
            BCDataValidationException{
    if(id == game.getGameId()){
        return game;
    }else return null;
    }

    @Override
     public BCRounds findByRoundId(int id) throws BCDuplicateIdException,
            BCDataValidationException{
    if(id == round.getRoundId()){
        return round;
    }else return null;
    }

    // true if item exists and is updated
     @Override
    public boolean update(BC game) throws BCDuplicateIdException,
            BCDataValidationException{
    if(game != null){
    return true;}
    else return false;
    }

    @Override
    public boolean updateRound(BCRounds round) throws BCDuplicateIdException,
            BCDataValidationException{
    if(round != null){
    return true;}
    else return false;
    } 

    // true if item exists and is deleted
    @Override
    public boolean deleteById(int id) throws BCDuplicateIdException,
            BCDataValidationException{

    if ( id == game.getGameId()){
    return true;}
    else return false;
    } 

    @Override
    public boolean deleteByRoundId(int id)throws BCDuplicateIdException,
            BCDataValidationException{

    if ( id == round.getRoundId()){
    return true;}
    else return false;
    }  

    @Override
    public BC begin(int rndDigits, BC game) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException{
    if (game != null){
        return game;}
        else return null;
    }

    @Override
    public BCRounds guessInput(BCRounds round) throws BCDuplicateIdException,
            BCDataValidationException, BCPersistenceException{
    
        if (round != null){
        return round;}
        else return null;
    }
    
    
}
