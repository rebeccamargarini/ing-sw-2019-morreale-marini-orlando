package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.utils.Deserializer;

import java.util.ArrayList;
import java.util.List;

public class BuyWithPowerupsMessage extends ToServerMessage {

    public BuyWithPowerupsMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        String hashPowerup = (String) payload;
        try {
                Controller.getInstance().getPlayerManager().convertPowerUpToAmmo(Deserializer.getPowerUp(Integer.parseInt(hashPowerup), sender));
        } catch (Exception e) {

        }

    }
}
