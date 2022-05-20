/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullcows.controller;

import com.mycompany.bullcows.data.BCDao;
import com.mycompany.bullcows.models.BC;
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

    private final BCDao dao;

    public BCController(BCDao dao) {
        this.dao = dao;
    }

    //GET Requests
    @GetMapping
    public List<BC> all() {
        return dao.getAll();
    }

    @GetMapping("/rounds")
    public List<BC> allRounds() {
        return dao.getAllRounds();
    }

    @GetMapping("game/{gameId}")
    public BC findById(int gameId) {

        return dao.findById(gameId);
    }

    @GetMapping("/rounds/{roundId}")
    public BC findByRoundId(int roundId) {

        return dao.findById(roundId);
    }

    //POST Request
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BC create(@RequestBody BC game) {
        return dao.add(game);
    }
    /*
    This should start the game. It needs to generate a random number from 
    service layer.
    @PostMapping("begin")
    @ResponseStatus(HttpStatus.CREATED)
    public BC begin(@RequestBody BC game) {
        return dao.add(game);
    }      
    */
    
    /*
    This should take in the user input and compare it to the answer. It returns
    the round object with the results filled in. 
    @PostMapping("guess")
    @ResponseStatus(HttpStatus.CREATED)
    public BC guess(@RequestBody BC game) {
        return dao.add(game);
    }
    
    */

    @PostMapping("/rounds")
    @ResponseStatus(HttpStatus.CREATED)
    public BC createRound(@RequestBody BC round) {
        return dao.addRound(round);
    }

//PUT REQUESTS
    @PutMapping("/{gameId}")
    public ResponseEntity update(@PathVariable int gameId, 
            @RequestBody BC game) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (gameId != game.getGameId()) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (dao.update(game)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }
    
    @PutMapping("/rounds/{roundId}")
    public ResponseEntity updateRound(@PathVariable int roundId, 
            @RequestBody BC round) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (roundId != round.getRoundId()) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (dao.updateRound(round)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }

//DELETE REQUESTS
    @DeleteMapping("/{gameId}")
    public ResponseEntity delete(@PathVariable int gameId) {
        if (dao.deleteById(gameId)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/rounds/{roundId}")
    public ResponseEntity deleteRound(@PathVariable int roundId) {
        if (dao.deleteByRoundId(roundId)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
