package it.polimi.se2019.model.card.weapons;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class TestElectroscythe extends TestWeaponFather {
    private Electroscythe electroscythe;

    @Before
    public void initTest() throws InvalidImageException, InvalidNameException, InvalidCubeException, IOException, InvalidDeckException, InvalidCardException {
        super.initTest();
        electroscythe = new Electroscythe("electroscythe", "desc", "img", AmmoCube.BLUE, new AmmoCube[]{});
        for (Player player : game.getPlayers()) {
            player.setCurrentPlatform(game.getGameField().getPlatforms().get(2));
            player.getPlayerBoard().resetDamageLine();
        }
    }

    @Test
    public void testEffect1() {
        assertNull(electroscythe.getEffects().get(0).getPossibleTargets());
        electroscythe.activateEffect(0, null);
        for (Player player : game.getPlayers()) {
            if (player != currPlayer) {
                try {
                    player.getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter());
                } catch (Exception e) {
                    fail();
                }
            }
        }
    }

    @Test
    public void testEffect2() {
        assertNull(electroscythe.getEffects().get(1).getPossibleTargets());
        electroscythe.activateEffect(1, null);
        for (Player player : game.getPlayers()) {
            if (player != currPlayer) {
                try {
                    player.getPlayerBoard().getDamageLine().contains(currPlayer.getCharacter());
                } catch (Exception e) {
                    fail();
                }
            }
        }
    }

    @After
    public void finishTest(){
        super.finishTest();
    }
}