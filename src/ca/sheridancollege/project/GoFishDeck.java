/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author JennyLe
 * @author Sad
 * @author destinyodia
 * @author Christeena
 * Date: 19 Feb 2024
 */
public class GoFishDeck extends GroupOfCards{
    private final List<GoFishCard> cards ;

   
    
    

    public GoFishDeck(int size) {
        super(size);
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new GoFishCard(rank, suit));
            }
        }
        shuffle();
    }
    

    public GoFishCard dealCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    public int size() {
        return cards.size();
    }
    
  
    
}

