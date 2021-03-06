package it.polimi.se2019.model.player;


import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.AmmoCard;
import it.polimi.se2019.model.card.Deck;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.enumeration.Orientation;
import it.polimi.se2019.utils.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * A test class verifying that player behaviour is correct with the game guidelines
 *
 * @author Simone Orlando
 * @author Gabriel Raul Marini
 */
public class TestPlayer {
    private Deck<PowerUpCard> deck;
    private Deck<AmmoCard> deckAmmos;

    @Before
    public void initTest() throws InvalidCardException, InvalidCubeException, InvalidNameException, InvalidImageException, InvalidDeckException, IOException {
        JsonParser parser = new JsonParser("/json/powerups.json");
        deck = parser.buildPowerupCards();
        JsonParser parserAmmos = new JsonParser("/json/ammocards.json");
        deckAmmos = parserAmmos.buildAmmoCards();
    }

    @Test
    public void testChangePlatform() throws NoCardException, InvalidCardException, InvalidPositionException, InvalidCharacterException {
        int[] pos = {1, 0};

        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player("ciao", Character.BANSHEE, start);

        Platform newPlat = new Platform(pos, true, card, Color.BLUE, orient);

        player.changePlatform(newPlat);
        assertEquals(player.getCurrentPlatform(), newPlat);

        try {
            player.changePlatform(null);
            fail();
        } catch (InvalidPositionException e) {

        }
    }

    @Test
    public void testAddPoint() throws NoCardException, InvalidCardException, InvalidPositionException, InvalidCharacterException {
        int[] pos = {1, 0};
        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player("ciao", Character.BANSHEE, start);

        player.addPoint(5);
        assertEquals(5, player.getCurrentScore());

    }

    @Test
    public void testAddDeath() throws NoCardException, InvalidCardException, InvalidPositionException, InvalidCharacterException {
        int[] pos = {1, 0};
        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player("ciao", Character.BANSHEE, start);

        player.addDeath();
        assertEquals(1, player.getNumOfDeaths());
    }

    @Test
    public void testResetDeaths() throws NoCardException, InvalidCardException, InvalidPositionException, InvalidCharacterException {
        int[] pos = {1, 0};
        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player("ciao", Character.BANSHEE, start);

        player.addDeath();
        assertEquals(1, player.getNumOfDeaths());
        player.resetDeaths();
        assertEquals(0, player.getNumOfDeaths());
    }

    @Test
    public void testAddPowerUpCard() throws NoCardException, InvalidCardException, InvalidPositionException, InvalidCharacterException {
        int[] pos = {1, 0};
        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player("ciao", Character.BANSHEE, start);

        PowerUpCard pCard = deck.drawCard();

        player.addPowerUpCard(pCard);

        assertTrue(player.getPowerUpCards().contains(pCard));
    }

    @Test
    public void testRemovePowerUpCard() throws NoCardException, InvalidCardException, InvalidPositionException, InvalidCharacterException {
        int[] pos = {1, 0};
        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player("ciao", Character.BANSHEE, start);

        PowerUpCard pCard = deck.drawCard();
        player.addPowerUpCard(pCard);
        assertTrue(player.getPowerUpCards().contains(pCard));
        try {
            player.removePowerUpCard(pCard);
        } catch (InvalidCardException e) {
            fail();
        }
        assertFalse(player.getPowerUpCards().contains(pCard));

        try {
            player.removePowerUpCard(null);
            fail();
        } catch (InvalidCardException e) {

        }
    }

    @Test
    public void testAddWeaponCard() throws NoCardException, InvalidCardException, InvalidPositionException, InvalidCharacterException {
        int[] pos = {1, 0};
        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player("ciao", Character.BANSHEE, start);

        WeaponCard wCard = new WeaponCard();
        try {
            player.addWeaponCard(wCard);
        } catch (MaxWeaponException e) {
            fail();
        }
        assertTrue(player.getWeaponCards().contains(wCard));

        try {
            player.addWeaponCard(wCard);
            player.addWeaponCard(wCard);
        } catch (MaxWeaponException e) {
            fail();
        }

        try {
            player.addWeaponCard(wCard);
            fail();
        } catch (MaxWeaponException e) {

        }
    }

    @Test
    public void testRemoveWeaponCard() throws NoCardException, InvalidCardException, InvalidPositionException, InvalidCharacterException, MaxWeaponException {
        int[] pos = {1, 0};
        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player("ciao", Character.BANSHEE, start);

        WeaponCard wCard = new WeaponCard();
        player.addWeaponCard(wCard);
        assertTrue(player.getWeaponCards().contains(wCard));


        player.removeWeaponCard(wCard);

        assertFalse(player.getWeaponCards().contains(wCard));

    }

    @Test
    public void testSetFrenzyModeType() throws NoCardException, InvalidCardException, InvalidPositionException, InvalidCharacterException {
        int[] pos = {1, 0};
        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player("ciao", Character.BANSHEE, start);

        player.setFrenzyModeType(2);
        assertEquals(2, player.getFrenzyModeType());
    }

    @Test
    public void testIsDamaged() throws NoCardException, InvalidCardException, InvalidPositionException, InvalidCharacterException {
        int[] pos = {1, 0};
        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player("ciao", Character.BANSHEE, start);

        player.getPlayerBoard().addDamage(Character.BANSHEE, 5);
        assertTrue(player.isDamaged());

        player.getPlayerBoard().resetDamageLine();
        player.getPlayerBoard().addDamage(Character.BANSHEE, 2);
        assertTrue(!player.isDamaged());

        player.getPlayerBoard().resetDamageLine();
        player.getPlayerBoard().addDamage(Character.BANSHEE, 7);
        assertTrue(!player.isDamaged());
    }

    @Test
    public void testIsSeriouslyDamaged() throws NoCardException, InvalidCardException, InvalidPositionException, InvalidCharacterException {
        int[] pos = {1, 0};
        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player("ciao", Character.BANSHEE, start);

        player.getPlayerBoard().addDamage(Character.BANSHEE, 7);
        assertTrue(player.isSeriouslyDamaged());

        player.getPlayerBoard().resetDamageLine();
        player.getPlayerBoard().addDamage(Character.BANSHEE, 2);
        assertTrue(!player.isSeriouslyDamaged());
    }

    @Test
    public void testAddTarget() throws NoCardException, InvalidCardException, InvalidPositionException, InvalidCharacterException {
        int[] pos = {1, 0};

        AmmoCard card = deckAmmos.drawCard();
        ArrayList<Orientation> orient = new ArrayList<>();
        Platform start = new Platform(pos, true, card, Color.BLUE, orient);
        Player player = new Player("ciao", Character.BANSHEE, start);
        Player target = new Player("target", Character.DOZER, start);

        player.addTarget(target);

        assertTrue(player.getCurrentTargets().contains(target));
    }

    @Test
    public void testIsDeath() {
        try {
            Player player = new Player("name", Character.DOZER, null);
            player.getPlayerBoard().addDamage(Character.BANSHEE, 11);
            assertTrue(player.isDead());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testWasOverKilled() {
        try {
            Player player = new Player("name", Character.DOZER, null);
            player.getPlayerBoard().addDamage(Character.BANSHEE, 12);
            assertTrue(player.wasOverkilled());
        } catch (Exception e) {
            fail();
        }
    }

}
