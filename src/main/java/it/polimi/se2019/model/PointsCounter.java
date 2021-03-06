package it.polimi.se2019.model;

import it.polimi.se2019.model.board.SkullsBoard;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.model.player.PlayerBoard;

import java.util.EnumMap;
import java.util.Map;


/**
 * Class used to calculate the amount of points of each player
 * @author Gabriel Raul Marini
 */
public final class PointsCounter {

    private PointsCounter() {
    }

    /**
     * @param player killed during the turn
     * @return a map containing the points assigned to each player
     */
    public static Map<Character, Integer> getPoints(Player player) {
        PlayerBoard board = player.getPlayerBoard();
        Map<Character, Integer> res = new EnumMap<>(Character.class);
        Map<Character, Integer> maxDamage = new EnumMap<>(Character.class);

        if (!player.getPlayerBoard().isReverted()) {
            Character firstDamage = board.getDamageLine().get(0);
            res.put(firstDamage, 1);
        }

        for (Character character : board.getDamageLine()) {
            if (maxDamage.containsKey(character))
                maxDamage.replace(character, maxDamage.get(character) + 1);
            else
                maxDamage.put(character, 1);
        }

        int i = 0;
        while (!maxDamage.isEmpty()) {
            Character currCharacter = null;
            int max = 0;

            for (Map.Entry<Character, Integer> entry : maxDamage.entrySet()) {
                if (entry.getValue() > max) {
                    currCharacter = entry.getKey();
                    max = entry.getValue();
                }
            }

            int pointsValue;

            if (player.getPlayerBoard().isReverted())
                pointsValue = PointsBattery.getPointsValue(3)[i];
            else
                pointsValue = PointsBattery.getPointsValue(player.getNumOfDeaths())[i];

            if (res.containsKey(currCharacter))
                res.replace(currCharacter, res.get(currCharacter) + pointsValue);
            else
                res.put(currCharacter, pointsValue);

            maxDamage.remove(currCharacter);
            i++;
        }

        return res;
    }

    /**
     * @param board containing all the kills
     * @return a map containing the points assigned to each player
     */
    public static Map<Character, Integer> getFinalScore(SkullsBoard board) {
        Map<Character, Integer> res = new EnumMap<>(Character.class);

        int i = 0;
        while (!board.getKillMarks().isEmpty()) {
            Character currCharacter = null;

            int max = 0;
            for (Character key : board.getKillMarks().keySet()) {
                if (board.getKillMarks().get(key) > max) {
                    currCharacter = key;
                    max = board.getKillMarks().get(key);
                }
            }

            res.put(currCharacter, PointsBattery.getFinalPointValue()[i]);
            board.getKillMarks().remove(currCharacter);
            i++;
        }

        return res;
    }
}