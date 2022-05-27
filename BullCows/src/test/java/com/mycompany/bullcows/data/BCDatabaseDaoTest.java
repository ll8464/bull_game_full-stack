/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.bullcows.data;

import com.mycompany.bullcows.models.BC;
import com.mycompany.bullcows.models.BCRounds;
import com.mycompany.bullcows.service.BCDataValidationException;
import com.mycompany.bullcows.service.BCDuplicateIdException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author leela
 */

//@RunWith(SpringRunner.class)
@SpringBootTest (classes = TestApplicationConfiguration.class)

public class BCDatabaseDaoTest {
 
    @Autowired
    BCDatabaseDao testDao;
    
    @Autowired    
    public BCDatabaseDaoTest(BCDatabaseDao testDao) {
        this.testDao = testDao;
    }

    //This asserts that the creation of a game does not cause an exception
    @Test
    public void testAdd() {
        BC game = new BC();
        game.setGameId(1);
        game.setAnswer(1234);
        game.setFinished(true);
        
        
        try{
            testDao.add(game);
        }catch (BCDuplicateIdException|BCDataValidationException|
                BCPersistenceException e){
        fail("Game was valid. No exception should have been thrown.");
        }
    }
    
    @Test
    public void testGetAll() throws Exception {
        
        BC game = new BC();
        game.setGameId(1);
        game.setAnswer(1234);
        game.setFinished(true);
        
        testDao.add(game);
        
        assertEquals( 1, testDao.getAll().size(), 
                                   "There are 1 games.");
    assertTrue( testDao.getAll().contains(game),
                              "The only game should be 1.");
        
    }
    
    @Test
    public void testFindById() throws BCDuplicateIdException,
            BCDataValidationException{
        
        BC game = new BC();
        game.setGameId(1);
        game.setAnswer(1234);
        game.setFinished(true);
        
        BC shouldBeOne = testDao.findById(1);
        
        assertNotNull(shouldBeOne, "Game should be not null");
        assertEquals(shouldBeOne.getGameId(),1, "GameId should be 1.");
                        
    }
    
    @Test
    public void testUpdate() throws BCDuplicateIdException,
            BCDataValidationException,
            BCPersistenceException {
        BC game = new BC();
        
        testDao.add(game);
    
        assertTrue(testDao.update(game));
    }
    
    @Test
    public void testDeleteById() throws BCDuplicateIdException,
            BCDataValidationException,
            BCPersistenceException {
        BC game = new BC();
        game.setGameId(1);
        
        testDao.add(game);
    
        assertTrue(testDao.deleteById(game.getGameId()));
    }
    
    @Test
    public void begin() throws BCDuplicateIdException,
            BCDataValidationException,
            BCPersistenceException {
        BC game = new BC();
        game.setGameId(1);
                        
        testDao.add(game);
    
        assertNotNull(testDao.begin(game.getGameId() ,game));
    }
    
   @Test
    public void guessInput() throws BCDuplicateIdException,
            BCDataValidationException,
            BCPersistenceException {
        BCRounds round = new BCRounds();
        round.setGameId(1);
                        
        testDao.addRound(round);
    
        assertNotNull(testDao.guessInput(round));
    }
    
    
}
