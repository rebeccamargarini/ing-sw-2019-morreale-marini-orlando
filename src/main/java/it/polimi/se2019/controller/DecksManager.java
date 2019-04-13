package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.InvalidDeckException;
import it.polimi.se2019.exceptions.NoCardException;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.powerups.PowerUpCard;

/**
 * A class that manages the refill and the draw operations on the deck
 *
 * @author Gabriel Raul Marini
 */
public class DecksManager {

    private Deck<PowerUpCard> powerUps;
    private Deck<PowerUpCard> garbageDeck;
    private Deck<AmmoCard> ammoDeck;

    public DecksManager(Deck<PowerUpCard> powerUps, Deck<PowerUpCard> garbageDeck, Deck<AmmoCard> ammoDeck) {
        this.powerUps = powerUps;
        this.garbageDeck = garbageDeck;
        this.ammoDeck = ammoDeck;
    }

    /**
     * Draw a card from the PowerUp deck and refill it in case it's empty
     *
     * @return a PowerUpCard from the deck
     */
    public PowerUpCard drawPowerUp() {
        PowerUpCard powerUp;
        try {
            powerUp = powerUps.drawCard();
        } catch (NoCardException e) {
            refillDeck();
            powerUp = powerUps.remove(powerUps.size() - 1);
        }
        return powerUp;
    }

    /**
     * This method is called when a player pick an AmmoCard from a Platform in order
     * to put a new one on the board
     *
     * @param oldAmmoCard picked by the player on the platform
     * @throws NoCardException if previous operation were wrong
     */
    public AmmoCard getNewAmmoCard(AmmoCard oldAmmoCard) throws NoCardException {
        ammoDeck.add(oldAmmoCard);
        ammoDeck.mix();
        return ammoDeck.drawCard();
    }

    /**
     * Check the deck status before drawing a card and refill the PowerUp deck with new
     * cards id necessary
     */
    private void refillDeck() {
        try {
            powerUps.addCards(garbageDeck);
            powerUps.mix();
        } catch (InvalidDeckException e) {
            System.out.println("Something went wrong when refilling the deck");
        }
    }

}