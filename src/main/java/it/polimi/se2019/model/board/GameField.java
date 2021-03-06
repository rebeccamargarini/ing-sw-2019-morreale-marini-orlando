package it.polimi.se2019.model.board;

import it.polimi.se2019.exceptions.*;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.enumeration.Orientation;
import it.polimi.se2019.utils.HandyFunctions;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.*;

/**
 * GameField is a matrix of Platform (3x4) in which characters can move.
 * It contains also the information about the rooms in an array.
 *
 * @author Federico Morreale
 */
public class GameField implements Serializable {

    protected WeaponCard[] initWeapons;
    private ArrayList<Room> rooms;
    private Platform[][] field;
    private SkullsBoard skullsBoard;

    /**
     * @param initWeapons an array of weapons ready to be grabbed, each weapon will be linked to its platform
     * @param skullsBoard the board that manages the killings
     * @param field       where the 10, 11 or 12 platforms are located
     * @throws InvalidFieldException             if there is more than 1 platform equal to null or the matrix is not 3x4
     * @throws InvalidAdjacentPlatformsException if the adjacency list has more than 2 nulls
     * @throws InvalidRoomException .
     * @throws InvalidDeckException .
     * @throws InvalidGenerationSpotException .
     */
    public GameField(Platform[][] field, WeaponCard[] initWeapons,
                     SkullsBoard skullsBoard) throws InvalidFieldException, InvalidRoomException,
            InvalidAdjacentPlatformsException, InvalidDeckException, InvalidGenerationSpotException {
        if (hasMoreThan2Nulls(field)) {
            throw new InvalidFieldException();
        }
        this.field = field;
        //build the adjacency list of every platform in the field
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                Platform p = field[i][j];
                if (p != null)
                    buildPlatformAdjMap(p, i, j);
            }
        }

        // we may now build the rooms
        this.rooms = new ArrayList<>();
        buildRooms();
        this.skullsBoard = skullsBoard;

        if (!areValidWeapons(initWeapons))
            throw new InvalidDeckException("something went wrong while building available weapons, please check again your arguments");
        this.initWeapons = initWeapons;
        //create the Map of weapons in each generation spot
        int index = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                Platform p = field[i][j];
                if (p != null && p.isGenerationSpot()) {
                    ArrayList<WeaponCard> arrWeapons = new ArrayList<>();
                    arrWeapons.add(initWeapons[index++]);
                    arrWeapons.add(initWeapons[index++]);
                    arrWeapons.add(initWeapons[index++]);
                    p.setWeapons(arrWeapons);
                }
            }
        }

    }

    /**
     * @return rooms in the gameField
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * @return the gameField
     */
    public Platform[][] getField() {
        return field;
    }

    public SkullsBoard getSkullsBoard() {
        return skullsBoard;
    }

    /**
     * @param position of one platform in the room
     * @return the Room in which the platform is located
     * @throws InvalidPositionException if position is out of the matrix
     */
    public Room getRoom(int[] position) throws InvalidPositionException {
        if (!HandyFunctions.isValidPosition(position))
            throw new InvalidPositionException("Invalid position");
        Platform p = field[position[0]][position[1]];
        for (Room r : rooms) {
            if (r.getPlatformsInRoom().contains(p)) return r;
        }
        return null;
    }

    /**
     * @param position of the platform
     * @return the Platform object having that position
     * @throws InvalidPositionException if position is out of the matrix
     */
    public Platform getPlatform(int[] position) throws InvalidPositionException {
        if (!HandyFunctions.isValidPosition(position))
            throw new InvalidPositionException("Invalid positions!");
        return field[position[0]][position[1]];
    }

    /**
     * @return true if the field has more than 2 nulls, if so, the field is not valid
     */
    private boolean hasMoreThan2Nulls(Platform[][] field) {
        int counter = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                Platform p = field[i][j];
                if (p == null) counter++;
            }
        }
        return counter > 2;
    }

    /**
     * @return true if the array of 9 weapons is valid
     */
    private boolean areValidWeapons(WeaponCard[] initWeapons) {
        if (initWeapons.length != 9) {
            return false;
        }
        for (int i = 0; i < initWeapons.length; i++) {
            if (initWeapons[i] == null) return false;
        }
        return true;
    }

    /**
     * @param platform to which build the adjacency list
     * @param row      of the current platform
     * @param column   of the current platform
     * @throws InvalidAdjacentPlatformsException is there are more than one nulls in the adjacency list
     *                                           build the adjacency list of each platform
     */
    private void buildPlatformAdjMap(Platform platform, int row, int column) throws InvalidAdjacentPlatformsException {

        EnumMap<Orientation, Platform> adjMap = new EnumMap<>(Orientation.class);
        if (row - 1 >= 0 && field[row - 1][column] != null && (platform.getPlatformColor().equals(field[row - 1][column].getPlatformColor()) || platform.getDoorLocation().contains(Orientation.UP)))
            adjMap.put(Orientation.UP, field[row - 1][column]);
        else adjMap.put(Orientation.UP, null);
        if (row + 1 < 3 && field[row + 1][column] != null && (platform.getPlatformColor().equals(field[row + 1][column].getPlatformColor()) || platform.getDoorLocation().contains(Orientation.DOWN)))
            adjMap.put(Orientation.DOWN, field[row + 1][column]);
        else adjMap.put(Orientation.DOWN, null);
        if (column - 1 >= 0 && field[row][column - 1] != null && (platform.getPlatformColor().equals(field[row][column - 1].getPlatformColor()) || platform.getDoorLocation().contains(Orientation.LEFT)))
            adjMap.put(Orientation.LEFT, field[row][column - 1]);
        else adjMap.put(Orientation.LEFT, null);
        if (column + 1 < 4 && field[row][column + 1] != null && (platform.getPlatformColor().equals(field[row][column + 1].getPlatformColor()) || platform.getDoorLocation().contains(Orientation.RIGHT)))
            adjMap.put(Orientation.RIGHT, field[row][column + 1]);
        else adjMap.put(Orientation.RIGHT, null);

        //check if it's a valid adjacency list
        int numOfNull = 0;
        for (Platform p : adjMap.values()) {
            if (p == null) numOfNull++;
        }
        if (numOfNull > 2)
            throw new InvalidAdjacentPlatformsException();

        platform.setAdjacentPlatforms(adjMap);
    }

    /**
     * it creates the array list of rooms
     * firstly it creates the arraylists of platform having the same color,
     * then those arraylists will be added to rooms arraylist
     * @throws InvalidRoomException .
     */
    private void buildRooms() throws InvalidRoomException {
        int numOfRooms;
        List<Color> listOfColors = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                Platform p = field[i][j];
                if (p != null) {
                    Color c = p.getPlatformColor();
                    if (!listOfColors.contains(c))
                        listOfColors.add(c);
                }
            }
        }
        numOfRooms = listOfColors.size();
        Color currentColor = null;
        for (int i = 0; i < numOfRooms; i++) {
            ArrayList<Platform> listOfPlat = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 4; k++) {
                    Platform p = field[j][k];
                    if (p != null) {
                        if (listOfPlat.isEmpty() && listOfColors.contains(p.getPlatformColor()))
                            currentColor = p.getPlatformColor();
                        if (listOfColors.contains(p.getPlatformColor()) && p.getPlatformColor() == currentColor) {
                            listOfPlat.add(p);
                        }
                    }
                }
            }
            Room r = new Room(listOfPlat, currentColor);
            listOfColors.remove(currentColor);
            this.rooms.add(r);
        }

    }

    /**
     * iterative DFS using adjacency list
     *
     * @param initPlat      is the root of the tree
     * @param numOfMaxSteps permitted from initPlat, depending on the state of the player
     * @return an arraylist of platforms in which the player can go from the initPlat,
     * given the numOfMaxSteps permitted
     */
    public List<Platform> getAvailablePlatforms(Platform initPlat, int numOfMaxSteps) {
        List<Platform> platsAvailable = new ArrayList<>();
        Deque<Platform> stack = new ArrayDeque<>();
        Deque<Integer> dist = new ArrayDeque<>();
        boolean[][] visitedPlats = new boolean[3][4];
        stack.add(initPlat);
        dist.add(0);
        while (!stack.isEmpty()) {
            Platform p = stack.pop();
            int distCorr = dist.pop();
            if (!visitedPlats[p.getPlatformPosition()[0]][p.getPlatformPosition()[1]]) {
                visitedPlats[p.getPlatformPosition()[0]][p.getPlatformPosition()[1]] = true;
                platsAvailable.add(p);
            }
            if (distCorr + 1 <= numOfMaxSteps) {
                for (Map.Entry<Orientation, Platform> entry : p.getAdjacentPlatforms().entrySet()) {
                    Platform pl = entry.getValue();
                    if (pl != null && !visitedPlats[pl.getPlatformPosition()[0]][pl.getPlatformPosition()[1]]) {
                        stack.push(pl);
                        dist.push(distCorr + 1);
                    }
                }
            }
        }
        return platsAvailable;
    }

    /**
     * @param platform in which calculate all the visible players
     * @return an arraylist of visible players from that platform
     */
    public List<Character> getVisiblePlayers(Platform platform) {
        List<Character> characterArrayList = new ArrayList<>();
        for (Platform p : platform.getPlatformRoom().getPlatformsInRoom()) {
            characterArrayList.addAll(p.getPlayersOnThePlatform());
        }
        for (Orientation or : platform.getDoorLocation()) {
            for (Platform p : platform.getAdjacentPlatform(or).getPlatformRoom().getPlatformsInRoom()) {
                characterArrayList.addAll(p.getPlayersOnThePlatform());
            }
        }
        return characterArrayList;
    }

    /**
     * @param platform in which calculate all the visible platform
     * @return an arraylist of visible platform from that platform
     */
    public List<Platform> getVisiblePlatforms(Platform platform) {
        List<Platform> platformsArrayList = new ArrayList<>();
        for (Platform p : platform.getPlatformRoom().getPlatformsInRoom()) {
            platformsArrayList.add(p);
        }
        for (Orientation or : platform.getDoorLocation()) {
            for (Platform p : platform.getAdjacentPlatform(or).getPlatformRoom().getPlatformsInRoom()) {
                platformsArrayList.add(p);
            }
        }
        return platformsArrayList;
    }

    /**
     * @return an arraylist of every platform in the field
     */
    public List<Platform> getPlatforms() {
        List<Platform> platformArrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (field[i][j] != null)
                    platformArrayList.add(field[i][j]);
            }
        }
        return platformArrayList;
    }

    /**
     * @param systemAddress of target platform
     * @return the reference to the wanted platform
     */
    public Platform getPlatform(String systemAddress) {
        for (Platform platform : getPlatforms())
            if (Integer.toString(System.identityHashCode(platform)).equals(systemAddress))
                return platform;
        return null;
    }

    /**
     * @param platform to get the light version
     * @return a string like 0,0
     */
    public String getPlatformPosLight(Platform platform) {
        if (platform != null)
            return platform.getPlatformPosition()[0] + "," + platform.getPlatformPosition()[1];
        return "null";
    }

    /**
     * @param curr platform of the target
     * @return the platforms in the directions selected of max distance from curr = 2, used by Newton
     */
    public List<Platform> getPlatformDir(Platform curr) {
        List<Platform> destinations = new ArrayList<>();
        int[] pos = curr.getPlatformPosition();
        int myX = pos[0];
        int myY = pos[1];
        destinations.addAll(getAvailablePlatforms(curr, 2));
        List<Platform> toDelete = new ArrayList<>();
        for (Platform platform : destinations) {
            int currX = platform.getPlatformPosition()[0];
            int currY = platform.getPlatformPosition()[1];
            if (myX != currX && myY != currY)
                toDelete.add(platform);
        }
        destinations.removeAll(toDelete);
        return destinations;
    }

}