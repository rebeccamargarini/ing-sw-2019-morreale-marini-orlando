package it.polimi.se2019.controller.validator;

import it.polimi.se2019.model.enumeration.Action;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.exceptions.InvalidActionException;
import it.polimi.se2019.exceptions.InvalidGenerationSpotException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.powerups.PowerUpCard;
import it.polimi.se2019.model.card.weapons.effect.Effect;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.player.AmmoBox;
import it.polimi.se2019.model.player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gabriel Raul Marini
 */
public abstract class Validator implements Serializable {
    protected Controller father;

    /**
     * Instantiate a validator according to the match state
     */
    public Validator(Controller father) {
        this.father = father;
    }

    /**
     * @param c command received by the player
     * @return the list of platform destination the player can move to
     * @throws InvalidActionException if the player cannot move in the current health state with the
     *                                selected action
     */
    public abstract List<Platform> getValidMoves(Action c) throws InvalidActionException;

    /**
     * @return the list of weapons that can be grabbed by the player in his current position
     * according to the ammo qty in his AmmoBox
     */
    public List<WeaponCard> getGrabableWeapons() throws InvalidGenerationSpotException {
        Platform p = father.getPlayerManager().getCurrentPlayer().getCurrentPlatform();
        return getGrabableWeapons(p);
    }

    /**
     * @param p generation spot selected
     * @return the list of weapons that can be grabbed by the player in the specified platform
     */
    public List<WeaponCard> getGrabableWeapons(Platform p) throws InvalidGenerationSpotException {
        List<WeaponCard> res = new ArrayList<>();
        Player currPlayer = father.getPlayerManager().getCurrentPlayer();
        AmmoBox ammoBox = currPlayer.getPlayerBoard().getAmmoBox();

        if (!p.isGenerationSpot())
            throw new InvalidGenerationSpotException();

        for (WeaponCard weapon : p.getWeapons()) {
            if (ammoBox.hasAmmos(weapon.getExtraCost()))
                res.add(weapon);
        }

        return res;
    }

    /**
     * @return the list of weapons the current player can reload according to his ammos
     */
    public List<WeaponCard> getReloadableWeapons() {
        Player currPlayer = father.getPlayerManager().getCurrentPlayer();
        List<WeaponCard> res = currPlayer.getWeaponCards();
        AmmoBox ammoBox = currPlayer.getPlayerBoard().getAmmoBox();
        List<WeaponCard> toRemove = new ArrayList<>();

        for (WeaponCard weapon : res) {
            if (!ammoBox.hasAmmos(weapon.getTotalCost()) || weapon.isLoaded())
                toRemove.add(weapon);
        }

        res.removeAll(toRemove);
        return res;
    }

    /**
     * @return the list of PowerUp cards the player can use in the actual state of the game
     */
    public List<PowerUpCard> getUsablePowerUps() {
        List<PowerUpCard> res = new ArrayList<>();
        Player currPlayer = father.getPlayerManager().getCurrentPlayer();

        for (PowerUpCard powerUp : currPlayer.getPowerUpCards())
            if (powerUp.getName().equals("teletrasporto") || powerUp.getName().equals("raggio cinetico"))
                res.add(powerUp);
        return res;
    }

    /**
     * @return the weapons the player can use according to the current state of the game
     */
    public List<WeaponCard> getUsableWeapons() {
        Player currPlayer = father.getPlayerManager().getCurrentPlayer();
        List<WeaponCard> res = currPlayer.getWeaponCards();
        List<WeaponCard> toRemove = new ArrayList<>();

        for (WeaponCard weapon : res) {
            if (!weapon.isLoaded())
                toRemove.add(weapon);
            else {
                boolean usable = false;
                boolean[] usableEffect = weapon.getUsableEffects();

                for (int i = 0; i < usableEffect.length; i++) {
                    if (usableEffect[i]) {
                        Effect effect = weapon.getEffects().get(i);
                        effect.setupTargets();
                        if (effect.getPossibleTargets() == null || !effect.getPossibleTargets().isEmpty())
                            usable = true;
                    }
                }

                if (!usable)
                    toRemove.add(weapon);
            }
        }
        res.removeAll(toRemove);
        return res;
    }
}