/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullcows.data;

import com.mycompany.bullcows.models.BC;
import java.util.List;

/**
 *
 * @author leela
 */
public interface BCDao {
    BC add(BC game);

    List<BC> getAll();
    
    List<BC> getAllRounds(); 
    
    BC addRound(BC round);

    BC findById(int id);
    
    public BC findByRoundId(int id);

    // true if item exists and is updated
    boolean update(BC game);
    
    boolean updateRound(BC round);

    // true if item exists and is deleted
    boolean deleteById(int id);
    
    boolean deleteByRoundId(int id);
}
