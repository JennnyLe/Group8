/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author destinyodia
 */
public class GoFishPlayers extends Player {

    public GoFishPlayers(String name) {
        super(name);
    }

    @Override
    public void play() {
        GoFishGame gaming= new GoFishGame();
        gaming.play();
        }
    
}
