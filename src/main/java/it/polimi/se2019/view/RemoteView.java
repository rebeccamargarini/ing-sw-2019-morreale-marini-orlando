package it.polimi.se2019.view;

import java.rmi.Remote;

/**
 * @author Gabriel Raul Marini
 */
public abstract class RemoteView extends View{

    public RemoteView() {

    }

    public abstract void showMessage(String msg);

    public abstract void reportError();

    public void update() {
        //TODO
    }
}
