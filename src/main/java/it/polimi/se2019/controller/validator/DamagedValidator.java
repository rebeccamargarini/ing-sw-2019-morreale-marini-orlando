package it.polimi.se2019.controller.validator;

import it.polimi.se2019.model.enumeration.Action;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.exceptions.InvalidActionException;
import it.polimi.se2019.model.board.GameField;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.player.Player;

import java.util.*;

/**
 * @author Gabriel Raul Marini
 */
public class DamagedValidator extends Validator {

    public DamagedValidator(Controller father) {
        super(father);
    }


    /**
     * @param c command received by the player
     * @return the list of platform destination the player can move to
     * @throws InvalidActionException if the player cannot move in the current health state with the
     * selected action
     */
    @Override
    public List<Platform> getValidMoves(Action c) throws InvalidActionException {
        List<Platform> res;
        GameField gameField = father.getGame().getGameField();
        Player currentPlayer = father.getPlayerManager().getCurrentPlayer();

        if (c == Action.MOVE)
            res = gameField.getAvailablePlatforms(currentPlayer.getCurrentPlatform(), 3);
        else if (c == Action.GRAB)
            res = gameField.getAvailablePlatforms(currentPlayer.getCurrentPlatform(), 2);
        else
            throw new InvalidActionException("Cannot move the player in this mode with the action passed!");

        return res;
    }
}