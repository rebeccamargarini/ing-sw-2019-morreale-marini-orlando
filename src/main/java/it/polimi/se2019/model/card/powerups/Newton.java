package it.polimi.se2019.model.card.powerups;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.exceptions.InvalidCubeException;
import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public final class Newton extends PowerUpCard {


    public Newton(AmmoCube ammoCube, String name, String description, String img) throws InvalidNameException, InvalidCubeException {
        super(ammoCube, name, description, img);
        /*
        Action eff1 = c -> {
            c.askFor(c.getValidator().getValidTargets(this), "targets");
            Player target = null;
            try {
                target = c.getCurrentTargets().take();
            } catch (Exception e) {
                HandyFunctions.LOGGER.log(Level.WARNING, e.getMessage());
            }
            c.askFor(c.getGame().getGameField().getPlatformDir(target.getCurrentPlatform()), "positionForOther");
        };

        effects.add(eff1);*/
        stages.add(0);
    }

    /**
     * Return if the player in his current state can use the power up or not
     */
    public boolean isUsable(Player player) {
        return true;
    }

    /**
     * @return a collection of players that can be the target
     */
    public List<Player> getPossibleTargets(Controller c) {
        //TODO
        return new ArrayList<>();
    }
}