package it.polimi.se2019.network.client;

import it.polimi.se2019.network.message.Message;
import it.polimi.se2019.network.server.Server;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.view.RemoteView;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * RMI implementation of remote client
 *
 * @author Gabriel Raul Marini
 */
public class RMIClient implements Client {
    private Server stub;
    private RemoteView actor;
    private boolean connected;
    private int port;
    private Registry registry;

    public RMIClient(RemoteView actor, int port) {
        this.actor = actor;
        this.port = port;

        try {
            registry = LocateRegistry.createRegistry(port);
        } catch (RemoteException e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }

        connected = false;
    }

    /**
     * Connect to the remote object on the server and register itself as skeleton calling
     * remote method registerClient() of the RMIServer
     *
     * @param host address of the server
     * @param port to bind in order to retrieve the stub
     */
    public void connect(String host, int port, String user) {
        try {
            Registry remoteRegistry = LocateRegistry.getRegistry(host, port);
            stub = (Server) remoteRegistry.lookup("FakeView");
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }

        exportRemoteObject();

        try {
            stub.registerClient(InetAddress.getLocalHost().getHostAddress(), this.port, user);
            HandyFunctions.LOGGER.log(Level.INFO, "Client registered itself to the server!");
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }

        connected = true;
        HandyFunctions.LOGGER.log(Level.INFO, "Client is connected!");
    }

    /**
     * Export the remote reference of RemoteView used to call his methods
     */
    private void exportRemoteObject() {
        try {
            Client skeleton = (Client) UnicastRemoteObject.exportObject(this, port);
            registry.bind("RemoteView", skeleton);
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
    }

    public void disconnect() {
        stub = null;
        try {
            registry.unbind("RemoteView");
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
        connected = false;
    }

    /**
     * @return if the client is connected to the server stub
     */
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void interpretMessage(Message msg) {
        msg.performAction(actor);
    }

    @Override
    public void callServer(Message msg) {
        try {
            stub.interpretMessage(msg);
        } catch (Exception e) {
            HandyFunctions.LOGGER.log(Level.INFO, e.toString());
        }
    }

    public Server getStub(){
        return stub;
    }

}
