package it.polimi.se2019.controller;

import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.enumeration.Orientation;
import it.polimi.se2019.model.player.Player;

import java.util.*;

/**
 * @author Federico Morreale
 */
public class HealthyValidator implements Validator {

    public HealthyValidator() {
    }

    /**
     * @param dir 
     * @return
     */
    public boolean isValidMove(ArrayList<Orientation> dir) {
        return false;
    }

    /**
     * @param target 
     * @return
     */
    public boolean isValidTarget(Player target) {
        return false;
    }

    /**
     * @param position 
     * @return
     */
    public boolean canGrab(Platform position) {
        return false;
    }

}