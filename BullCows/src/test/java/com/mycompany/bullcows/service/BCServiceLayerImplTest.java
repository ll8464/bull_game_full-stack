/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.bullcows.service;

import com.mycompany.bullcows.data.BCPersistenceException;
import com.mycompany.bullcows.data.TestApplicationConfiguration;
import com.mycompany.bullcows.models.BCRounds;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author leela
 */
//@RunWith(SpringRunner.class)
@SpringBootTest (classes = TestApplicationConfiguration.class)
public class BCServiceLayerImplTest {
    
    @Autowired
    BCServiceLayerImpl service;
    
    @Autowired    
    public BCServiceLayerImplTest(    BCServiceLayerImpl service) {
        this.service = service;
    }
    
   

    @Test
    public void testGetDigits() throws BCPersistenceException {
        assertNotNull(service.getDigits());
    }
    
    @Test
    public void testExactCounter() throws BCPersistenceException{
        BCRounds rounds = new BCRounds();
        rounds.setUserGuess(1234);
        int answer = 1234;
        
        assertNotNull(service.exactCounter(rounds.getUserGuess(), answer));
         assertEquals(service.exactCounter(rounds.getUserGuess(), answer), 4);  
        
    
    }
    
    @Test
    public void testPartialCounter() throws BCPersistenceException{
        BCRounds rounds = new BCRounds();
        rounds.setUserGuess(1234);
        int answer = 4321;
        
        assertNotNull(service.partialCounter(rounds.getUserGuess(), answer));
         assertEquals(service.partialCounter(rounds.getUserGuess(), answer), 4);  
        
    
    }
}
