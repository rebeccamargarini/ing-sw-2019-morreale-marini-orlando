package it.polimi.se2019.model;

import it.polimi.se2019.model.board.Table;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;

import java.util.*;

/**
 * @author Federico Morreale
 */
public class Game extends Observable {

    private Table table;
    private ArrayList<Player> players;
    private Deck<WeaponCard> weaponsDeck;
    private Deck<PowerUpCard> powerUpDeck;
    private Deck<PowerUpCard> garbageDeck;
    private Deck<AmmoCard> ammoDeck;
    private Map<Character, Player> characterPlayers;
    
    public Game() {
    }
    
    public Table getTable() {
 
        return null;
    }

    /**
     * @return the players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * @return
     */
    public Deck<WeaponCard> getWeaponsDeck() {
        return weaponsDeck;
    }

    /**
     * @return
     */
    public Deck<PowerUpCard> getPowerUpDeck() {
        return powerUpDeck;
    }

    /**
     * @return
     */
    public Deck<PowerUpCard> getGarbageDeck() {
        return garbageDeck;
    }

    /**
     * @return
     */
    public Deck<AmmoCard> getAmmoDeck() {
        return ammoDeck;
    }

    /**
     * @return
     */
    public Map<Character,Player> getCharacterPlayers() {
        return characterPlayers;
    }

    /**
     * @param player to be added to the match
     */
    public void addPlayer(Player player) {
    }

    /**
     * @param player to link to the character
     * @param character
     */
    public void setPlayerCharacter(Player player, Character character) {
    }

    /**
     * @param weapons
     */
    public void setWeaponDeck(Deck<WeaponCard> weapons) {
    }

    /**
     * @param powerUps
     */
    public void setPowerUpDeck(Deck<PowerUpCard> powerUps) {
    }

    /**
     * @param ammos
     */
    public void setAmmoDeck(Deck<AmmoCard> ammos) {
    }

}