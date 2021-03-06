package it.polimi.se2019.view.client.cli;

import it.polimi.se2019.exceptions.NoInputException;
import it.polimi.se2019.model.rep.AmmoRep;
import it.polimi.se2019.model.rep.CardRep;
import it.polimi.se2019.model.rep.LightGameVersion;
import it.polimi.se2019.network.client.RMIClient;
import it.polimi.se2019.network.client.SocketClient;
import it.polimi.se2019.network.message.toserver.*;
import it.polimi.se2019.utils.CustomLogger;
import it.polimi.se2019.utils.HandyFunctions;
import it.polimi.se2019.utils.TimerTurn;
import it.polimi.se2019.view.client.RemoteView;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.logging.Level;

/**
 * Class that manages the interaction with the controller through a CLI view
 * @author Simone Orlando
 */
public class CLI extends RemoteView {

    private CliReader reader;
    private int connectionChosen;
    private String ip;
    private int mapChosen = 0;
    private int timeLeftForMaps = 0;
    private int timeLeftForChar = 0;
    private int[] vote;
    private boolean firstTime = true;
    private ArrayList<String> charChosen;
    private int myChar = 0;
    private String myCharEnumString;
    private String chosenBoard;
    private int[][] map;
    private boolean isAsking;
    private LightGameVersion lightGameVersion;
    private boolean[] actives;
    private String lastMsg;
    private boolean powerUpChosen;
    private CardRep p1;
    private CardRep p2;
    private boolean firstCallChoosePU;
    private TimerTurn timerTurn;
    private List<CardRep> powerUps;
    private int actionType;
    private int chosingmap;
    private int inputSeconds;
    private int actionState;
    private int toReborn;
    private int timeLeft;
    private boolean callPowerUpChosen;
    private boolean myTurn;

    public CLI() {
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            HandyFunctions.LOGGER.log(Level.WARNING, e.toString());
        }
        reader = new CliReader(10);
        vote = new int[4];
        vote[0] = 0;
        vote[1] = 0;
        vote[2] = 0;
        vote[3] = 0;
        charChosen = new ArrayList<>();
        map = new int[3][4];
        isAsking = false;
        actives = new boolean[6];
        for (int i = 0; i < 6; i++)
            actives[i] = false;
        lastMsg = "";
        powerUpChosen = false;
        p1 = null;
        p2 = null;
        firstCallChoosePU = true;
        actionType = 0;
        chosingmap = 0;

        inputSeconds = 10;
        toReborn = 0;
        timeLeft = 0;
        callPowerUpChosen = false;
        myTurn = false;
    }

    @Override
    public void updateAll(LightGameVersion lightGameVersion) {
        this.lightGameVersion = lightGameVersion;
        actionType = lightGameVersion.getPlayerBoardRep().get(myCharEnumString).getActionType();
        if(toReborn == 0 && !callPowerUpChosen) {


            CliSetUp.clear();
            CliSetUp.cursorToHome();
            CliPrinter.welcomeMessage();
            CliSetUp.cursorLeft(109);
            CliSetUp.cursorDown(2);
            CliPrinter.printPlatformWeapons(lightGameVersion);
            CliSetUp.cursorRight(38);
            CliSetUp.cursorUp(23);
            CliPrinter.printMap(lightGameVersion, chosenBoard);
            CliSetUp.cursorDown(9);
            CliSetUp.cursorLeft(106);
            if ((powerUpChosen || p1 == null || p2 == null)) {
                if (actionType == 0)
                    CliPrinter.standardActionsMessage();
                else if (actionType == 1) {
                    CliPrinter.actionMessage1();
                }
                else if (actionType == 2) {
                    CliPrinter.actionMessage2();
                }
                else if (actionType == 3) {
                    CliPrinter.actionMessage3();
                }
                else {
                    CliPrinter.actionMessage4();
                }
                CliSetUp.savePosition();
                CliSetUp.cursorLeft(99);
                CliPrinter.boxMessage(null);
                CliSetUp.restorePosition();
            } else {
                CliSetUp.cursorUp(1);
                showChoosePowerup(powerUps);
                CliSetUp.cursorRight(9);
                CliSetUp.cursorDown(10);
            }

            CliSetUp.cursorUp(34);
            CliSetUp.cursorRight(104);
            CliPrinter.drawPlayersInfoBox(lightGameVersion, myCharEnumString);
            CliSetUp.cursorRight(59);
            CliSetUp.cursorUp(3);
            CliPrinter.drawGameInfoBox(lightGameVersion);
            CliSetUp.cursorDown(20);
            CliSetUp.cursorLeft(106);
            CliSetUp.cursorLeft(63);
            CliSetUp.cursorDown(9);
            CliPrinter.boxMessage(lastMsg);
            CliSetUp.cursorUp(11);
            CliSetUp.cursorRight(10);
            CliPrinter.stamp("Time left: " + timeLeft, CliColor.TEXTGREEN);
            CliPrinter.stamp(" (<8> update timer)");
        }
    }

    /**
     * Enable the input stream in CLI to get the action chosen
     */
    private void getActionInput() {

        if(actionType == 0 || actionType == 1 || actionType == 2) {
            if (!isAsking) {
                new Thread(() -> {
                    isAsking = true;
                    Console c = System.console();
                    int choise;
                    choise = Character.getNumericValue((c.readPassword())[0]);
                    isAsking = false;
                    if (choise == 1 && actives[0])
                        iWantToMove();
                    else if (choise == 2 && actives[1])
                        iWantToGrab();
                    else if (choise == 3 && actives[2])
                        iWantToShoot();
                    else if (choise == 4 && actives[3])
                        iWantToReload();
                    else if (choise == 5 && actives[4])
                        iWantToUsePowerUp();
                    else if (choise == 6 && actives[5])
                        endTurn();
                    else if (choise == 7)
                        iWantToConvertPowerUp();
                    else {
                        updateAll(lightGameVersion);
                        getActionInput();
                    }
                }).start();
            }
        }
        else {
            if (actionType == 3) {
                if (!isAsking) {
                    new Thread(() -> {
                        isAsking = true;
                        Console c = System.console();
                        int choise;
                        choise = Character.getNumericValue((c.readPassword())[0]);
                        isAsking = false;
                        if (choise == 1 && actives[0])
                            iWantToMove();
                        else if (choise == 2 && actives[1])
                            iWantToGrab();
                        else if (choise == 3 && actives[2])
                            iWantToShoot();
                        else if (choise == 4 && actives[3])
                            iWantToReload();
                        else if (choise == 5 && actives[4])
                            iWantToUsePowerUp();
                        else if (choise == 6 && actives[5])
                            endTurn();
                        else if (choise == 7)
                            iWantToConvertPowerUp();
                        else {
                            updateAll(lightGameVersion);
                            getActionInput();
                        }
                    }).start();
                }
            }
            else {
                if (!isAsking) {
                    new Thread(() -> {
                        isAsking = true;
                        Console c = System.console();
                        int choise;
                        choise = Character.getNumericValue((c.readPassword())[0]);
                        isAsking = false;
                        if (choise == 1 && actives[0])
                            iWantToMove();
                        else if (choise == 2 && actives[1])
                            iWantToGrab();
                        else if (choise == 4 && actives[2])
                            iWantToReload();
                        else if (choise == 5 && actives[3])
                            iWantToUsePowerUp();
                        else if (choise == 6 && actives[4])
                            endTurn();
                        else if (choise == 7)
                            iWantToConvertPowerUp();
                        else {
                            updateAll(lightGameVersion);
                            getActionInput();
                        }
                    }).start();
                }
            }
        }
    }

    /**
     * Check if is your turn
     * @return true if is the player's turn
     */
    private boolean isMyTurn() {
        for (int i = 0; i < 6; i++) {
            if (actives[i])
                return true;
        }
        return false;
    }

    /**
     * Send to server the action1 message
     */
    private void iWantToMove() {
        actionState = 0;
        PerformActionMessage message = new PerformActionMessage("action1");
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    /**
     * Send to server the action2 message
     */
    private void iWantToGrab() {
        actionState = 0;
        isAsking = true;
        PerformActionMessage message = new PerformActionMessage("action2");
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    /**
     * Send to server the action3 message
     */
    private void iWantToShoot() {
        actionState = 1;
        isAsking = true;
        PerformActionMessage message = new PerformActionMessage("action3");
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    /**
     * Send to server the action4 message
     */
    private void iWantToReload() {
        actionState = 1;
        isAsking = true;
        PerformActionMessage message = new PerformActionMessage("action4");
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    /**
     * Send to server the action5 message
     */
    private void iWantToUsePowerUp() {
        updateAll(lightGameVersion);
        actionState = 1;
        isAsking = true;
        PerformActionMessage message = new PerformActionMessage("action5");
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
    }

    /**
     * Send to server the endTurn message
     */
    public void endTurn() {
        updateAll(lightGameVersion);
        actionState = 0;
        EndTurnMessage message = new EndTurnMessage(null);
        message.setSender(userName);
        viewSetChanged();
        notifyObservers(message);
        updateAll(lightGameVersion);
        myTurn = false;
    }

    /**
     * Send to server the BuyWithPowerups message
     */
    private void iWantToConvertPowerUp() {

        CliSetUp.savePosition();
        isAsking = true;
        int returnValue = CliPrinter.convertPowerUpMessage(lightGameVersion,myCharEnumString);
        if(returnValue == -1) {
            isAsking = false;
            updateAll(lightGameVersion);
            getActionInput();
            return;
        }
        new Thread(() -> {
            int choise;
            Scanner s = new Scanner(System.in);
            try {
                choise = s.nextInt();
                Map<String, List<CardRep>> playerPowerUps = lightGameVersion.getPlayerPowerups();
                List<CardRep> myPowerUps = playerPowerUps.get(myCharEnumString);
                if (choise < myPowerUps.size()) {
                    int hash = myPowerUps.get(choise).getId();
                    BuyWithPowerupsMessage message = new BuyWithPowerupsMessage(Integer.toString(hash));
                    notifyController(message);
                    isAsking = false;
                    CliSetUp.restorePosition();
                    updateAll(lightGameVersion);
                    showActionMenu();
                } else {
                    updateAll(lightGameVersion);
                    CliPrinter.stamp("\n");
                    iWantToConvertPowerUp();
                }
            }
            catch (InputMismatchException e) {
                updateAll(lightGameVersion);
                CliPrinter.stamp("\n");
                iWantToConvertPowerUp();
            }
        }).start();
    }

    @Override
    public void showChoosePowerup(List<CardRep> cards) {
        callPowerUpChosen = true;

        CliSetUp.savePosition();
        powerUps = cards;
        if (!powerUpChosen) {
            this.p1 = cards.get(0);
            this.p2 = cards.get(1);
            CliPrinter.stamp("\n");
            CliPrinter.choosePowerUpMessage(p1, p2);
            CliSetUp.cursorRight(9);
            CliPrinter.boxMessage(null);
            CliSetUp.cursorUp(10);
            CliSetUp.cursorLeft(10);
            if (firstCallChoosePU) {
                firstCallChoosePU = false;
                new Thread(() -> {
                    int choosenPowerUp;
                    Console c = System.console();
                    choosenPowerUp = Character.getNumericValue((c.readPassword())[0]);
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    if (choosenPowerUp == 1) {
                        arrayList.add(p1.getId());
                        arrayList.add(p2.getId());
                    } else {
                        arrayList.add(p2.getId());
                        arrayList.add(p1.getId());
                    }
                    SendInitPowerUpMessage message = new SendInitPowerUpMessage(arrayList);
                    message.setSender(userName);
                    viewSetChanged();
                    notifyObservers(message);
                    CliSetUp.restorePosition();
                    callPowerUpChosen = false;
                    powerUpChosen = true;

                }).start();
            }
        }
        else {
            toReborn = 1;
            CliPrinter.stamp("\n");
            CliPrinter.choosePowerUpMessage2(cards);
            new Thread(() -> {
                int choosenPowerUp;
                Scanner s = new Scanner(System.in);
                try {
                    choosenPowerUp = s.nextInt();
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    if (choosenPowerUp < cards.size() && choosenPowerUp >= 0) {
                        arrayList.add(cards.get(choosenPowerUp).getId());
                        arrayList.add(cards.get(choosenPowerUp).getId());
                        SendInitPowerUpMessage message = new SendInitPowerUpMessage(arrayList);
                        message.setSender(userName);
                        viewSetChanged();
                        notifyObservers(message);
                        callPowerUpChosen = false;
                        toReborn = 0;

                    } else {
                        updateAll(lightGameVersion);
                        showChoosePowerup(cards);
                    }
                }
                catch (InputMismatchException e) {
                    updateAll(lightGameVersion);
                    CliPrinter.stamp("\n");
                    showChoosePowerup(cards);
                }
            }).start();
        }
    }

    @Override
    public void showGameBoard(List<AmmoRep> ammoReps, Map<String, List<CardRep>> posWeapons, List<String> arrChars) {
        if (powerUpChosen) {
            updateAll(lightGameVersion);
            return;
        }
        CliPrinter.reset();
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        CliPrinter.welcomeMessage();
        HandyFunctions.printConsole("\n\n");
        if (chosenBoard.equals("1"))
            CliPrinter.printMap1(map, ammoReps, posWeapons);
        else if (chosenBoard.equals("2"))
            CliPrinter.printMap2(map, ammoReps, posWeapons);
        else if (chosenBoard.equals("3"))
            CliPrinter.printMap3(map, ammoReps, posWeapons);
        else
            CliPrinter.printMap4(map, ammoReps, posWeapons);
    }

    @Override
    public void updateVotesCharacterChosen(String c) {
        if (!c.equals(myCharEnumString))
            charChosen.add(c);
        showChooseCharacter(chosenBoard);
    }

    @Override
    public void updateTimerCharacter(int count) {
        timeLeftForChar = count;
        inputSeconds = count;
        showChooseCharacter(chosenBoard);
    }

    @Override
    public void showChooseCharacter(String config) {
        CliPrinter.reset();
        chosenBoard = config;
        if (!firstTime) {
            new Thread(() -> {
                CliReader reader2 = new CliReader(inputSeconds);
                int temp;
                try {
                    temp = reader2.getTimedInt();
                    if (temp == 1) {
                        myCharEnumString = "BANSHEE";
                    } else if (temp == 2) {
                        myCharEnumString = "SPROG";
                    } else if (temp == 3) {
                        myCharEnumString = "DOZER";
                    } else if (temp == 4) {
                        myCharEnumString = "VIOLET";
                    } else {
                        myCharEnumString = "DISTRUCTOR";
                    }
                    if (!charChosen.contains(myCharEnumString)) {
                        if(temp >= 6)
                            temp = 5;
                        myChar = temp;
                        SendCharacterChosenMessage message = new SendCharacterChosenMessage(myCharEnumString);
                        message.setSender(userName);
                        viewSetChanged();
                        notifyObservers(message);
                    }
                    else {
                        firstTime = false;
                        showChooseCharacter(config);
                    }
                } catch (NoInputException | IOException e) {
                    CliPrinter.stamp("");
                }
            }).start();
            firstTime = true;
        }
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        CliPrinter.welcomeMessage();
        HandyFunctions.printConsole("\n\n");
        CliPrinter.possibleCharMessage(timeLeftForChar, charChosen, myChar);
    }

    @Override
    public void updateTimerMap(int count) {
        timeLeftForMaps = count;
        showChooseMap();
    }

    @Override
    public void updateVotesMapChosen(Map<Integer, Integer> map) {
        for (int i = 1; i <= 4; i++) {
            vote[i - 1] = map.get(i);
        }
        showChooseMap();
    }

    @Override
    public void showChooseMap() {
        chosingmap = 1;
        CliPrinter.reset();
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        CliPrinter.welcomeMessage();
        HandyFunctions.printConsole("\n\n");
        CliPrinter.possibleMapsMessage(timeLeftForMaps, vote);
        if (firstTime) {
            new Thread(() -> {
                CliReader reader1 = new CliReader(inputSeconds);
                try {
                    mapChosen = reader1.getTimedInt();
                    if (mapChosen == 1 || mapChosen == 2 || mapChosen == 3 || mapChosen == 4) {
                        SendMapChosenMessage message = new SendMapChosenMessage(mapChosen);
                        message.setSender(userName);
                        viewSetChanged();
                        notifyObservers(message);
                    } else {
                        mapChosen = 1;
                        SendMapChosenMessage message = new SendMapChosenMessage(mapChosen);
                        message.setSender(userName);
                        viewSetChanged();
                        notifyObservers(message);
                    }
                } catch (NoInputException | IOException e) {
                    CliPrinter.stamp("");
                }
            }).start();
            firstTime = false;
        }
    }

    @Override
    public void updateTimerLobby(int count) {
        CliPrinter.timerMessage(count);
    }

    @Override
    public void start() {
        CliPrinter.welcomeMessage();
        setCommunicationType();
        setUserName();
        waitGameStart();
        startConnection();
    }

    @Override
    public void updatePlayersOnWaitingList(List<String> users) {
        if(chosingmap == 1)
            return;
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        CliPrinter.welcomeMessage();
        HandyFunctions.printConsole("\n\n");
        CliPrinter.boxLobbyMessage(users);
    }

    @Override
    public void startGame() {
        HandyFunctions.printConsole("The game is started!\n");
    }

    @Override
    public void setCommunicationType() {
        HandyFunctions.printConsole("\n\n");
        CliPrinter.boxConnectionMessage();
        connectionChosen = reader.getInt();
        if (connectionChosen != 1 && connectionChosen != 2)
            connectionChosen = 1;
    }

    @Override
    public void startConnection() {
        final int SOCKET = 1;
        if (connectionChosen == SOCKET) {
            SocketClient client = new SocketClient(this, userName);
            client.connect(ip, HandyFunctions.parserClientSettings.getSocketServerPort());
            this.addObserver(client);
        } else {

            RMIClient client = new RMIClient(this, HandyFunctions.randomIntegerBetWeen(1500, 2000), userName);
            client.connect(ip, HandyFunctions.parserClientSettings.getRmiServerPort());
            this.addObserver(client);
        }
    }

    @Override
    public void setUserName() {
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        CliPrinter.welcomeMessage();
        HandyFunctions.printConsole("\n\n");
        CliPrinter.boxUsernameMessage();
        userName = reader.getString();
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        CliPrinter.welcomeMessage();
        HandyFunctions.printConsole("\n\n");
        CliPrinter.boxIpMessage(userName);
        ip = reader.getString();
    }

    @Override
    public void waitGameStart() {
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        CliPrinter.welcomeMessage();
        HandyFunctions.printConsole("\n\n");
        CliPrinter.boxWaitingMessage();
    }

    @Override
    public void update(Observable obs, Object obj) {
        /*
            Not used in CLI.
         */
    }

    @Override
    public void lightWeapons(List<String> weapons) {
        if(actionState == 0)
            CliPrinter.stamp("\n");

        CliSetUp.savePosition();
        if(weapons.isEmpty())
            return;
        Map<Integer, Integer> hashes;
        hashes = CliPrinter.printPossibleWeapon(lightGameVersion, weapons);
        new Thread(() -> {
            int choise;
            Scanner s = new Scanner(System.in);
            try {
                choise = s.nextInt();
                if (choise < weapons.size() && choise >= 0) {
                    ChosenWeaponMessage message = new ChosenWeaponMessage(Integer.toString(hashes.get(choise)));
                    notifyController(message);
                    isAsking = false;
                    CliSetUp.restorePosition();
                    updateAll(lightGameVersion);
                } else {
                    updateAll(lightGameVersion);
                    lightWeapons(weapons);
                }
            }
            catch (InputMismatchException e) {
                updateAll(lightGameVersion);
                lightWeapons(weapons);
            }
        }).start();
    }

    @Override
    public void lightPlatforms(List<String> platforms) {

        CliSetUp.savePosition();
        if(platforms.isEmpty())
            return;
        CliPrinter.printPossiblePlatform(platforms);
        new Thread(() -> {
            String platform;
            Console c = System.console();
            platform = c.readLine();
            if (platforms.contains(platform)) {
                MoveCurrPlayerMessage message = new MoveCurrPlayerMessage(platform);
                notifyController(message);
                CliSetUp.restorePosition();
                if (!platform.equals("0,2") && !platform.equals("1,0") && !platform.equals("2,3")) {
                    isAsking = false;
                    updateAll(lightGameVersion);
                    CliPrinter.stamp("\n");
                }
            } else {
                updateAll(lightGameVersion);
                CliPrinter.stamp("\n");
                lightPlatforms(platforms);
            }
        }).start();
    }

    @Override
    public void setRandomChar(String randomChar) {
        myCharEnumString = randomChar;
    }

    @Override
    public void setValidActions(boolean[] actives) {
        this.actives = actives;
        isAsking = false;
    }

    @Override
    public void showMessage(String msg) {
        lastMsg = msg;
    }

    @Override
    public void switchWeapon() {

        CliPrinter.stamp("\n");
        CliSetUp.savePosition();
        CliPrinter.discartWeaponMessage(lightGameVersion, myCharEnumString);
        new Thread(() -> {
            Scanner s = new Scanner(System.in);
            int choice;
            int idCard;
            try {
                choice = s.nextInt();
                CliSetUp.restorePosition();
                Map<String, List<CardRep>> playerWeapons = lightGameVersion.getPlayerWeapons();
                List<CardRep> myWeapons = playerWeapons.get(myCharEnumString);

                if (choice == 0 || choice == 1 || choice == 2) {
                    idCard = myWeapons.get(choice).getId();
                } else {
                    idCard = myWeapons.get(2).getId();
                }
                DiscardWeaponMessage message = new DiscardWeaponMessage(Integer.toString(idCard));
                notifyController(message);
                CliSetUp.restorePosition();
                updateAll(lightGameVersion);
            }
            catch (InputMismatchException e) {
                updateAll(lightGameVersion);
                CliPrinter.stamp("\n");
                switchWeapon();
            }
        }).start();
    }

    @Override
    public void showTargets(List<String> targets) {

        updateAll(lightGameVersion);
        CliPrinter.stamp("\n");
        CliSetUp.savePosition();
        if(targets.isEmpty())
            return;
        CliPrinter.showTargetMessage(lightGameVersion, targets);
        List<String> toSend = new ArrayList<>();
        new Thread(() -> {
            boolean isOk = true;
            String choise;
            Scanner s = new Scanner(System.in);
            choise = s.next();

            for (int i = 0; i < choise.length(); i++) {
                if (choise.charAt(i) != ',' && Character.getNumericValue(choise.charAt(i)) != -1 && Character.getNumericValue(choise.charAt(i)) < targets.size()) {
                    toSend.add(targets.get(Character.getNumericValue(choise.charAt(i))));
                }
            }
            if (toSend.isEmpty())
                isOk = false;
            else {
                for (String str : toSend) {
                    if (!targets.contains(str))
                        isOk = false;

                }
            }
            if (isOk) {
                SendTargetsMessage message = new SendTargetsMessage(toSend);
                notifyController(message);
                isAsking = false;
                CliSetUp.restorePosition();
                updateAll(lightGameVersion);
                CliPrinter.stamp("\n");
            } else {
                updateAll(lightGameVersion);
                showTargets(targets);
            }
        }).start();

    }

    @Override
    public void enlightenEffects(List<Integer> effects) {

        updateAll(lightGameVersion);
        CliPrinter.stamp("\n");
        CliSetUp.savePosition();
        if(effects.isEmpty())
            return;
        CliPrinter.enlightenEffectsMessage(effects);
        new Thread(() -> {
            int choise;
            Scanner s = new Scanner(System.in);
            try {
                choise = s.nextInt();
                if (!effects.contains(choise))
                    choise = effects.get(0);
                ChosenEffectMessage message = new ChosenEffectMessage(choise);
                notifyController(message);
                CliSetUp.restorePosition();
                updateAll(lightGameVersion);
                CliPrinter.stamp("\n");
                actionState = 0;
            }
            catch (InputMismatchException e) {
                updateAll(lightGameVersion);
                enlightenEffects(effects);
            }
        }).start();
    }

    @Override
    public void showBinaryOption(String message) {

        CliPrinter.stamp("\n");
        CliSetUp.savePosition();
        CliPrinter.binaryOptionMessage(message);
        new Thread(() -> {
            char choise;
            Scanner s = new Scanner(System.in);
            choise = s.next().charAt(0);
            boolean toSend;
            if (choise == 'y')
                toSend = true;
            else
                toSend = false;
            ResponseToBinaryOption msg = new ResponseToBinaryOption(toSend);
            notifyController(msg);
            if (message.equals("Do you want to move the target 1 square away?")) {
                CliSetUp.cursorUp(5);
            }
            if (message.equals("Do you want to use another effect?")) {
                actionState = 1;
            }
            CliSetUp.restorePosition();
            updateAll(lightGameVersion);
            CliPrinter.stamp("\n");
        }).start();
    }

    @Override
    public void showReconnectedGameBoard(int configMap, LightGameVersion lightGameVersion, List<String> charsInGame, String myChar) {
        chosenBoard = Integer.toString(configMap);
        myCharEnumString = myChar;
        updateAll(lightGameVersion);
        toReborn = 0;
        powerUpChosen = true;
    }


    @Override
    public void startTimerTurn(int count, String currPlayer) {
        try {
            if (timerTurn != null) timerTurn.interrupt();
        } catch (Exception ex) {
            CustomLogger.logException(this.getClass().getName(), ex);
        }
        timerTurn = new TimerTurn(count, this, currPlayer);
        timerTurn.start();
    }

    @Override
    public void updateTimerTurn(int seconds, String curr) {
        timeLeft = seconds;
        if(!myTurn && powerUpChosen) {
            try {
                updateAll(lightGameVersion);
            }
            catch (NullPointerException e) {
                CliPrinter.stamp("");
            }
        }
        if (seconds == 0 && isMyTurn()) {
            System.exit(0);
        }
    }

    @Override
    public void showReloadableWeapons(List<String> weapons) {
        if(weapons.isEmpty()) {
            updateAll(lightGameVersion);
            getActionInput();
        }

        if(actionState == 0)
            CliPrinter.stamp("\n");
        CliSetUp.savePosition();
        if(weapons.isEmpty())
            return;
        CliPrinter.reloadWeaponMessage(lightGameVersion, myCharEnumString, weapons);
        new Thread(() -> {
            try {
                ReloadWeaponsMessage message = new ReloadWeaponsMessage(Integer.toString(showWeapons(weapons)));
                notifyController(message);
                isAsking = false;
                CliSetUp.restorePosition();
                actionState = 0;
                updateAll(lightGameVersion);
            }
            catch (InputMismatchException e) {
                updateAll(lightGameVersion);
                CliPrinter.stamp("\n");
                showReloadableWeapons(weapons);
            }
        }).start();
    }

    @Override
    public void showUsableWeapons(List<String> weapons) {

        if (actionState == 0)
            CliPrinter.stamp("\n");
        CliSetUp.savePosition();
        if(weapons.isEmpty())
            return;
        CliPrinter.chooseWeaponMessage(lightGameVersion, myCharEnumString, weapons);
        new Thread(() -> {
            try {
                ActivateCardMessage message = new ActivateCardMessage(Integer.toString(showWeapons(weapons)));
                notifyController(message);
                CliSetUp.restorePosition();
                updateAll(lightGameVersion);
                actionState = 0;
            }
            catch (InputMismatchException e) {
                updateAll(lightGameVersion);
                CliPrinter.stamp("\n");
                showUsableWeapons(weapons);
            }
        }).start();
    }

    @Override
    public void showUsablePowerups(List<String> powerups) {

        CliPrinter.stamp("\n");
        CliSetUp.savePosition();
        toReborn = 1;
        if(powerups.isEmpty()) {
            toReborn = 0;
            updateAll(lightGameVersion);
            isAsking = false;
            getActionInput();
            return;
        }
        CliPrinter.usePowerUpMessage(lightGameVersion, myCharEnumString, powerups);
        new Thread(() -> {
            int choise;
            Scanner s = new Scanner(System.in);
            try {
                if (!isMyTurn()) {
                    CliReader reader3 = new CliReader(7);
                    try {
                        choise = reader3.getTimedInt();
                        if (choise != 99) {
                            int idCard;
                            if (choise < powerups.size()) {
                                idCard = Integer.parseInt(powerups.get(choise));
                            } else {
                                idCard = Integer.parseInt(powerups.get(0));
                            }

                            ActivateCardMessage message = new ActivateCardMessage(Integer.toString(idCard));
                            notifyController(message);
                            toReborn = 0;
                            isAsking = false;
                            CliSetUp.restorePosition();
                            updateAll(lightGameVersion);
                            CliPrinter.stamp("\n");
                        } else {
                            toReborn = 0;
                            updateAll(lightGameVersion);
                        }
                    } catch (NoInputException|IOException e) {
                        toReborn = 0;
                        updateAll(lightGameVersion);
                    }
                } else {
                    choise = s.nextInt();
                    CliSetUp.restorePosition();
                    int idCard;
                    if (choise < powerups.size()) {
                        idCard = Integer.parseInt(powerups.get(choise));
                    } else {
                        idCard = Integer.parseInt(powerups.get(0));
                    }

                    ActivateCardMessage message = new ActivateCardMessage(Integer.toString(idCard));
                    notifyController(message);
                    toReborn = 0;
                    isAsking = false;
                    CliSetUp.restorePosition();
                    updateAll(lightGameVersion);
                    CliPrinter.stamp("\n");
                }
            }
            catch(InputMismatchException e){
                    updateAll(lightGameVersion);
                    CliPrinter.stamp("\n");
                    showUsablePowerups(powerups);
                }
        }).start();
    }


    @Override
    public void resetTimer() {
        /*
            Not used in CLI.
         */
    }

    /**
     * Get the weapon chosen
     * @param weapons all possible weapons
     * @return -1 if weapons.size() == 0, else 0
     */
    private int showWeapons(List<String> weapons) {
        if(weapons.isEmpty())
            return -1;
        int choise;

        Scanner s = new Scanner(System.in);
        try {
            choise = s.nextInt();
            CliSetUp.restorePosition();
            Map<String, List<CardRep>> playerWeapons = lightGameVersion.getPlayerWeapons();
            List<CardRep> myWeapons = playerWeapons.get(myCharEnumString);
            int idCard;
            if (choise < weapons.size()) {
                idCard = myWeapons.get(choise).getId();
            } else {
                idCard = myWeapons.get(0).getId();
            }
            return idCard;
        }
        catch (InputMismatchException e) {
            throw new InputMismatchException();
        }
    }

    @Override
    public void showAmmoToDiscard() {

        CliPrinter.stamp("\n");
        CliSetUp.savePosition();
        List<String> ammoList = CliPrinter.discartAmmoMessage(lightGameVersion,myCharEnumString);
        new Thread(() -> {
            int choise;
            Scanner s = new Scanner(System.in);
            try {
                choise = s.nextInt();
                if (choise < ammoList.size() && choise >= 0) {
                    ChosenAmmoMessage message = new ChosenAmmoMessage(ammoList.get(choise));
                    notifyController(message);
                }
                isAsking = false;
                CliSetUp.restorePosition();
                updateAll(lightGameVersion);
            }
            catch (InputMismatchException e) {
                updateAll(lightGameVersion);
                showAmmoToDiscard();
            }
        }).start();
    }

    @Override
    public void showUsernameAlreadyInUse(String user) {
        CliSetUp.cursorRight(60);
        CliPrinter.stamp("Username not available!",CliColor.TEXTRED);
        try {
            Thread.sleep(1500);
        }
        catch (InterruptedException e) {
            HandyFunctions.LOGGER.log(Level.SEVERE,"Interrupted!");
            Thread.currentThread().interrupt();
        }
        setUserName();
        startConnection();
    }

    @Override
    public void showScoreboard(Map<String, Integer> scoreboard) {
        CliSetUp.clear();
        CliSetUp.cursorToHome();
        CliPrinter.stamp("CLASSIFICA: ", CliColor.TEXTGREEN);
        CliPrinter.stamp("\n");
        for (Map.Entry<String, Integer> s: scoreboard.entrySet()) {
            CliPrinter.stamp(s.getKey() + ": " + s.getValue() + "\n");
        }
        System.exit(0);
    }

    @Override
    public void showActionMenu() {

        if (powerUpChosen && toReborn == 0) {
            getActionInput();
            myTurn = true;
            actionState = 1;
        }
    }
}
