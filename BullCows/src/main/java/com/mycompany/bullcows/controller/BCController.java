/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullcows.controller;

import com.mycompany.bullcows.data.BCDao;
import com.mycompany.bullcows.models.BC;
import com.mycompany.bullcows.models.BCRounds;
import com.mycompany.bullcows.service.BCServiceLayerImpl;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author leela
 */
@RestController
@RequestMapping("/api")
public class BCController {

    
    private final BCServiceLayerImpl service;

    public BCController(BCDao dao, BCServiceLayerImpl service) {
        
        this.service = service;
    }

    //GET Requests
    @GetMapping
    public List<BC> all() {
        return service.all();
    }

    @GetMapping("/rounds")
    public List<BCRounds> allRounds() {
        return service.allRounds();
    }

    @GetMapping("game/{gameId}")
    public BC findById(int gameId) {

        return service.findById(gameId);
    }

    @GetMapping("/rounds/{roundId}")
    public BCRounds findByRoundId(int roundId) {

        return service.findByRoundId(roundId);
    }

    //POST Request
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BC create(@RequestBody BC game) {
        return service.create(game);
    }

    /*
    This should start the game. It needs to generate a random number from 
    service layer.*/
    @PostMapping("begin")
    @ResponseStatus(HttpStatus.CREATED)
    public BC begin(@RequestBody BC game) {

        return service.begin(game);
    }

    /*
    This should take in the user input and compare it to the answer. It returns
    the round object with the results filled in. 
     */
    @PostMapping("guess")
    @ResponseStatus(HttpStatus.CREATED)
    public BCRounds guess(@RequestBody BCRounds game) {
        return service.guess(game);

    }

    @PostMapping("/rounds")
    @ResponseStatus(HttpStatus.CREATED)
    public BCRounds createRound(@RequestBody BCRounds round) {
        return service.createRound(round);
    }

//PUT REQUESTS
    @PutMapping("/{gameId}")
    public ResponseEntity update(@PathVariable int gameId,
            @RequestBody BC game) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (gameId != game.getGameId()) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (service.update(game)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @PutMapping("/rounds/{roundId}")
    public ResponseEntity updateRound(@PathVariable int roundId,
            @RequestBody BCRounds round) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (roundId != round.getRoundId()) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (service.updateRound(round)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }

//DELETE REQUESTS
    @DeleteMapping("/{gameId}")
    public ResponseEntity delete(@PathVariable int gameId) {
        if (service.deleteById(gameId)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/rounds/{roundId}")
    public ResponseEntity deleteRound(@PathVariable int roundId) {
        if (service.deleteByRoundId(roundId)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

}
