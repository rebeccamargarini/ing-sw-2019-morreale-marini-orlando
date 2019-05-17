package it.polimi.se2019.view.server;


import it.polimi.se2019.Lobby;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.network.message.to_client.ToClientMessage;
import it.polimi.se2019.network.server.Server;
import it.polimi.se2019.view.State;
import it.polimi.se2019.view.View;

import java.util.List;
import java.util.Observable;


public class VirtualView extends View {
    private Game game;
    private Server virtualServer;
    private String user;

    /**
     * @param virtualServer either RMIServer or SocketServer
     * @param user
     */
    public VirtualView(Server virtualServer, String user) {
        this.virtualServer = virtualServer;
        this.user = user;
        this.game = Game.getInstance();
        //virtual view(this) observs model(Game)
        game.addObserver(this);
        //controller observs (this)
        this.addObserver(Controller.getInstance());
        Controller.getInstance().addVirtualView(this, user);
    }

    public String getUser() {
        return user;
    }

    /**
     * @param game    who is being observed by (this)
     * @param message sent by the model(game)
     */
    @Override
    public void update(Observable game, Object message) {
        //callView(new GameChangedMessage(message))
        updateUsers((ToClientMessage) message);
    }

    @Override
    public void start() {

    }

    public void startGame() {

    }

    @Override
    public void setCommunicationType() {
        //TODO
    }

    @Override
    public void startConnection() {
        //TODO
    }

    @Override
    public void setUserName() {
        //TODO
    }

    @Override
    public void waitGameStart() {
        //TODO
    }

    public void getCurrentPlayer() {
        //TODO
    }

    @Override
    public void setState(State newState) {
        //TODO
    }

    /**
     * @param targets players to show as targets
     */
    public void lightPlayers(List<Player> targets) {
        //TODO
    }

    /**
     * Common method across RMI and Socket to send requests to client
     *
     * @param msg  to the destination client
     * @param user recipient of the message
     */
    public void callView(ToClientMessage msg, String user) {
        if (Lobby.getRmiServer().isConnected(user))
            Lobby.getRmiServer().sendToClient(msg, user);
        if (Lobby.getSocketServer().isConnected(user))
            Lobby.getSocketServer().sendToClient(msg, user);
    }

    /**
     * update all clients connected
     *
     * @param message to be sent
     */
    private void updateUsers(ToClientMessage message) {
        for (String user : Controller.getInstance().getTurnController().getUsers()) {
            callView(message, user);
        }
    }

}
