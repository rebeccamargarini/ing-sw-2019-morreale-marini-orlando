package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.network.message.to_client.ShowReconnectedGameBoard;


public class ReconnectedClientMessage extends ToServerMessage {
    public ReconnectedClientMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        Controller actor = Controller.getInstance();
        String user = (String) payload;
        String mychar = actor.getGame().getPlayer(user).getCharacter().name();
        actor.callView(new ShowReconnectedGameBoard(actor.getConfigMap(), actor.getGame().getLightVersion(), actor.findCharactersInGame(), actor.getGame().getPlayer(user).getCharacter().name()), user);
        actor.broadcastMessage(user + " reconnected!");
        actor.getGame().getPlayer(user).setConnected(true);
        actor.getPingsList().add(mychar);
        actor.getAlreadyNotified().remove(mychar);
        if (actor.getPingsList().size() == 2) {
            actor.getTurnController().enablePlayers(Game.getInstance().getPlayer(actor.getTurnController().getTurnUser()));
        }

    }
}
