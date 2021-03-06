package it.polimi.se2019.network.server;

import it.polimi.se2019.Lobby;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.ControllerState;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.network.message.toclient.ToClientMessage;
import it.polimi.se2019.network.message.toclient.UsernameAlreadyInUseMessage;
import it.polimi.se2019.network.message.toserver.NewClientConnectedMessage;
import it.polimi.se2019.network.message.toserver.ReconnectedClientMessage;
import it.polimi.se2019.network.message.toserver.ToServerMessage;
import it.polimi.se2019.utils.CustomLogger;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.server.VirtualView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Socket implementation of the server
 *
 * @author Gabriel Raul Marini
 */
public class SocketServer implements Server {
    private Map<String, SpecificSocketServer> connections;
    private ServerSocket serverSocket;
    private Map<String, VirtualView> actors;
    private boolean available;
    private int port;

    public SocketServer(int port) {
        connections = new HashMap();
        this.port = port;
        actors = new HashMap<>();
        available = false;
    }

    public Map<String, SpecificSocketServer> getConnections() {
        return connections;
    }

    public Map<String, VirtualView> getActors() {
        return actors;
    }

    @Override
    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            HandyFunctions.LOGGER.log(Level.INFO, "Socket Server is ready");

            new Thread(() -> {
                while (true) {
                    try {
                        Socket socket = serverSocket.accept();
                        Thread.sleep(500);
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                        output.flush();
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        String user = (String) input.readObject();
                        if (Controller.getInstance().getState().equals(ControllerState.SETUP) && Controller.getInstance().getUserView().containsKey(user)) {
                            output.writeObject(new UsernameAlreadyInUseMessage(user));
                            output.reset();
                        }
                        else {
                            boolean newConnection = true;
                            VirtualView virtualView;
                            if (!Controller.getInstance().getUserView().containsKey(user)) {
                                virtualView = new VirtualView(user);
                                actors.put(user, virtualView);
                                Lobby.addUser(user);
                            } else if (actors.containsKey(user)) {
                                virtualView = actors.get(user);
                                Game.getInstance().addObserver(virtualView);
                                Game.getInstance().getPlayer(user).setConnected(true);
                                newConnection = false;
                            } else {
                                virtualView = Controller.getInstance().getUserView().get(user);
                                virtualView.addObserver(Controller.getInstance());
                                Game.getInstance().addObserver(virtualView);
                                Game.getInstance().getPlayer(user).setConnected(true);
                                Lobby.getRmiServer().getClientActor().remove(user);
                                Lobby.getRmiServer().getSkeletons().remove(user);
                                actors.put(user, virtualView);
                                newConnection = false;
                            }
                            SpecificSocketServer specificSocketServer = new SpecificSocketServer(this, socket, input, output, virtualView);
                            specificSocketServer.start();
                            connections.put(user, specificSocketServer);
                            HandyFunctions.LOGGER.log(Level.INFO, user + " connected to the socket server!");
                            virtualView.viewSetChanged();
                            if (newConnection) {
                                virtualView.notifyObservers(new NewClientConnectedMessage(user));
                                HandyFunctions.checkForAtLeast2Players(virtualView);
                            } else {
                                virtualView.notifyObservers(new ReconnectedClientMessage(user));
                            }
                        }
                    } catch (Exception e) {
                        CustomLogger.logException(this.getClass().getName(), e);
                    }
                }
            }).start();
        } catch (IOException ex) {
            HandyFunctions.LOGGER.log(Level.SEVERE, "Error starting socket server");
        }
    }

    @Override
    /**
     * Close the streams and the sockets
     */
    public void stop() {
        serverSocket = null;
        available = false;
    }

    @Override
    public void sendToClient(ToClientMessage msg, String user) {
        try {
            if (!connections.get(user).getSocket().isClosed()) {
                ObjectOutputStream outStream = connections.get(user).getOutput();
                outStream.writeObject(msg);
                outStream.flush();
                outStream.reset();
            }
        } catch (Exception e) {
            CustomLogger.logException(this.getClass().getName(), e);
        }
    }


    @Override
    public void registerClient(String host, int port, String username) {
        /*
        unused for socket server
         */
    }

    public void interpretMessage(ToServerMessage msg) {
        //virtual view associated to the right sender notifies the controller
        getVirtualView(msg.getSender()).viewSetChanged();
        getVirtualView(msg.getSender()).notifyObservers(msg);
    }

    @Override
    public boolean isConnected(String user) {
        return connections.containsKey(user);
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    public VirtualView getVirtualView(String user) {
        return actors.get(user);
    }

    @Override
    public boolean isUsed() {
        return false;
    }

    @Override
    public void setUsed(boolean isused) {
        //nothing
    }
}
