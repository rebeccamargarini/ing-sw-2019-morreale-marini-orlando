package it.polimi.se2019.model.player;

import it.polimi.se2019.exceptions.InvalidCharacterException;
import it.polimi.se2019.model.enumeration.Character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that manages the damages and the marks suffered by the player and
 * the points obtainable at the player's next death
 *
 * @author Simone Orlando
 */
public class PlayerBoard implements Serializable {

    private ArrayList<Character> damageLine;
    private ArrayList<Character> revengeMarks;
    private AmmoBox ammoBox;
    private boolean reverted;

    /**
     * Class constructor that initializes the data structures it uses
     */
    public PlayerBoard() {
        damageLine = new ArrayList<>();
        revengeMarks = new ArrayList<>();
        ammoBox = new AmmoBox();
        reverted = false;
    }

    /**
     * Get the player's ammo container
     *
     * @return Player's ammo container
     */
    public AmmoBox getAmmoBox() {
        return ammoBox;
    }

    /**
     * Gets the marks that the player has received from other players
     *
     * @return A list with the marks suffered
     */
    public List<Character> getRevengeMarks() {
        return revengeMarks;
    }

    public void clearMarks() {
        revengeMarks.clear();
    }

    /**
     * Get the damage suffered by the player
     *
     * @return A list with the damages suffered
     */
    public List<Character> getDamageLine() {
        return new ArrayList<>(damageLine);
    }


    /**
     * Add damage to the player
     *
     * @param character The character of player who deals the damage
     * @param value     The number of damages to be inflicted
     * @throws InvalidCharacterException if character reference is null
     */
    public void addDamage(Character character, int value) throws InvalidCharacterException {
        if (character == null)
            throw new InvalidCharacterException("Character can not be null!");
        int oldSize = damageLine.size();
        if (oldSize + value > 12) {
            for (int i = 0; i < 12 - oldSize; i++) {
                damageLine.add(character);
            }
        } else {
            for (int i = 0; i < value; i++) {
                damageLine.add(character);
            }
        }
    }


    /**
     * Remove all damage suffered by the player
     */
    public void resetDamageLine() {
        damageLine.clear();
    }

    /**
     * Add marks to the player
     *
     * @param character The character of player who marks
     * @param value     The number of marks to be added
     */
    public void addRevengeMark(Character character, int value) {
        int counter = 0;
        for (Character c : revengeMarks) {
            if (c == character)
                counter++;
        }

        if (counter + value < 3) {
            for (int i = 0; i < value; i++) {
                revengeMarks.add(character);
            }
        } else {
            for (int i = 0; i < 3 - counter; i++) {
                revengeMarks.add(character);
            }
        }
    }

    /**
     * Remove all marks received from a player
     *
     * @param character The character of the player whose marks are to be removed
     */
    public void removeRevengeMarks(Character character) {
        while (revengeMarks.contains(character)) {
            revengeMarks.remove(character);
        }
    }

    /**
     *
     * @return the value of reverted
     */
    public boolean isReverted() {
        return reverted;
    }

    /**
     *
     * @param reverted the value for reverted 
     */
    public void setReverted(boolean reverted) {
        this.reverted = reverted;
    }
}
